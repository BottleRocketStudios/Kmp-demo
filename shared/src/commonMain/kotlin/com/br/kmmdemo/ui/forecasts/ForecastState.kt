package com.br.kmmdemo.ui.forecasts

data class ForecastState(
    val dayTime: String? = null,
    val precipProbability: String? = null,
    val temperature: String? = null,
    val weatherIcon: WeatherEnum,
    val isNow: Boolean = false,
)
