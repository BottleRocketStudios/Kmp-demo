import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.br.kmpdemo.compose.resources.SharedRes
import com.br.kmpdemo.compose.resources.theme.Dimens
import com.br.kmpdemo.compose.resources.theme.letterSpacing
import com.br.kmpdemo.compose.ui.weatherDetails.DetailsWidgetLabel
import com.br.kmpdemo.compose.ui.weatherDetails.WeatherDetailsSurface
import com.br.kmpdemo.compose.ui.weatherDetails.visibility.VisibilityState
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun VisibilityWidget(visibilityState: VisibilityState) {
    WeatherDetailsSurface(
        content = {
            DetailsWidgetLabel(
                icon = SharedRes.images.visibility_icon,
                iconDesc = SharedRes.strings.visibility,
                label = SharedRes.strings.visibility,
            )

            // TODO: Add metric/imperial conditional
            Text(
                visibilityState.currentVisibility?.let { visibility ->
                    stringResource(SharedRes.strings.input_visibility_mph, visibility)
                } ?: stringResource(SharedRes.strings.empty_digits_error),
                modifier = Modifier.padding(top = Dimens.grid_2),
                style = MaterialTheme.typography.titleLarge.letterSpacing(0.96.sp)
            )

            Text(
                stringResource(SharedRes.strings.visibility_miles),
                modifier = Modifier.padding(top = Dimens.grid_3),
                style = MaterialTheme.typography.labelLarge
            )
        }
    )
}