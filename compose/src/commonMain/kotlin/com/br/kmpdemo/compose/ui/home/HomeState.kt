package com.br.kmpdemo.compose.ui.home

import com.br.kmpdemo.compose.ui.forecasts.ForecastState
import com.br.kmpdemo.compose.ui.weatherDetails.WeatherDetailsState

data class HomeState(
    // Home Weather Overlay
    val location: String?,
    val currentTemp: String?,
    val weatherDescription: String?,
    val tempHigh: String?,
    val tempLow: String?,

    // Weather Details
    val hourlyForecasts: List<ForecastState>,
    val weeklyForecasts: List<ForecastState>,
    val weatherDetailsState: WeatherDetailsState
)
