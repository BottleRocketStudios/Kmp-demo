package com.br.kmpdemo.compose.ui.forecasts

import ForecastChip
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.br.kmpdemo.compose.resources.theme.Dimens

@Composable
fun HourlyChipList(forecasts: List<ForecastState>) {
    LazyRow(
        modifier = Modifier
            .padding(start = Dimens.grid_1_75)
            .wrapContentHeight()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Dimens.grid_2_5),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        items(forecasts) { dailyForecast ->
            ForecastChip(state = dailyForecast) { state ->
                HourlyForecastChip(state)
            }
        }
    }
}