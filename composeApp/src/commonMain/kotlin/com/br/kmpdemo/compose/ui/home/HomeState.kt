package com.br.kmpdemo.compose.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.br.kmpdemo.compose.ui.forecasts.ForecastState
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum
import com.br.kmpdemo.compose.ui.weatherDetails.airQuality.AirQualityEnum
import com.br.kmpdemo.compose.ui.weatherDetails.feelsLike.FeelsLikeState
import com.br.kmpdemo.compose.ui.weatherDetails.humidity.HumidityState
import com.br.kmpdemo.compose.ui.weatherDetails.rainFall.RainFallState
import com.br.kmpdemo.compose.ui.weatherDetails.sunrise_sunset.SunriseSunsetState
import com.br.kmpdemo.compose.ui.weatherDetails.uvIndex.UVIndexEnum
import com.br.kmpdemo.compose.ui.weatherDetails.wind.WindState
import com.br.kmpdemo.models.PermissionsDialogState
import com.br.kmpdemo.utils.MeasurementType

data class HomeState(
    /// Forecasts
    val hourlyForecasts: State<List<ForecastState>?>,
    val dailyForecasts: State<List<ForecastState>?>,
    val location: State<String?> = mutableStateOf(null),

    /// Weather Details
    val airQuality: State<AirQualityEnum?> = mutableStateOf(null),
    val temperature: State<Double?> = mutableStateOf(null),
    val temperatureHi: State<Double?> = mutableStateOf(null),
    val temperatureLow: State<Double?> = mutableStateOf(null),
    val sunriseSunsetState: State<SunriseSunsetState?> = mutableStateOf(null),
    val rainFallState: State<RainFallState?> = mutableStateOf(null),
    val windState: State<WindState?> = mutableStateOf(null),
    val weatherVisibility: State<Int?> = mutableStateOf(null),
    val weatherDescriptionEnum: State<WeatherEnum?> = mutableStateOf(null),
    val uvIndexEnum: State<UVIndexEnum?> = mutableStateOf(null),
    val feelsLikeState: State<FeelsLikeState?> = mutableStateOf(null),
    val humidityState: State<HumidityState?> = mutableStateOf(null),
    val biometricPressure: State<Float?> = mutableStateOf(null),

    // Units
    val measurementPref: State<MeasurementType?> = mutableStateOf(null),

    // Permissions
    val shouldShowPermissionsDialog: State<Boolean> = mutableStateOf(false),
    val locationPermissionsDialog: PermissionsDialogState,
)