
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import com.br.kmpdemo.compose.resources.theme.Colors
import com.br.kmpdemo.compose.resources.theme.Dimens

@Composable
fun BarometerGauge(pressure: Float) {
    val themeTertiary = Colors.onTertiary
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.grid_2, vertical = Dimens.grid_1),
        onDraw = {
            val diameter = size.width // Defines diameter of Barometer
            val radius = diameter / 2
            val gaugeWidth = 34f // Defines the stroke width of Barometer
            val dashPattern = floatArrayOf(4f, 12f) // Defines the dash pattern for the Barometer
            val topLeftOffset = Offset(size.width / 2 - radius, size.height / 2 - radius)

            // Draw the dashed background circle
            drawArc(
                color = themeTertiary,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = topLeftOffset,
                size = Size(diameter, diameter),
                style = Stroke(width = gaugeWidth, pathEffect = PathEffect.dashPathEffect(dashPattern, phase = 20F))
            )

            // Define the fading barometer reading gradient
            val gradient = Brush.sweepGradient(
                pressure - 0.2F to Color.Transparent,
                pressure to Color.White,
                pressure to Color.Transparent,
                center = Offset(size.width / 2, size.height / 2)
            )

            // Rotate arc and line (barometer reading) so that 0 is at the top
            rotate(degrees = -90F) {
                // Draw the barometric pressure reading with the gradient that fades
                drawArc(
                    brush = gradient,
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    topLeft = topLeftOffset,
                    size = Size(diameter, diameter),
                    style = Stroke(width = gaugeWidth, cap = StrokeCap.Round)
                )

                // Draw the leading line using the calculated positions
                if (pressure != 0F) {
                    drawLine(
                        color = Color.White,
                        start = center.getBarometerLineOffset(pressure, radius + gaugeWidth / 2 + 3),
                        end = center.getBarometerLineOffset(pressure, radius - gaugeWidth / 2 - 3),
                        strokeWidth = 13F,
                        cap = StrokeCap.Round
                    )
                }
            }
        }
    )
}