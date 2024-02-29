package com.br.kmpdemo.compose.previews.utils

import com.br.kmpdemo.compose.ui.forecasts.ForecastState
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum
import com.br.kmpdemo.compose.ui.home.HomeState
import com.br.kmpdemo.compose.ui.weatherDetails.airQuality.AirQualityEnum
import com.br.kmpdemo.compose.ui.weatherDetails.feelsLike.FeelsLikeState
import com.br.kmpdemo.compose.ui.weatherDetails.humidity.HumidityState
import com.br.kmpdemo.compose.ui.weatherDetails.pressure.BarometricPressureState
import com.br.kmpdemo.compose.ui.weatherDetails.rainFall.RainFallState
import com.br.kmpdemo.compose.ui.weatherDetails.sunrise_sunset.SunriseSunsetState
import com.br.kmpdemo.compose.ui.weatherDetails.uvIndex.UVIndexEnum
import com.br.kmpdemo.compose.ui.weatherDetails.visibility.VisibilityState
import com.br.kmpdemo.compose.ui.weatherDetails.wind.WindDirectionEnum
import com.br.kmpdemo.compose.ui.weatherDetails.wind.WindState
import kotlin.random.Random

object MockData {
    private val weekdays = listOf("MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN")
    private val hours = (1..12).map { "$it AM" }.toMutableList()
    fun getMockWeeklyForecast(): List<ForecastState> {
        val nowIndex = Random.nextInt(from = 0, until = weekdays.lastIndex)
        return weekdays.map {
            ForecastState(
                dayTime = it,
                precipProbability = getMockPrecipProbability(),
                temperature = getMockTemp(),
                weatherIcon = getMockRandomIcon(),
                isNow = weekdays.indexOf(it) == nowIndex
            )
        }.toList()
    }

    fun getMockHourlyForecast(): List<ForecastState> {
        val nowIndex = Random.nextInt(from = 0, until = hours.lastIndex)
        return hours.map {
            ForecastState(
                dayTime = it,
                precipProbability = getMockPrecipProbability(),
                temperature = getMockTemp(),
                weatherIcon = getMockRandomIcon(),
                isNow = hours.indexOf(it) == nowIndex
            )
        }.toList()
    }

    fun getMockPrecipProbability(): Int {
        return Random.nextInt(from = 0, until = 99)
    }

    fun getMockTemp(): Int {
        return Random.nextInt(from = 0, until = 115)
    }

    fun getMockRandomIcon(): WeatherEnum {
        return WeatherEnum.values().random()
    }

    fun getMockHomeState(isError: Boolean = false): HomeState {
        if (isError) return HomeState(
            hourlyForecasts = getMockHourlyForecast(),
            dailyForecasts = getMockWeeklyForecast(),
            realTimeWeather = null,
            airQuality = AirQualityEnum.UNKNOWN,
            temperature = 76,
            temperatureHi = 80,
            temperatureLow = 68,
            feelsLikeState = FeelsLikeState(null, 65.0),
            humidityState = HumidityState(null, null),
            pressureState = BarometricPressureState(pressure = 0.0F),
            rainFallState = RainFallState(),
            sunriseSunsetState = SunriseSunsetState(
                localTime = null,
                sunriseTime = null,
                sunsetTime = null
            ),
            uvIndexState = UVIndexEnum.UNKNOWN,
            visibilityState = VisibilityState(),
            windState = WindState(windDirection = WindDirectionEnum.N)
        )

        return HomeState(
            hourlyForecasts = getMockHourlyForecast(),
            dailyForecasts = getMockWeeklyForecast(),
            realTimeWeather = null,
            airQuality = AirQualityEnum.YELLOW,
            temperature = 76,
            temperatureHi = 80,
            temperatureLow = 68,
            weatherDescription = WeatherEnum.SUNNY,
            feelsLikeState = FeelsLikeState(75.0, 65.0),
            humidityState = HumidityState(90.0, 17.0),
            pressureState = BarometricPressureState(pressure = 0.2F),
            rainFallState = RainFallState(1.8, 1.2),
            sunriseSunsetState = SunriseSunsetState(
                localTime = "13:00",
                sunriseTime = "4:58",
                sunsetTime = "17:35"
            ),
            uvIndexState = UVIndexEnum.MODERATE,
            visibilityState = VisibilityState("8 km"),
            windState = WindState(windDirection = WindDirectionEnum.N, windSpeed = "9.7"),
        )
    }

}