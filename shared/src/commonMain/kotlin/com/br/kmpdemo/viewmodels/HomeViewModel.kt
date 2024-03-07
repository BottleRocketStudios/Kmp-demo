package com.br.kmpdemo.viewmodels

import BaseViewModel
import MeasurementPreference
import co.touchlab.kermit.Logger
import com.br.kmpdemo.compose.ui.forecasts.ForecastState
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum
import com.br.kmpdemo.compose.ui.utils.WeatherCodes.getWeatherFromCode
import com.br.kmpdemo.compose.ui.weatherDetails.airQuality.AirQualityEnum
import com.br.kmpdemo.compose.ui.weatherDetails.feelsLike.FeelsLikeState
import com.br.kmpdemo.compose.ui.weatherDetails.humidity.HumidityState
import com.br.kmpdemo.compose.ui.weatherDetails.pressure.BarometricPressureState
import com.br.kmpdemo.compose.ui.weatherDetails.rainFall.RainFallState
import com.br.kmpdemo.compose.ui.weatherDetails.sunrise_sunset.SunriseSunsetState
import com.br.kmpdemo.compose.ui.weatherDetails.uvIndex.getUvIndex
import com.br.kmpdemo.compose.ui.weatherDetails.visibility.VisibilityState
import com.br.kmpdemo.compose.ui.weatherDetails.wind.WindState
import com.br.kmpdemo.compose.ui.weatherDetails.wind.getWindDirection
import com.br.kmpdemo.models.Forecast
import com.br.kmpdemo.models.RealTime
import com.br.kmpdemo.repositories.WeatherRepository
import com.br.kmpdemo.viewmodels.HomeViewModelUtils.convertUtcTimeForSunriseSunset
import com.br.kmpdemo.viewmodels.HomeViewModelUtils.extractCityName
import com.br.kmpdemo.viewmodels.HomeViewModelUtils.getPressureFloat
import com.br.kmpdemo.viewmodels.HomeViewModelUtils.toDailyForecastState
import com.br.kmpdemo.viewmodels.HomeViewModelUtils.toHourlyForecastState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import moe.tlaster.precompose.viewmodel.viewModelScope
import org.koin.core.component.inject

class HomeViewModel : BaseViewModel() {
    private val weatherRepo: WeatherRepository by inject()
    val measurementPref = MutableStateFlow(MeasurementPreference.preference)

    /**region Forecast Responses */
    val initForecasts = List(10) { ForecastState(weatherIcon = WeatherEnum.SUNNY) }
    private val hourlyResponse = MutableStateFlow<Forecast?>(null)
    private val dailyResponse = MutableStateFlow<Forecast?>(null)
    val realTimeResponse = MutableStateFlow<RealTime?>(null)
    //endregion

    /**region UI Forecasts */
    val hourlyForecasts = hourlyResponse
        .map { it?.timelines?.hourly?.toHourlyForecastState() ?: initForecasts }
    val dailyForecasts = dailyResponse
        .map { it?.timelines?.daily?.toDailyForecastState() ?: initForecasts }
    //endregion

    /**region Home Screen Weather Overlay */
    val location = realTimeResponse
        .map { it?.location?.name?.extractCityName() }
    val currentTemp = realTimeResponse
        .map { it?.data?.realTimeValues?.temperature?.toInt() }
    val weatherDescription = realTimeResponse
        .map { it?.data?.realTimeValues?.weatherCode?.getWeatherFromCode() }
    val currentTempHi = dailyForecasts
        .map { daily -> daily.find { it.isNow }?.temperatureMax }
    val currentTempLow = dailyForecasts
        .map { daily -> daily.find { it.isNow }?.temperatureMin } //endregion

    /**region Weather Details (Data for BottomSheet widgets) */
    // TODO: ASAA-176 Air Quality Data, Requires a separate call with a different key
    val airQuality = MutableStateFlow<AirQualityEnum?>(null)
    val feelsLikeState = realTimeResponse.map { realTime ->
        with(realTime?.data?.realTimeValues) {
            FeelsLikeState(
                this?.temperatureApparent,
                this?.temperature
            )
        }
    }
    val humidityState = realTimeResponse.map { realTime ->
        with(realTime?.data?.realTimeValues) {
            HumidityState(this?.dewPoint, this?.humidity)
        }
    }
    val pressureState = realTimeResponse.map { realTime ->
        BarometricPressureState(realTime?.data?.realTimeValues?.pressureSurfaceLevel?.getPressureFloat())
    }
    val rainFallState = combine(hourlyForecasts, dailyForecasts) { hourly, daily ->
        RainFallState(
            currentAccumulation = hourly.find { it.isNow }?.currentRainAccumulation,
            expectedAccumulation = daily.find { it.isNow }?.expectedRainAccumulation,
        )
    }
    val sunriseSunsetState = dailyForecasts.map { daily ->
        val today = daily.find { it.isNow }
        SunriseSunsetState(
            localTime = Clock.System.now().toString().convertUtcTimeForSunriseSunset(),
            sunriseTime = today?.sunriseTime?.convertUtcTimeForSunriseSunset(),
            sunsetTime = today?.sunsetTime?.convertUtcTimeForSunriseSunset(),
        )
    }
    val uvIndexState = realTimeResponse.map { realTime ->
        realTime?.data?.realTimeValues?.uvIndex?.getUvIndex()
    }
    val visibilityState = realTimeResponse.map { realTime ->
        VisibilityState(realTime?.data?.realTimeValues?.visibility?.toInt()?.toString())
    }
    val windState = realTimeResponse.map { realTime ->
        with(realTime?.data?.realTimeValues) {
            WindState(
                windDirection = this?.windDirection?.getWindDirection(),
                windGust = this?.windGust,
                windSpeed = this?.windSpeed?.toString(),
            )
        }
    }
    //endregion

    init {
        // TODO: ASAA-189 Get Current Location and remove hardcoded values
        getDailyForecasts(location = "75254")
        getHourlyForecasts(location = "75254")
        getRealTimeForecasts(location = "75254")
    }


    /**region Network calls */
    private fun getDailyForecasts(location: String) =
        viewModelScope.launch {
            weatherRepo.getDailyForecast(location = location, units = measurementPref.value.type)
                .onSuccess { dailyResponse.value = it }
                .onFailure { Logger.e("[getDailyForecasts]") { "Failure: ${it.message}" } }
        }

    private fun getHourlyForecasts(location: String) =
        viewModelScope.launch {
            weatherRepo.getHourlyForecast(location = location, units = measurementPref.value.type)
                .onSuccess { hourlyResponse.value = it }
                .onFailure { Logger.e("[getHourlyForecasts]") { "Failure: ${it.message}" } }
        }

    private fun getRealTimeForecasts(location: String) =
        viewModelScope.launch {
            weatherRepo.getRealTimeForecast(location, units = measurementPref.value.type)
                .onSuccess { realTimeResponse.value = it }
                .onFailure { Logger.e("[getRealTimeForecasts]") { "Failure: ${it.message}" } }
        }
    //endregion
}