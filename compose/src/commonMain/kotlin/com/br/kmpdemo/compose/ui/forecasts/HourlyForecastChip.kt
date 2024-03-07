package com.br.kmpdemo.compose.ui.forecasts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.br.kmpdemo.compose.resources.SharedRes
import com.br.kmpdemo.compose.resources.theme.Colors
import com.br.kmpdemo.compose.resources.theme.Dimens
import com.br.kmpdemo.compose.resources.theme.semiBold
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun HourlyForecastChip(state: ForecastState) {
    with(state) {
        Text(
            dayTime ?: stringResource(SharedRes.strings.empty_digits_error),
            style = MaterialTheme.typography.bodySmall.semiBold()
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(Dimens.grid_0_25),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = {
                Image(
                    modifier = Modifier.size(Dimens.grid_4_5),
                    painter = painterResource(weatherIcon.icon),
                    contentDescription = stringResource(SharedRes.strings.weatherIcon),
                )
                Text(
                    precipProbability?.let { probability ->
                        stringResource(SharedRes.strings.input_percentage, probability)
                    } ?: stringResource(SharedRes.strings.number_error),
                    color = Colors.inverseOnSurface,
                    style = MaterialTheme.typography.labelLarge.semiBold()
                )
            })

        Text(
            temperature?.let {
                stringResource(SharedRes.strings.input_degrees, temperature)
            } ?: stringResource(SharedRes.strings.tempError),
            style = MaterialTheme.typography.titleSmall
        )
    }
}