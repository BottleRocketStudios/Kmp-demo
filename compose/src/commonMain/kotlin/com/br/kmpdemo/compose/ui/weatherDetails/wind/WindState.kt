package com.br.kmpdemo.compose.ui.weatherDetails.wind

data class WindState(
    val windDirection: WindDirection? = null,
    val windGust: Double? = null,
    val windSpeed: String? = null,
    // May remove if imperial/metric setting becomes global setting
    val isMetric: Boolean = false,
)
