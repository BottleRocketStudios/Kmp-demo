package com.br.kmpdemo.ui.home

import BaseViewModel
import KmpLocationProvider
import LastKnownLocation
import MeasurementPreference
import UserLocation
import co.touchlab.kermit.Logger
import com.bottlerocketstudios.launchpad.google.utils.network.service.airquality.AirQualityApiService
import com.br.kmpdemo.compose.ui.forecasts.ForecastState
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum
import com.br.kmpdemo.compose.ui.utils.WeatherCodes.getWeatherFromCode
import com.br.kmpdemo.compose.ui.weatherDetails.airQuality.AirQualityEnum
import com.br.kmpdemo.compose.ui.weatherDetails.airQuality.getAirQualityEnum
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
import com.br.kmpdemo.viewmodels.HomeViewModelUtils.getPressureFloat
import com.br.kmpdemo.viewmodels.HomeViewModelUtils.toCoordinates
import com.br.kmpdemo.viewmodels.HomeViewModelUtils.toDailyForecastState
import com.br.kmpdemo.viewmodels.HomeViewModelUtils.toHourlyForecastState
import com.br.kmpdemo.ui.BaseViewModel
import com.br.kmpdemo.utils.WeatherUtils.convertUtcTimeForSunriseSunset
import com.br.kmpdemo.utils.WeatherUtils.extractCityName
import com.br.kmpdemo.utils.WeatherUtils.getPressureFloat
import com.br.kmpdemo.utils.WeatherUtils.toDailyForecastState
import com.br.kmpdemo.utils.WeatherUtils.toHourlyForecastState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock
import org.koin.core.component.inject

class HomeViewModel : BaseViewModel() {
    private val weatherRepo: WeatherRepository by inject()
    private val locationProvider: KmpLocationProvider by inject()
    private val airQualityApiService: AirQualityApiService by inject()

    val measurementPref = MutableStateFlow(MeasurementPreference.preference)
    val shouldShowPermissionsDialog = MutableStateFlow(true)

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
    val userLocation = MutableStateFlow<String>("")
    val currentTemp = realTimeResponse
        .map { it?.data?.realTimeValues?.temperature?.toInt() }
    val weatherDescription = realTimeResponse
        .map { it?.data?.realTimeValues?.weatherCode?.getWeatherFromCode() }
    val currentTempHi = dailyForecasts
        .map { daily -> daily.find { it.isNow }?.temperatureMax }
    val currentTempLow = dailyForecasts
        .map { daily -> daily.find { it.isNow }?.temperatureMin } //endregion

    /**region Weather Details (Data for BottomSheet widgets) */
    // TODO: ASAA-176 Air Quality Data: will require using maps function
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

    /**region Network calls */
    private fun getDailyForecasts(location: String) =
       launchIO {
            weatherRepo.getDailyForecast(location = location, units = measurementPref.value.type)
                .onSuccess { dailyResponse.value = it }
                .onFailure { Logger.e("[getDailyForecasts]") { "Failure: ${it.message}" } }
        }

    private fun getHourlyForecasts(location: String) =
        launchIO {
            weatherRepo.getHourlyForecast(location = location, units = measurementPref.value.type)
                .onSuccess { hourlyResponse.value = it }
                .onFailure { Logger.e("[getHourlyForecasts]") { "Failure: ${it.message}" } }
        }

    private fun getRealTimeForecasts(location: String) =
        launchIO {
            weatherRepo.getRealTimeForecast(location, units = measurementPref.value.type)
                .onSuccess { realTimeResponse.value = it }
                .onFailure { Logger.e("[getRealTimeForecasts]") { "Failure: ${it.message}" } }
        }

    // TODO: ASAA-196 Add details for AirQualityWidget "See More" navigation
    private fun getAirQualityDetails(location: UserLocation) =
        launchIO {
            try {
                airQuality.value = airQualityApiService
                    .getCurrentAqiConditions(location.latitude, location.longitude)
                    .aqiConditions?.find { it.code == "usa_epa" }
                    ?.aqi?.getAirQualityEnum()
            } catch (e: Exception) {
                Logger.e("[onLocationPermissionsGranted]") { "Failure: ${e.message}" }
            }
        }
    //endregion

    /**region Permissions Utils */
    fun onDismissLocationPermissionDialog(permissionGranted: Boolean = false) {
        if (permissionGranted) {
            shouldShowPermissionsDialog.value = false
            onLocationPermissionsGranted()
        }
    }

    private fun onLocationPermissionsGranted() {
        launchIO {
            locationProvider.getUsersLocation()
            LastKnownLocation.userLocation.collect { location ->
                location?.let {
                    getDailyForecasts(location.toCoordinates())
                    getHourlyForecasts(location.toCoordinates())
                    getRealTimeForecasts(location.toCoordinates())
                    userLocation.value = location.cityName
                    getAirQualityDetails(location)
                }
            }
        }
    }
}