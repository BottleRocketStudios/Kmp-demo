package com.br.kmpdemo.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.br.kmpdemo.compose.ui.home.HomeState
import com.br.kmpdemo.models.PermissionsDialogState
import com.br.kmpdemo.utils.MeasurementType
import com.br.kmpdemo.ui.home.HomeViewModel

@Composable
fun HomeViewModel.toState() = HomeState(
    // Forecasts
    hourlyForecasts = hourlyForecasts.collectAsState(initForecasts),
    dailyForecasts = dailyForecasts.collectAsState(initForecasts),
    realTimeWeather = realTimeResponse.collectAsState(null),

    // Weather Details
    location = userLocation.collectAsState(null),
    temperature = currentTemp.collectAsState(null),
    temperatureHi = currentTempHi.collectAsState(null),
    temperatureLow = currentTempLow.collectAsState(null),
    weatherDescription = weatherDescription.collectAsState(null),
    airQuality = airQuality.collectAsState(null),
    feelsLikeState = feelsLikeState.collectAsState(null),
    humidityState = humidityState.collectAsState(null),
    pressureState = pressureState.collectAsState(null),
    rainFallState = rainFallState.collectAsState(null),
    sunriseSunsetState = sunriseSunsetState.collectAsState(null),
    uvIndexState = uvIndexState.collectAsState(null),
    visibilityState = visibilityState.collectAsState(null),
    windState = windState.collectAsState(null),

    // Units
    measurementPref = measurementPref.collectAsState(MeasurementType.IMPERIAL),

    // Permissions
    shouldShowPermissionsDialog = shouldShowPermissionsDialog.collectAsState(true),
    locationPermissionsDialog = toLocationPermissionsDialogState()
)

fun HomeViewModel.toLocationPermissionsDialogState() = PermissionsDialogState(
    permissionText = "Please enable your location permission for local weather information",
    onDismiss = ::onDismissLocationPermissionDialog,
)