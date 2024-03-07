import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.br.kmpdemo.compose.resources.SharedRes
import com.br.kmpdemo.compose.resources.theme.Dimens
import com.br.kmpdemo.compose.resources.theme.letterSpacing
import com.br.kmpdemo.compose.ui.home.HomeState
import com.br.kmpdemo.compose.ui.weatherDetails.DetailsWidgetLabel
import com.br.kmpdemo.compose.ui.weatherDetails.WeatherDetailsSurface
import com.br.kmpdemo.utils.MeasurementType
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun VisibilityWidget(state: HomeState) {
    val isMetric = state.measurementPref.value == MeasurementType.METRIC
    val visibilityUnit =
        if (isMetric) SharedRes.strings.visibility_km else SharedRes.strings.visibility_miles
    val visibilityInputUnit =
        if (isMetric) SharedRes.strings.input_visibility_km else SharedRes.strings.input_visibility_mph

    with(state.visibilityState) {
        WeatherDetailsSurface(
            content = {
                DetailsWidgetLabel(
                    icon = SharedRes.images.visibility_icon,
                    iconDesc = SharedRes.strings.visibility,
                    label = SharedRes.strings.visibility,
                )

                Text(
                    value?.currentVisibility?.let { visibility ->
                        stringResource(visibilityInputUnit, visibility)
                    } ?: stringResource(SharedRes.strings.empty_digits_error),
                    modifier = Modifier.padding(top = Dimens.grid_2),
                    style = MaterialTheme.typography.titleLarge.letterSpacing(0.96.sp)
                )

                Text(
                    stringResource(visibilityUnit),
                    modifier = Modifier.padding(top = Dimens.grid_3),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        )
    }

}