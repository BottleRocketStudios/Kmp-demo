package com.br.kmpdemo.compose.ui.weatherDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.br.kmpdemo.compose.resources.theme.Dimens
import com.br.kmpdemo.compose.ui.weatherDetails.airQuality.AirQualityEnum
import com.br.kmpdemo.compose.ui.weatherDetails.airQuality.AirQualityWidget
import com.br.kmpdemo.compose.ui.weatherDetails.feelsLike.FeelsLikeState
import com.br.kmpdemo.compose.ui.weatherDetails.feelsLike.FeelsLikeWidget
import com.br.kmpdemo.compose.ui.weatherDetails.humidity.HumidityState
import com.br.kmpdemo.compose.ui.weatherDetails.humidity.HumidityWidget
import com.br.kmpdemo.compose.ui.weatherDetails.pressure.BarometricPressureWidget
import com.br.kmpdemo.compose.ui.weatherDetails.rainFall.RainFallWidget
import com.br.kmpdemo.compose.ui.weatherDetails.sunrise_sunset.SunriseSunsetState
import com.br.kmpdemo.compose.ui.weatherDetails.sunrise_sunset.SunriseSunsetWidget
import com.br.kmpdemo.compose.ui.weatherDetails.uvIndex.UVIndexEnum
import com.br.kmpdemo.compose.ui.weatherDetails.uvIndex.UvIndexWidget
import com.br.kmpdemo.compose.ui.weatherDetails.visibility.VisibilityWidget
import com.br.kmpdemo.compose.ui.weatherDetails.wind.WindWidget

@Composable
fun WeatherDetails(state: WeatherDetailsState) {
    with(state) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
                .padding(
                    top = Dimens.grid_1_25,
                    start = Dimens.grid_2_5,
                    end = Dimens.grid_2_5,
                ),
        ) {
            AirQualityWidget(airQuality ?: AirQualityEnum.UNKNOWN) /// TODO: ASAA-177

            WeatherDetailsRow {
                UvIndexWidget(uvIndexWidget ?: UVIndexEnum.UNKNOWN)
                SunriseSunsetWidget(sunriseSunsetState ?: SunriseSunsetState())
            }

            WeatherDetailsRow {
                WindWidget(windState, state.measurementPref)
                RainFallWidget(rainFallState, state.measurementPref)
            }

            WeatherDetailsRow {
                FeelsLikeWidget(feelsLikeState ?: FeelsLikeState())
                HumidityWidget(humidityState ?: HumidityState())
            }

            WeatherDetailsRow {
                VisibilityWidget(visibility, state.measurementPref)
                BarometricPressureWidget(barometricPressure ?: 0.0f)
            }
        }
    }
}