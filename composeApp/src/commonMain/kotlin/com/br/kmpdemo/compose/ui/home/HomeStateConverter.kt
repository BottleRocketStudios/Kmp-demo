package com.br.kmpdemo.compose.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.br.kmpdemo.compose.ui.forecasts.ForecastState
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum
import com.br.kmpdemo.compose.ui.utils.WeatherCodes.getWeatherFromCode
import com.br.kmpdemo.compose.ui.utils.WeatherUtils.toForecastTimeFormat
import com.br.kmpdemo.compose.ui.weatherDetails.airQuality.getAirQualityEnum
import com.br.kmpdemo.compose.ui.weatherDetails.rainFall.RainFallState
import com.br.kmpdemo.models.Daily
import com.br.kmpdemo.models.Hourly
import com.br.kmpdemo.models.PermissionsDialogState
import com.br.kmpdemo.viewmodels.HomeViewModel
import com.br.kmpdemo.utils.MeasurementType
import com.br.kmpdemo.utils.date
import com.br.kmpdemo.utils.isHour
import com.br.kmpdemo.utils.isToday
import com.br.kmpdemo.utils.time
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

val initForecasts: List<ForecastState> = List(10) { ForecastState(weatherIcon = WeatherEnum.SUNNY) }

@Composable
fun HomeViewModel.toState() = HomeState(
    // Forecasts
    hourlyForecasts = hourlyForecasts.map { it.toHourlyForecastState() }.collectAsState(initForecasts),
    dailyForecasts = dailyForecasts.map { it.toDailyForecastState() }.collectAsState(initForecasts),
    realTimeWeather = realTimeWeather.collectAsState(null),
    currentDaily = currentDaily.collectAsState(null),
    currentHourly = currentHourly.collectAsState(null),
    location = userLocation.collectAsState(null),

    // Weather Details
    airQuality = airQualityIndex.map { it?.getAirQualityEnum() }.collectAsState(null),
    rainFall = combine(currentDaily, currentHourly) { daily, hourly ->
        RainFallState(daily?.rainAccumulationMax , hourly?.rainAccumulation)
    }.collectAsState(null),

    // Units
    measurementPref = measurementPref.collectAsState(MeasurementType.IMPERIAL),

    // Permissions
    shouldShowPermissionsDialog = shouldShowPermissionsDialog.collectAsState(true),
    locationPermissionsDialog = toLocationPermissionsDialogState()
)

fun HomeViewModel.toLocationPermissionsDialogState() = PermissionsDialogState(
    permissionText = "Please enable your location permission for local weather information",
    onDismiss = ::onDismissLocationPermissionDialog,
)

fun List<Hourly?>?.toHourlyForecastState(): List<ForecastState> {
    val dailyForecasts = arrayListOf<ForecastState>()
    this?.forEach { hourly ->
        with(hourly?.hourlyValues) {
            dailyForecasts.add(
                ForecastState(
                    currentRainAccumulation = this?.rainAccumulation,
                    dayTime = hourly?.time?.time()?.toForecastTimeFormat(),
                    precipProbability = this?.precipitationProbability?.toInt(),
                    temperature = this?.temperature?.toInt(),
                    weatherIcon = this?.weatherCode?.getWeatherFromCode() ?: WeatherEnum.SUNNY,
                    isNow = hourly?.time?.isHour() ?: false
                )
            )
        }
    }
    return dailyForecasts
}

fun List<Daily?>?.toDailyForecastState(): List<ForecastState> {
    val dailyForecasts = arrayListOf<ForecastState>()
    this?.forEach { daily ->
        with(daily?.dailyValues) {
            dailyForecasts.add(
                ForecastState(
                    dayTime = daily?.time?.date()?.dayOfWeek.toString().take(3),
                    expectedRainAccumulation = this?.rainAccumulationMax,
                    precipProbability = this?.precipitationProbabilityMax?.toInt(),
                    temperature = this?.temperatureAvg?.toInt(),
                    temperatureMax = this?.temperatureMax?.toInt(),
                    temperatureMin = this?.temperatureMin?.toInt(),
                    sunriseTime = this?.sunriseTime,
                    sunsetTime = this?.sunsetTime,
                    weatherIcon = this?.weatherCodeMin?.getWeatherFromCode() ?: WeatherEnum.SUNNY,
                    isNow = daily?.time?.isToday() ?: false
                )
            )
        }
    }
    return dailyForecasts
}
