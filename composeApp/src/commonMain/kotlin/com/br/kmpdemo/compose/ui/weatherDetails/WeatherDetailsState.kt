package com.br.kmpdemo.compose.ui.weatherDetails

import com.br.kmpdemo.compose.ui.weatherDetails.airQuality.AirQualityEnum
import com.br.kmpdemo.compose.ui.weatherDetails.feelsLike.FeelsLikeState
import com.br.kmpdemo.compose.ui.weatherDetails.humidity.HumidityState
import com.br.kmpdemo.compose.ui.weatherDetails.rainFall.RainFallState
import com.br.kmpdemo.compose.ui.weatherDetails.sunrise_sunset.SunriseSunsetState
import com.br.kmpdemo.compose.ui.weatherDetails.uvIndex.UVIndexEnum
import com.br.kmpdemo.compose.ui.weatherDetails.wind.WindState
import com.br.kmpdemo.utils.MeasurementType

data class WeatherDetailsState(
    val airQuality: AirQualityEnum?,
    val measurementPref: MeasurementType?,
    val uvIndexWidget: UVIndexEnum? = UVIndexEnum.UNKNOWN,
    val sunriseSunsetState: SunriseSunsetState? = SunriseSunsetState(),
    val windState: WindState? = WindState(),
    val rainFallState: RainFallState? = RainFallState(),
    val feelsLikeState: FeelsLikeState? = FeelsLikeState(),
    val humidityState: HumidityState? = HumidityState(),
    val visibility: Int?,
    val barometricPressure: Float?,
)
