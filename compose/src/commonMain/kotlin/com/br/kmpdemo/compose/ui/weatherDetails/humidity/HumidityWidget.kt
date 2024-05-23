package com.br.kmpdemo.compose.ui.weatherDetails.humidity

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.br.kmpdemo.compose.resources.SharedRes
import com.br.kmpdemo.compose.ui.weatherDetails.DetailsWidgetLabel
import com.br.kmpdemo.compose.ui.weatherDetails.WeatherDetailsSurface
import com.br.kmpdemo.compose.resources.theme.Dimens
import com.br.kmpdemo.compose.resources.theme.letterSpacing
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun HumidityWidget(humidityState: HumidityState) {
    WeatherDetailsSurface(
        content = {
            DetailsWidgetLabel(
                icon = SharedRes.images.humidity_icon,
                iconDesc = SharedRes.strings.humidity,
                label = SharedRes.strings.humidity,
            )

            Text(
                stringResource(
                    SharedRes.strings.input_percentage,
                    humidityState.humidity?.toInt()
                        ?: stringResource(SharedRes.strings.number_error)
                ),
                modifier = Modifier.padding(top = Dimens.grid_2),
                style = MaterialTheme.typography.titleLarge.letterSpacing(0.96.sp)
            )

            Text(
                stringResource(
                    SharedRes.strings.humidity_dew_description,
                    humidityState.dewPoint?.toInt()
                        ?: stringResource(SharedRes.strings.unknown_number)
                ),
                modifier = Modifier.padding(top = Dimens.grid_3),
                style = MaterialTheme.typography.labelLarge
            )
        }
    )
}