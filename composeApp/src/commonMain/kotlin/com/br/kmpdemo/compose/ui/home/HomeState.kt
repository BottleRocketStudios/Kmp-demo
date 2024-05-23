package com.br.kmpdemo.compose.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.br.kmpdemo.compose.ui.forecasts.ForecastState
import com.br.kmpdemo.compose.ui.utils.WeatherCodes.getWeatherFromCode
import com.br.kmpdemo.compose.ui.utils.WeatherUtils.convertUtcTimeForSunriseSunset
import com.br.kmpdemo.compose.ui.utils.WeatherUtils.getPressureFloat
import com.br.kmpdemo.compose.ui.weatherDetails.airQuality.AirQualityEnum
import com.br.kmpdemo.compose.ui.weatherDetails.feelsLike.FeelsLikeState
import com.br.kmpdemo.compose.ui.weatherDetails.humidity.HumidityState
import com.br.kmpdemo.compose.ui.weatherDetails.rainFall.RainFallState
import com.br.kmpdemo.compose.ui.weatherDetails.sunrise_sunset.SunriseSunsetState
import com.br.kmpdemo.compose.ui.weatherDetails.uvIndex.uvIndexEnum
import com.br.kmpdemo.compose.ui.weatherDetails.wind.WindState
import com.br.kmpdemo.compose.ui.weatherDetails.wind.getWindDirection
import com.br.kmpdemo.models.DailyValues
import com.br.kmpdemo.models.HourlyValues
import com.br.kmpdemo.models.PermissionsDialogState
import com.br.kmpdemo.models.RealTime
import com.br.kmpdemo.utils.MeasurementType
import kotlinx.datetime.Clock.System.now

data class HomeState(
    /// Forecasts
    val hourlyForecasts: State<List<ForecastState>?>,
    val dailyForecasts: State<List<ForecastState>?>,
    // TODO - Use UI models here and remove domain from state?
    val realTimeWeather: State<RealTime?>,
    val currentDaily: State<DailyValues?>,
    val currentHourly: State<HourlyValues?>,
    val location: State<String?> = mutableStateOf(null),

    /// Weather Details
    val airQuality: State<AirQualityEnum?> = mutableStateOf(null),

    // Units
    val measurementPref: State<MeasurementType?> = mutableStateOf(null),

    // Permissions
    val shouldShowPermissionsDialog: State<Boolean> = mutableStateOf(true),
    val locationPermissionsDialog: PermissionsDialogState,
) {
//    TODO - Talk to team about moving these to converter.
    @Composable
    fun getTemperature() = realTimeWeather.value?.data?.realTimeValues?.temperature

    @Composable
    fun getTemperatureHi() = currentDaily.value?.temperatureMax

    @Composable
    fun getTemperatureLow() = currentDaily.value?.temperatureMin

    @Composable
    fun getSunriseState() = currentDaily.value?.let {
        SunriseSunsetState(
            localTime = now().toString().convertUtcTimeForSunriseSunset(),
            sunriseTime = it.sunriseTime?.convertUtcTimeForSunriseSunset(),
            sunsetTime = it.sunsetTime?.convertUtcTimeForSunriseSunset(),
        )
    }

    @Composable
    fun getRainFallState() = RainFallState(
        currentAccumulation = currentHourly.value?.rainAccumulation,
        expectedAccumulation = currentDaily.value?.rainAccumulationMax
    )

    @Composable
    fun getWindState() = realTimeWeather.value?.data?.realTimeValues?.let {
        WindState(
            windDirection = it.windDirection?.getWindDirection(),
            windGust = it.windGust,
            windSpeed = it.windSpeed?.toString()
        )
    }

    @Composable
    fun getVisibility() = realTimeWeather.value?.data?.realTimeValues?.visibility?.toInt()

    @Composable
    fun getWeatherDescription() = realTimeWeather.value?.data?.realTimeValues?.weatherCode?.getWeatherFromCode()

    @Composable
    fun getUvIndex() = realTimeWeather.value?.data?.realTimeValues?.uvIndex?.uvIndexEnum()

    @Composable
    fun getFeelsLike() = realTimeWeather.value?.data?.realTimeValues?.let {
        FeelsLikeState(it.temperatureApparent, it.temperature)
    }

    @Composable
    fun getHumidityInfo() = realTimeWeather.value?.data?.realTimeValues?.let {
        HumidityState(it.dewPoint, it.humidity)
    }

    @Composable
    fun getPressure() =  realTimeWeather.value?.data?.realTimeValues?.pressureSurfaceLevel?.getPressureFloat()
}