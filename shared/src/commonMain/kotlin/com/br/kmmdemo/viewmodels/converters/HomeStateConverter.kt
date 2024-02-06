package com.br.kmmdemo.viewmodels.converters

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.br.kmmdemo.compose.ui.forecasts.ForecastState
import com.br.kmmdemo.compose.ui.home.HomeState
import com.br.kmmdemo.viewmodels.HomeViewModel

@Composable
fun HomeViewModel.toState() = HomeState(
    location = location.collectAsState(null).value,
    currentTemp = currentTemp.collectAsState(null).value,
    weatherDescription = weatherDescription.collectAsState(null).value,
    tempHigh = tempHigh.collectAsState(null).value,
    tempLow = tempLow.collectAsState(null).value,

    hourlyForecasts = hourlyForecasts.collectAsState(listOf<ForecastState>()).value,
    weeklyForecasts = weeklyForecasts.collectAsState(listOf<ForecastState>()).value,
    weatherDetailsState = weatherDetailsState.collectAsState().value,
)