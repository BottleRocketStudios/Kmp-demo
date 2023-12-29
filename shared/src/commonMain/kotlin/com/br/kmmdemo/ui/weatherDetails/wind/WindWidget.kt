package com.br.kmmdemo.ui.weatherDetails.wind

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.rememberTextMeasurer
import com.br.kmmdemo.resources.SharedRes
import com.br.kmmdemo.theme.Colors
import com.br.kmmdemo.theme.Dimens
import com.br.kmmdemo.theme.bold
import com.br.kmmdemo.theme.getKmpTypography
import com.br.kmmdemo.ui.weatherDetails.DetailsWidgetLabel
import com.br.kmmdemo.ui.weatherDetails.WeatherDetailsSurface
import com.br.kmmdemo.utils.drawCardinalLabels
import com.br.kmmdemo.utils.drawCardinalLines
import com.br.kmmdemo.utils.drawCompassGauge
import com.br.kmmdemo.utils.drawCompassNeedle
import com.br.kmmdemo.utils.drawNorthArrow
import com.br.kmmdemo.utils.drawWindSpeedText
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun WindWidget(state: WindState) {
    val themeTertiary = Colors.onTertiary
    val cardinalStyle = getKmpTypography().labelLarge.copy(color = Colors.onPrimary).bold()
    val windSpeedStyle = getKmpTypography().titleSmall.copy(color = Colors.onPrimary).bold()
    val perHourStyle = getKmpTypography().bodySmall.copy(color = Colors.onPrimary).bold()
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

