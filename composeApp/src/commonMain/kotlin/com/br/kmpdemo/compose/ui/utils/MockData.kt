package com.br.kmpdemo.compose.ui.utils

import androidx.compose.runtime.mutableStateOf
import com.br.kmpdemo.compose.ui.forecasts.ForecastState
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum
import com.br.kmpdemo.compose.ui.home.HomeState
import com.br.kmpdemo.compose.ui.weatherDetails.airQuality.AirQualityEnum
import com.br.kmpdemo.models.DailyValues
import com.br.kmpdemo.models.HourlyValues
import com.br.kmpdemo.models.PermissionsDialogState
import com.br.kmpdemo.models.RealTime
import com.br.kmpdemo.models.RealTimeData
import com.br.kmpdemo.models.RealTimeValues
import com.br.kmpdemo.utils.MeasurementType
import kotlin.random.Random

// FIXME - Move this to common Main
object MockData {
    private fun getMockPrecipProbability() = Random.nextInt(from = 0, until = 99)
    private fun getMockTemp() = Random.nextInt(from = 0, until = 115)
    private fun getMockRandomIcon() = WeatherEnum.entries.random()

    private val weekdays = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")
    private val hours = (1..12).map { "$it AM" }.toMutableList()
    fun getMockWeeklyForecast(nowIndex: Int = Random.nextInt(from = 0, until = weekdays.lastIndex)) =
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
            realTimeWeather = mutableStateOf(RealTime(
                location = null,
                data = RealTimeData(
                    time = null,
                    realTimeValues = RealTimeValues(
                        humidity = null,
                        dewPoint = null,
                        temperatureApparent = 76.0,
                        pressureSurfaceLevel = 0.0,
                        windDirection = 0.0,
                    )
                )
            )),
            currentDaily = mutableStateOf(DailyValues(
                temperatureMax = 80.0,
                temperatureMin = 68.0,

            )),
            currentHourly = mutableStateOf(HourlyValues()),
            airQuality = mutableStateOf(AirQualityEnum.UNKNOWN),
            measurementPref = mutableStateOf(MeasurementType.IMPERIAL),
            locationPermissionsDialog = PermissionsDialogState("") {}
        )

        return HomeState(
            hourlyForecasts = mutableStateOf(getMockHourlyForecast()),
            dailyForecasts = mutableStateOf(getMockWeeklyForecast()),
            airQuality = mutableStateOf(AirQualityEnum.YELLOW),
            realTimeWeather = mutableStateOf(RealTime(
                location = null,
                data = RealTimeData(
                    time = null,
                    realTimeValues = RealTimeValues(
                        humidity = 17.0,
                        dewPoint = 90.0,
                        temperatureApparent = 76.0,
                        pressureSurfaceLevel = 0.2,
                        windDirection = 0.0,
                        windSpeed = 9.7,
                        windGust = 12.3,
                        weatherCode = 1000.0,
                        uvIndex = 5.0,
                        visibility = 8.0,
                    )
                )
            )),
            currentDaily = mutableStateOf(DailyValues(
                temperatureMax = 80.0,
                temperatureMin = 68.0,
                rainAccumulationMax = 2.2,
                sunriseTime = "010224T083312",
                sunsetTime = "010224T183312"
                )),
            currentHourly = mutableStateOf(HourlyValues(
                rainAccumulation = 1.8,
            )),

            measurementPref = mutableStateOf(MeasurementType.IMPERIAL),
            locationPermissionsDialog = PermissionsDialogState("") {}
        )
    }

}