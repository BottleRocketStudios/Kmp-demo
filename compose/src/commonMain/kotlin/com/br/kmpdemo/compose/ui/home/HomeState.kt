package com.br.kmpdemo.compose.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.br.kmpdemo.compose.ui.forecasts.ForecastState
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum
import com.br.kmpdemo.compose.ui.weatherDetails.airQuality.AirQualityEnum
import com.br.kmpdemo.compose.ui.weatherDetails.feelsLike.FeelsLikeState
import com.br.kmpdemo.compose.ui.weatherDetails.humidity.HumidityState
import com.br.kmpdemo.compose.ui.weatherDetails.pressure.BarometricPressureState
import com.br.kmpdemo.compose.ui.weatherDetails.rainFall.RainFallState
import com.br.kmpdemo.compose.ui.weatherDetails.sunrise_sunset.SunriseSunsetState
import com.br.kmpdemo.compose.ui.weatherDetails.uvIndex.UVIndexEnum
import com.br.kmpdemo.compose.ui.weatherDetails.visibility.VisibilityState
import com.br.kmpdemo.compose.ui.weatherDetails.wind.WindState
import com.br.kmpdemo.models.RealTime
import com.br.kmpdemo.utils.MeasurementType

data class HomeState(
    /// Forecasts
    val hourlyForecasts: State<List<ForecastState>?>,
    val dailyForecasts: State<List<ForecastState>?>,
    val realTimeWeather: State<RealTime?>,

    /// Weather Details
    val location: State<String?> = mutableStateOf(null),
    val temperature: State<Int?> = mutableStateOf(null),
    val temperatureHi: State<Int?> = mutableStateOf(null),
    val temperatureLow: State<Int?> = mutableStateOf(null),
    val weatherDescription: State<WeatherEnum?> = mutableStateOf(null),
    val airQuality: State<AirQualityEnum?> = mutableStateOf(null),
    val feelsLikeState: State<FeelsLikeState?> = mutableStateOf(null),
    val humidityState: State<HumidityState?> = mutableStateOf(null),
    val pressureState: State<BarometricPressureState?> = mutableStateOf(null),
    val rainFallState: State<RainFallState?> = mutableStateOf(null),
    val sunriseSunsetState: State<SunriseSunsetState?> = mutableStateOf(null),
    val uvIndexState: State<UVIndexEnum?> = mutableStateOf(null),
    val visibilityState: State<VisibilityState?> = mutableStateOf(null),
    val windState: State<WindState?> = mutableStateOf(null),

    // Units
    val measurementPref: State<MeasurementType?> = mutableStateOf(null)
)