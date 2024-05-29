package com.br.kmpdemo.compose.ui.utils

import androidx.compose.runtime.mutableStateOf
import com.br.kmpdemo.compose.ui.forecasts.ForecastState
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum
import com.br.kmpdemo.compose.ui.home.HomeState
import com.br.kmpdemo.compose.ui.weatherDetails.airQuality.AirQualityEnum
import com.br.kmpdemo.models.PermissionsDialogState
import com.br.kmpdemo.utils.MeasurementType
import kotlin.random.Random

// FIXME - Move this to common Main
object MockData {
    private fun getMockPrecipProbability() = Random.nextInt(from = 0, until = 99)
    private fun getMockTemp() = Random.nextInt(from = 0, until = 115)
    private fun getMockRandomIcon() = WeatherEnum.entries.random()

    private val weekdays = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")
    private val hours = (1..12).map { "$it AM" }.toMutableList()
    fun getMockWeeklyForecast(
        nowIndex: Int = Random.nextInt(
            from = 0,
            until = weekdays.lastIndex
        )
    ) =
        weekdays.map {
            ForecastState(
                dayTime = it,
                precipProbability = getMockPrecipProbability(),
                temperature = getMockTemp(),
                weatherIcon = getMockRandomIcon(),
                isNow = weekdays.indexOf(it) == nowIndex
            )
        }.toList()

    fun getMockHourlyForecast(nowIndex: Int = Random.nextInt(from = 0, until = hours.lastIndex)) =
        hours.map {
            ForecastState(
                dayTime = it,
                precipProbability = getMockPrecipProbability(),
                temperature = getMockTemp(),
                weatherIcon = getMockRandomIcon(),
                isNow = hours.indexOf(it) == nowIndex
            )
        }.toList()

    fun getMockHomeState(isError: Boolean = false): HomeState {
        if (isError) return HomeState(
            hourlyForecasts = mutableStateOf(getMockHourlyForecast()),
            dailyForecasts = mutableStateOf(getMockWeeklyForecast()),
            airQuality = mutableStateOf(AirQualityEnum.UNKNOWN),
            measurementPref = mutableStateOf(MeasurementType.IMPERIAL),
            locationPermissionsDialog = PermissionsDialogState("") {}
        )

        return HomeState(
            hourlyForecasts = mutableStateOf(getMockHourlyForecast()),
            dailyForecasts = mutableStateOf(getMockWeeklyForecast()),
            airQuality = mutableStateOf(AirQualityEnum.YELLOW),
            measurementPref = mutableStateOf(MeasurementType.IMPERIAL),
            locationPermissionsDialog = PermissionsDialogState("") {}
        )
    }

}