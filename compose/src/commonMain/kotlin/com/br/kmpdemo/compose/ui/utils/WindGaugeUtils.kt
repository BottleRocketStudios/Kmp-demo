package com.br.kmpdemo.compose.ui.utils

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextMeasurer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.br.kmpdemo.compose.ui.weatherDetails.wind.WindDirection
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

// Constants for Cardinal Point Labels
const val NORTH = "N"
const val SOUTH = "S"
const val EAST = "E"
const val WEST = "W"

// Constants for repeated values
const val GAUGE_WIDTH = 20F
const val RADIANS_OFFSET = -90
const val ARROW_LENGTH = 20F
const val ARROW_WIDTH = 15F
const val NEEDLE_LINE_LENGTH = 70F

fun DrawScope.drawCompassGauge(
    gaugeColor: Color,
    gaugeWidth: Float = com.br.kmpdemo.compose.ui.utils.GAUGE_WIDTH
) {
    val radius = size.width / 2
    val topLeftOffset = Offset(size.width / 2 - radius, size.height / 2 - radius)
    drawArc(
        color = gaugeColor,
        startAngle = 0f,
        sweepAngle = 360f,
        useCenter = false,
        topLeft = topLeftOffset,
        size = Size(size.width, size.width),
        style = Stroke(
            width = gaugeWidth,
            pathEffect = PathEffect.dashPathEffect(
                floatArrayOf(2f, 3f), // [0] Dash thickness, [1] Spacing
                phase = 13F // Offset for first dash
            )
        )
    )
}

fun DrawScope.drawCardinalLines(lineColor: Color) {
    val radius = size.width / 2
    // Draw the 12 cardinal and intermediate point lines
    for (i in 0..11) {
        val thetaIncrement = 30 // Angle between each line in degrees
        val lineLength = 10 // Length of line for each point
        val offsetAlongRadius = 10 // Offset to adjust lines along the radius
        val theta = thetaIncrement * i * (PI / 180) // Convert degrees to radians
        // Calculate the start and end points for the lines
        val start = Offset(
            (center.x + (radius + offsetAlongRadius) * cos(theta)).toFloat(),
            (center.y + (radius + offsetAlongRadius) * sin(theta)).toFloat()
        )
        val end = Offset(
            (center.x + (radius - lineLength) * cos(theta)).toFloat(),
            (center.y + (radius - lineLength) * sin(theta)).toFloat()
        )
        // Draw the line
        drawLine(
            color = lineColor,
            start = start,
            end = end,
            strokeWidth = 4f
        )
    }
}

fun DrawScope.drawNorthArrow() {
    val radius = size.width / 2
    val windDirectionRadians = com.br.kmpdemo.compose.ui.utils.RADIANS_OFFSET * (PI / 180)
    val northArrow = Path().drawNeedleArrowHead(
        startPoint = Offset(center.x, center.y - radius),
        windDirectionRadians = windDirectionRadians,
        arrowWidth = 8F,
        arrowLength = 12F
    )
    drawPath(path = northArrow, color = Color.LightGray, style = Fill)
}

fun DrawScope.drawCardinalLabels(
    textMeasurer: TextMeasurer,
    cardinalStyle: TextStyle,
) {
    val radius = size.width / 2
    val positions = mapOf(
        com.br.kmpdemo.compose.ui.utils.NORTH to Offset(center.x, center.y - radius + 32),
        com.br.kmpdemo.compose.ui.utils.EAST to Offset(center.x + radius - 32, center.y),
        com.br.kmpdemo.compose.ui.utils.SOUTH to Offset(center.x, center.y + radius - 36),
        com.br.kmpdemo.compose.ui.utils.WEST to Offset(center.x - radius + 36, center.y)
    )

    positions.forEach { (text, position) ->
        val annotatedString = buildAnnotatedString { append(text) }
        // Measure the text layout
        val textLayoutResult = textMeasurer.measure(annotatedString, cardinalStyle)
        // Calculate the offset to center the text at the specified position
        val textOffset = Offset(
            x = position.x - textLayoutResult.size.width / 2,
            y = position.y - textLayoutResult.size.height / 2
        )

        drawText(
            textMeasurer = textMeasurer,
            text = annotatedString,
            topLeft = textOffset,
            style = cardinalStyle
        )
    }
}

