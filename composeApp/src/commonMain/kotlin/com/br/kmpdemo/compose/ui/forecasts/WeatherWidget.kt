package com.br.kmpdemo.compose.ui.forecasts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.kmpdemo.compose.resources.SharedRes
import com.br.kmpdemo.compose.resources.theme.Colors
import com.br.kmpdemo.compose.resources.theme.Dimens
import com.br.kmpdemo.compose.resources.theme.Gradients
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun WeatherWidget(state: WeatherWidgetState) {
    with(state) {
        Surface(
            color = Color.Transparent,
            contentColor = Colors.onPrimary
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(Dimens.grid_24)
                    .background(
                        brush = Brush.linearGradient(Gradients.weatherWidgetGradient),
                        shape = SlantedShape()
                    )
            ) {
                Row(
                    modifier = Modifier.matchParentSize(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.padding(top = Dimens.grid_3_75, start = Dimens.grid_2_5),
                        content = {
                            Text(
                                text = tempCurrent ?: stringResource(SharedRes.strings.empty_digits_error),
                                style = MaterialTheme.typography.displayLarge.copy(fontSize = 64.sp),
                            )
                            Text(
                                modifier = Modifier.padding(top = Dimens.grid_2),
                                text = tempHighLow ?: stringResource(SharedRes.strings.highLowTempError),
                                style = MaterialTheme.typography.labelLarge.copy(color = Colors.onSecondary)
                            )
                            Text(
                                text = location ?: stringResource(SharedRes.strings.locationError),
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    )

                    Column(
                        modifier = Modifier.padding(end = Dimens.grid_2_5),
                        content = {
                            Image(
                                modifier = Modifier.size(140.dp),
                                painter = painterResource(weatherIcon.icon),
                                contentDescription = stringResource(weatherIcon.weather),
                            )
                            Text(
                                modifier = Modifier
                                    .padding(top = Dimens.grid_1_5)
                                    .align(alignment = Alignment.End),
                                text = stringResource(weatherIcon.weather),
                                style = MaterialTheme.typography.labelLarge
                            )
                        }
                    )
                }
            }
        }
    }
}