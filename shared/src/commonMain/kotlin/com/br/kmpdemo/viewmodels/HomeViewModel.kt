package com.br.kmpdemo.viewmodels

import com.br.kmpdemo.compose.ui.forecasts.ForecastState
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum
import com.br.kmpdemo.compose.ui.weatherDetails.WeatherDetailsState
import com.br.kmpdemo.compose.ui.weatherDetails.airQuality.AirQualityEnum
import com.br.kmpdemo.compose.ui.weatherDetails.feelsLike.FeelsLikeState
import com.br.kmpdemo.compose.ui.weatherDetails.humidity.HumidityState
import com.br.kmpdemo.compose.ui.weatherDetails.pressure.BarometricPressureState
import com.br.kmpdemo.compose.ui.weatherDetails.rainFall.RainFallState
import com.br.kmpdemo.compose.ui.weatherDetails.sunrise_sunset.SunriseSunsetState
import com.br.kmpdemo.compose.ui.weatherDetails.uvIndex.UVIndexEnum
import com.br.kmpdemo.compose.ui.weatherDetails.visibility.VisibilityState
import com.br.kmpdemo.compose.ui.weatherDetails.wind.WindState
import kotlinx.coroutines.flow.MutableStateFlow
import moe.tlaster.precompose.viewmodel.ViewModel

class HomeViewModel : ViewModel() {
    // Home Weather Overlay
    val location = MutableStateFlow<String?>(null)
    val currentTemp = MutableStateFlow<String?>(null)
    val weatherDescription = MutableStateFlow<String?>(null)
    val tempHigh = MutableStateFlow<String?>(null)
    val tempLow = MutableStateFlow<String?>(null)

    // Forecasts
    private val initForecasts = List(10) { ForecastState(weatherIcon = WeatherEnum.DAY_FOG) }
    val hourlyForecasts = MutableStateFlow(initForecasts)
    val weeklyForecasts = MutableStateFlow(initForecasts)

    // Weather Details
    private val airQualityState = MutableStateFlow(AirQualityEnum.UNKNOWN)
    private val feelsLikeState = MutableStateFlow(FeelsLikeState())
    private val humidityState = MutableStateFlow(HumidityState())
    private val barometricPressureState = MutableStateFlow(BarometricPressureState())
    private val rainFallState = MutableStateFlow(RainFallState())
    private val sunriseSunsetState = MutableStateFlow(SunriseSunsetState())
    private val uvIndexState = MutableStateFlow(UVIndexEnum.UNKNOWN)
    private val visibilityState = MutableStateFlow(VisibilityState())
    private val windState = MutableStateFlow(WindState())

    val weatherDetailsState = MutableStateFlow(
        WeatherDetailsState(
            airQuality = airQualityState.value,
            feelsLikeState = feelsLikeState.value,
            humidityState = humidityState.value,
            pressureState = barometricPressureState.value,
            rainFallState = rainFallState.value,
            sunriseSunsetState = sunriseSunsetState.value,
            uvIndexState = uvIndexState.value,
            visibilityState = visibilityState.value,
            windState = windState.value
        )
    )
}