fun DrawScope.drawWindSpeedText(
    windSpeed: String,
    windSpeedStyle: TextStyle,
    textMeasurer: TextMeasurer,
    perHourStyle: TextStyle,
    perHourString: String,
) {
    val currentWindSpeed = buildAnnotatedString { append(windSpeed) }
    val speedLayoutResult = textMeasurer.measure(currentWindSpeed, windSpeedStyle)

    drawText(
        textMeasurer = textMeasurer,
        text = currentWindSpeed,
        topLeft = Offset(center.x - speedLayoutResult.size.width / 2, (center.y - speedLayoutResult.size.height / 2) - 22),
        style = windSpeedStyle
    )

    val speedPerHour = buildAnnotatedString { append(perHourString) }
    val perHourLayoutResult = textMeasurer.measure(speedPerHour, perHourStyle)

    drawText(
        textMeasurer = textMeasurer,
        text = speedPerHour,
        topLeft = Offset(center.x - perHourLayoutResult.size.width / 2, (center.y - perHourLayoutResult.size.height / 2) + 22),
        style = perHourStyle
    )
}

fun DrawScope.drawCompassNeedle(
    gaugeWidth: Float = com.br.kmpdemo.compose.ui.utils.GAUGE_WIDTH,
    windDirection: WindDirection = WindDirection.N,
    density: Density,
) {
    val radius = size.width / 2
    val circleRadiusPx = with(density) { 10.dp.toPx() }
    val needleStrokeWidthPx = with(density) { 2.dp.toPx() }

    // Wind direction in radians. Degrees adjusted by subtracting 90
    // degrees to align correctly with the gauge that has been rotated for `Phase`
    val windDirectionRadians = (windDirection.degrees + com.br.kmpdemo.compose.ui.utils.RADIANS_OFFSET) * (PI / 180)

    // Calculate start and end points for arrow needle
    val needlePointStart = center + Offset(
        x = (radius - gaugeWidth / 2) * cos(windDirectionRadians).toFloat(),
        y = (radius - gaugeWidth / 2) * sin(windDirectionRadians).toFloat()
    )

    val needlePointEnd = center + Offset(
        x = (radius - com.br.kmpdemo.compose.ui.utils.NEEDLE_LINE_LENGTH) * cos(windDirectionRadians).toFloat(),
        y = (radius - com.br.kmpdemo.compose.ui.utils.NEEDLE_LINE_LENGTH) * sin(windDirectionRadians).toFloat()
    )

    // Calculate start and end points for circle needle
    val needleCircleStart = center - Offset(
        x = (radius - circleRadiusPx / 2) * cos(windDirectionRadians).toFloat(),
        y = (radius - circleRadiusPx / 2) * sin(windDirectionRadians).toFloat()
    )

    val needleCircleEnd = center - Offset(
        x = (radius - com.br.kmpdemo.compose.ui.utils.NEEDLE_LINE_LENGTH) * cos(windDirectionRadians).toFloat(),
        y = (radius - com.br.kmpdemo.compose.ui.utils.NEEDLE_LINE_LENGTH) * sin(windDirectionRadians).toFloat()
    )

    // Draw needle lines -- Point for line with arrow, Circle for line with circle
    drawLine(
        color = Color.White,
        start = needlePointStart,
        end = needlePointEnd,
        strokeWidth = needleStrokeWidthPx
    )
    drawLine(
        color = Color.White,
        start = needleCircleStart,
        end = needleCircleEnd,
        strokeWidth = needleStrokeWidthPx
    )

    val arrowHeadPath = Path().drawNeedleArrowHead(needlePointStart, windDirectionRadians)

    // Draw arrowhead point of the needle
    drawPath(path = arrowHeadPath, color = Color.White, style = Fill)

    // Draw the circle at the opposite end of the needle
    drawCircle(
        color = Color.White,
        center = center - Offset(
            x = (radius * 0.98F) * cos(windDirectionRadians).toFloat(),
            y = (radius * 0.98F) * sin(windDirectionRadians).toFloat()
        ),
        radius = (circleRadiusPx / 2.5).toFloat(),
        style = Stroke(width = needleStrokeWidthPx)
    )
}

fun Path.drawNeedleArrowHead(
    startPoint: Offset,
    windDirectionRadians: Double,
    arrowLength: Float = com.br.kmpdemo.compose.ui.utils.ARROW_LENGTH,
    arrowWidth: Float = com.br.kmpdemo.compose.ui.utils.ARROW_WIDTH
): Path = Path().apply {
    moveTo(startPoint.x, startPoint.y)
    lineTo(
        startPoint.x - arrowWidth * cos(windDirectionRadians - PI / 2).toFloat(),
        startPoint.y - arrowWidth * sin(windDirectionRadians - PI / 2).toFloat()
    )
    lineTo(
        startPoint.x + arrowLength * cos(windDirectionRadians).toFloat(),
        startPoint.y + arrowLength * sin(windDirectionRadians).toFloat()
    )
    lineTo(
        startPoint.x + arrowWidth * cos(windDirectionRadians - PI / 2).toFloat(),
        startPoint.y + arrowWidth * sin(windDirectionRadians - PI / 2).toFloat()
    )
    close()
}