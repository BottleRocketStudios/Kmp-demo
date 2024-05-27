package com.br.kmpdemo.compose.ui.weatherDetails

import androidx.compose.runtime.Composable
import com.br.kmpdemo.compose.ui.home.HomeState

@Composable
fun HomeState.toWeatherDetailsState(): WeatherDetailsState = WeatherDetailsState(
    airQuality = airQuality.value,
    measurementPref = measurementPref.value,
    uvIndexWidget = getUvIndex(),
    sunriseSunsetState = getSunriseState(),
    windState = getWindState(),
    rainFallState = getRainFallState(),
    feelsLikeState = getFeelsLike(),
    humidityState = getHumidityInfo(),
    visibility = getVisibility(),
    barometricPressure = getPressure(),
)