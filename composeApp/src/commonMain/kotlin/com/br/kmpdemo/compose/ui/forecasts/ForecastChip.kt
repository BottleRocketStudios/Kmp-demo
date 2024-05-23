package com.br.kmpdemo.compose.ui.forecasts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.br.kmpdemo.compose.resources.theme.Colors
import com.br.kmpdemo.compose.resources.theme.Dimens

@Composable
fun ForecastChip(
    state: ForecastState,
    content: @Composable ColumnScope.(ForecastState) -> Unit,
) = with(state) {
    Surface(
        modifier = Modifier.width(60.dp).height(146.dp),
        color = if (isNow) Colors.surfaceVariant else Colors.surface,
        shape = RoundedCornerShape(Dimens.grid_3_75),
        shadowElevation = Dimens.grid_1_25,
        border = BorderStroke(
            width = 1.dp,
            color = if (isNow) Colors.outline else Colors.outlineVariant,
        ),
    ) {
        Column(
            modifier = Modifier.padding(horizontal = Dimens.grid_1, vertical = Dimens.grid_2),
            verticalArrangement = Arrangement.spacedBy(Dimens.grid_1, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            content = { content(state) },
        )
    }
}