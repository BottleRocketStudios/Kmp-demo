package com.br.kmmdemo.compose.ui.forecasts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.br.kmmdemo.resources.SharedRes
import com.br.kmmdemo.theme.Colors
import com.br.kmmdemo.theme.Dimens
import com.br.kmmdemo.theme.semiBold
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
                    precipProbability ?: stringResource(SharedRes.strings.percentageError),
                    color = Colors.inverseOnSurface,
                    style = MaterialTheme.typography.labelLarge.semiBold(),
                )
            })

        Text(
            temperature ?: stringResource(SharedRes.strings.tempError),
            style = MaterialTheme.typography.titleSmall
        )
    }
}