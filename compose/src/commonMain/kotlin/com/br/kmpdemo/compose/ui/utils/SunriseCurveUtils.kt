import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.br.kmpdemo.compose.resources.theme.Colors
import com.br.kmpdemo.compose.resources.theme.sunriseBlue
import kotlin.math.pow

@Composable
fun SunriseBezierCurve(currentTime: Float, sunrise: Float, sunset: Float) {
    val gray = Colors.onSecondary
    Canvas(modifier = Modifier.fillMaxWidth().height(42.dp)) {
        with(size) {
            // Control Points for the first half of curve
            val p0 = Offset(0f, height)
            val p1 = Offset(width * 0.2f, height)
            val p2 = Offset(width * 0.2f, 0f)
            val p3 = Offset(width * 0.5f, 0f)

            // Control points for the second half of curve
            val p4 = Offset(width * 0.5f, 0f)
            val p5 = Offset(width * 0.8f, 0f)
            val p6 = Offset(width * 0.8f, height)
            val p7 = Offset(width, height)

            // Create Bezier Curve Path using control points above
            val path = Path().apply {
                moveTo(p0.x, p0.y)
                cubicTo(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y)
                cubicTo(p5.x, p5.y, p6.x, p6.y, p7.x, p7.y)
            }

            // Sampling the curve according to enable
            val firstHalfSamples = sampleCurvePath(p0, p1, p2, p3, sampleCount = 1440)
            val secondHalfSamples = sampleCurvePath(p4, p5, p6, p7, sampleCount = 1440)

            // Combine curve samples and drop the overlap (control points 3 & 4)
            val fullCurveSamples = firstHalfSamples + secondHalfSamples.drop(1)

            // Indicator (Sun icon)
            val indicatorX = indicatorLerp(0f, width, currentTime)
            val indicatorY = interpolateY(fullCurveSamples, indicatorX)

            // Divider Line (Horizon)
            val horizonX = indicatorLerp(0f, width, sunrise)
            val horizonY = interpolateY(fullCurveSamples, horizonX)

            // Draw curve path on the canvas
            drawPath(
                path = path,
                brush = Brush.horizontalGradient(
                    colorStops = arrayOf(
                        Pair(sunrise - 0.07F, sunriseBlue),
                        Pair(0.5F, Color.White),
                        Pair(sunset, sunriseBlue), // 0.85F
                    ),
                    startX = 0f,
                    endX = width,
                ),
                style = Stroke(10f)
            )

            // Draw the indicator along the path (the Sun)
            drawCircle(
                color = Color.Yellow,
                radius = 10f,
                center = Offset(indicatorX, indicatorY)
            )

            drawCircle(
                color = Color.Yellow.copy(alpha = 0.1F),
                radius = 20f,
                center = Offset(indicatorX, indicatorY)
            )

            // Draw sunrise horizon line
            drawLine(
                color = gray,
                start = Offset(0f, horizonY),
                end = Offset(width, horizonY),
                strokeWidth = 2f
            )

        }
    }
}

///// Helper Functions to Calculate Offset for Indicator to follow Bezier Path /////
fun indicatorLerp(start: Float, stop: Float, initValue: Float): Float = (1 - initValue) * start + initValue * stop

// Finds the x & y coordinates along the sunrise curve for the samples
fun calculateBezierPoint(sunrise: Float, p0: Offset, p1: Offset, p2: Offset, p3: Offset): Offset {
    val adjustedSunrise = 1 - sunrise
    return Offset(
        x = adjustedSunrise.pow(3) *
                p0.x + 3 * adjustedSunrise.pow(2) *
                sunrise * p1.x + 3 * adjustedSunrise *
                sunrise.pow(2) * p2.x + sunrise.pow(3) * p3.x,
        y = adjustedSunrise.pow(3) *
                p0.y + 3 * adjustedSunrise.pow(2) *
                sunrise * p1.y + 3 * adjustedSunrise *
                sunrise.pow(2) * p2.y + sunrise.pow(3) * p3.y
    )
}

// Create samples to keep indicator on sunrise curve
fun sampleCurvePath(p0: Offset, p1: Offset, p2: Offset, p3: Offset, sampleCount: Int): List<Offset> =
    (0..sampleCount).map { i ->
        calculateBezierPoint(i / sampleCount.toFloat(), p0, p1, p2, p3)
    }

fun interpolateY(samples: List<Offset>, x: Float): Float {
    // Find two points between which x falls
    if (samples.isEmpty()) return -1F
    val lowerPoint = samples.lastOrNull { it.x <= x } ?: samples.first()
    val upperPoint = samples.firstOrNull { it.x >= x } ?: samples.last()

    // If x is exactly at one of the sample points
    if (lowerPoint.x == x) return lowerPoint.y
    if (upperPoint.x == x) return upperPoint.y

    // Linear interpolation
    val ratio = (x - lowerPoint.x) / (upperPoint.x - lowerPoint.x)
    return lowerPoint.y + ratio * (upperPoint.y - lowerPoint.y)
}

// Turn time string into float in range 0.0F to 1.0F (Must use 24 hour clock format)
fun String?.timeToFractionOfDay(): Float {
    val parts = splitTimeString(this)
    return ((parts[0].toInt() * 60) + parts[1].toInt()) / 1440f
}

// Changes 24-hour clock time string to 12-hour
fun String?.adjustSunsetTime(): String {
    val parts = splitTimeString(this)
    return if (parts[0].toInt() > 12) "${parts[0].toInt() - 12}:${parts[1]}" else this!!
}

fun splitTimeString(time: String?): List<String> =
    time?.split(":")?.let { parts ->
        if (parts.size != 2) {
            throw IllegalArgumentException("Invalid time format. Expected HH:mm; Found $time")
        }
        listOf(parts[0], parts[1])
    } ?: emptyList()