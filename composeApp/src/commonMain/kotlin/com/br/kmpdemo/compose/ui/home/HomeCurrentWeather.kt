package com.br.kmpdemo.compose.ui.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.br.kmpdemo.compose.resources.SharedRes
import com.br.kmpdemo.compose.resources.theme.Colors
import com.br.kmpdemo.compose.resources.theme.Dimens
import com.br.kmpdemo.compose.resources.theme.bold
import com.br.kmpdemo.compose.resources.theme.light
import com.br.kmpdemo.compose.resources.theme.size
import com.br.kmpdemo.utils.WeatherUtils.asDisplayUnit
import com.br.kmpdemo.utils.WeatherUtils.roundToDisplayDouble
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun HomeCurrentWeather(
    state: HomeState,
    isExpanded: Boolean,
) {
    with(state) {
        Column(
            modifier = Modifier.padding(Dimens.grid_6),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                // Location Name
                Text(
                    text = location.value ?: stringResource(SharedRes.strings.locationError),
                    style = MaterialTheme.typography.titleLarge,
                    color = Colors.onPrimary,
                    textAlign = TextAlign.Center,
                )
                // Current Temp
                AnimatedVisibility(visible = !isExpanded) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = temperature.value?.let { temp ->
                                    stringResource(
                                        SharedRes.strings.input_degrees_with_unit,
                                        temp.roundToDisplayDouble(),
                                        state.measurementPref.value.asDisplayUnit(),
                                    )
                                } ?: stringResource(SharedRes.strings.tempError),
                            color = Colors.onPrimary,
                            style = MaterialTheme.typography.displayLarge.size(84.sp).light(),
                            textAlign = TextAlign.Center,
                        )

                        // Weather Description
                        Text(
                            text = stringResource(
                                weatherDescriptionEnum.value?.weather
                                    ?: SharedRes.strings.description_error
                            ),
                            style = MaterialTheme.typography.titleMedium,
                            color = Colors.onPrimary.copy(alpha = 0.5F),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                // Location, temp, and description when sheet is expanded
                AnimatedVisibility(visible = isExpanded) {
                    Text(
                        text = temperature.value?.let { temp ->
                            weatherDescriptionEnum.value?.weather?.let { desc ->
                                stringResource(
                                    SharedRes.strings.input_collapsed_details,
                                        temp,
                                        stringResource(desc),
                                    )
                                }
                            } ?: stringResource(SharedRes.strings.input_collapsed_error),
                        modifier = Modifier.padding(top = Dimens.grid_1_5),
                        style = MaterialTheme.typography.titleMedium,
                        color = Colors.onPrimary.copy(alpha = 0.5F),
                        textAlign = TextAlign.Center,
                    )
                }
                // Temp high and Low
                AnimatedVisibility(visible = !isExpanded) {
                    Text(
                        text = temperatureHi.value?.let { max ->
                            temperatureLow.value?.let { min ->
                                stringResource(SharedRes.strings.temp_high_low, max, min)
                            }
                            } ?: stringResource(SharedRes.strings.highLowTempError),
                        style = MaterialTheme.typography.titleMedium.bold(),
                        color = Colors.onPrimary,
                        textAlign = TextAlign.Center,
                    )
                }
            },
        )
    }
}
