package com.br.kmpdemo.compose.ui.weatherDetails.wind

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import com.br.kmpdemo.compose.resources.theme.Colors
import com.br.kmpdemo.compose.resources.theme.Dimens
import com.br.kmpdemo.compose.resources.theme.bold
import com.br.kmpdemo.compose.ui.utils.drawCardinalLabels
import com.br.kmpdemo.compose.ui.utils.drawCardinalLines
import com.br.kmpdemo.compose.ui.utils.drawCompassGauge
import com.br.kmpdemo.compose.ui.utils.drawCompassNeedle
import com.br.kmpdemo.compose.ui.utils.drawNorthArrow
import com.br.kmpdemo.compose.ui.utils.drawWindSpeedText
import com.br.kmpdemo.compose.ui.weatherDetails.DetailsWidgetLabel
import com.br.kmpdemo.compose.ui.weatherDetails.WeatherDetailsSurface
import com.br.kmpdemo.compose.resources.SharedRes
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun WindWidget(state: WindState) {
    val themeTertiary = Colors.onTertiary
    val cardinalStyle = MaterialTheme.typography.labelLarge.copy(color = Colors.onPrimary).bold()
    val windSpeedStyle = MaterialTheme.typography.titleSmall.copy(color = Colors.onPrimary).bold()
    val perHourStyle = MaterialTheme.typography.bodySmall.copy(color = Colors.onPrimary).bold()
    val perHourString = stringResource(
        if (state.isMetric) SharedRes.strings.wind_kmh else SharedRes.strings.wind_mph
    )
    val errorString = stringResource(SharedRes.strings.empty_digits_error)
    val textMeasurer = rememberTextMeasurer()

    // Use LocalDensity to convert dp units to pixel units
    val density = LocalDensity.current

    WeatherDetailsSurface(
        content = {
            DetailsWidgetLabel(
                icon = SharedRes.images.wind_icon,
                iconDesc = SharedRes.strings.wind,
                label = SharedRes.strings.wind,
            )

            with(state) {
                Canvas(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            horizontal = Dimens.grid_1_5,
                            vertical = Dimens.grid_0_25
                        ),
                    onDraw = {
                        drawCompassGauge(themeTertiary)
                        drawCardinalLines(themeTertiary)
                        drawNorthArrow()
                        drawCardinalLabels(textMeasurer, cardinalStyle)
                        drawWindSpeedText(
                            windSpeed ?: errorString,
                            windSpeedStyle,
                            textMeasurer,
                            perHourStyle,
                            perHourString,
                        )
                        // If wind speed or direction is null, do not draw needle
                        windSpeed?.let {
                            windDirection?.let { direction ->
                                drawCompassNeedle(windDirection = direction, density = density)
                            }
                        }
                    }
                )
            }
        }
    )
}

