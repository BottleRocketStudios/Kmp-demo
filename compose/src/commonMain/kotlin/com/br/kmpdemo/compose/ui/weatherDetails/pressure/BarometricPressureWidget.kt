
import androidx.compose.runtime.Composable
import com.br.kmpdemo.compose.ui.weatherDetails.DetailsWidgetLabel
import com.br.kmpdemo.compose.ui.weatherDetails.WeatherDetailsSurface
import com.br.kmpdemo.compose.ui.weatherDetails.pressure.BarometricPressureState
import com.br.kmpdemo.compose.resources.SharedRes

@Composable
fun BarometricPressureWidget(state: BarometricPressureState) {
    with (state) {
        WeatherDetailsSurface(
            content = {
                DetailsWidgetLabel(
                    icon = SharedRes.images.pressure_icon,
                    iconDesc = SharedRes.strings.pressure,
                    label = SharedRes.strings.pressure,
                )

                BarometerGauge(pressure ?: 0F)
            }
        )
    }
}