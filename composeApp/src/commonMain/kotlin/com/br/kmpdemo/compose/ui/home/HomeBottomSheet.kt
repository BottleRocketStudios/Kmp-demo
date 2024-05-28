package com.br.kmpdemo.compose.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.br.kmpdemo.compose.resources.SharedRes
import com.br.kmpdemo.compose.resources.theme.Dimens
import com.br.kmpdemo.compose.ui.forecasts.ForecastState
import com.br.kmpdemo.compose.ui.forecasts.ForecastTabState
import com.br.kmpdemo.compose.ui.forecasts.ForecastTabs
import com.br.kmpdemo.compose.ui.forecasts.HourlyChipList
import com.br.kmpdemo.compose.ui.forecasts.WeeklyChipList
import com.br.kmpdemo.compose.ui.shared.GradientCard
import com.br.kmpdemo.compose.ui.weatherDetails.WeatherDetailsRow
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
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun HomeBottomSheet(state: HomeState, isExpanded: Boolean) {
    GradientCard(isExpanded) {
        Column(modifier = Modifier.fillMaxHeight()) {

            ForecastTabs(
                generateTabs(state.hourlyForecasts.value, state.dailyForecasts.value)
            )

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
                AirQualityWidget(
                    state.airQuality.value ?: AirQualityEnum.UNKNOWN
                ) /// TODO: ASAA-177

                WeatherDetailsRow {
                    UvIndexWidget(state.getUvIndex() ?: UVIndexEnum.UNKNOWN)
                    SunriseSunsetWidget(state.getSunriseState() ?: SunriseSunsetState())
                }

                WeatherDetailsRow {
                    WindWidget(state.getWindState(), state.measurementPref.value)
                    RainFallWidget(state.getRainFallState(), state.measurementPref.value)
                }

                WeatherDetailsRow {
                    FeelsLikeWidget(state.getFeelsLike() ?: FeelsLikeState())
                    HumidityWidget(state.getHumidityInfo() ?: HumidityState())
                }

                WeatherDetailsRow {
                    VisibilityWidget(state.getVisibility(), state.measurementPref.value)
                    BarometricPressureWidget(state.getPressure() ?: 0.0f)
                }
            }
        }
    }
}

@Composable
private fun generateTabs(hourly: List<ForecastState>?, daily: List<ForecastState>?) = listOf(
    ForecastTabState(
        itemTitle = stringResource(SharedRes.strings.hourly_forecast),
        content = { hourly?.let { HourlyChipList(it) } ?: unavailableMessage() }
    ),
    ForecastTabState(
        itemTitle = stringResource(SharedRes.strings.weekly_forecast),
        content = { daily?.let { WeeklyChipList(it) } ?: unavailableMessage() }
    )
)

@Composable
private fun unavailableMessage() {
    Text(
        text = stringResource(SharedRes.strings.forecasts_unavailable),
        style = MaterialTheme.typography.bodySmall,
    )
}