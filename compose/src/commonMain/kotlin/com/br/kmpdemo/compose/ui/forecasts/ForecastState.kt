package com.br.kmpdemo.compose.ui.forecasts

data class ForecastState(
    val currentRainAccumulation: Double? = null,
    val dayTime: String? = null,
    val expectedRainAccumulation: Double? = null,
    val precipProbability: Int? = null,
    val temperature: Int? = null,
    val temperatureMax: Int? = null,
    val temperatureMin: Int? = null,
    val sunriseTime: String? = null,
    val sunsetTime: String? = null,
    val weatherIcon: WeatherEnum,
    val isNow: Boolean = false,
)