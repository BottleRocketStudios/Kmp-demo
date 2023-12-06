import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.br.kmmdemo.resources.SharedRes
import com.br.kmmdemo.theme.Dimens
import com.br.kmmdemo.theme.letterSpacing
import com.br.kmmdemo.ui.weatherDetails.DetailsWidgetLabel
import com.br.kmmdemo.ui.weatherDetails.WeatherDetailsSurface
import com.br.kmmdemo.ui.weatherDetails.feelsLike.FeelsLikeState
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun FeelsLikeWidget(feelsLike: FeelsLikeState) {
    WeatherDetailsSurface(
        content = {
            DetailsWidgetLabel(
                icon = SharedRes.images.feels_like_icon,
                iconDesc = SharedRes.strings.feels_like,
                label = SharedRes.strings.feels_like,
            )

            Text(
                "${feelsLike.temperatureApparent?.toInt() ?: stringResource(SharedRes.strings.tempError)}°",
                modifier = Modifier.padding(top = Dimens.grid_2),
                style = MaterialTheme.typography.titleLarge.letterSpacing(0.96.sp)
            )

            Text(
                stringResource(feelsLike.description),
                modifier = Modifier.padding(top = Dimens.grid_3),
                style = MaterialTheme.typography.labelLarge
            )
        }
    )
}