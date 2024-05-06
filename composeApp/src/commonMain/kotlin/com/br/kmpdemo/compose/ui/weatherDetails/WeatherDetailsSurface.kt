package com.br.kmpdemo.compose.ui.weatherDetails

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.br.kmpdemo.compose.resources.theme.Colors
import com.br.kmpdemo.compose.resources.theme.Dimens

@Composable
fun WeatherDetailsSurface(
    modifier: Modifier = Modifier.size(DETAILS_WIDGET_SIZE),
    columnModifier: Modifier = Modifier.padding(
        start = Dimens.grid_2_5,
        end = Dimens.grid_2_5,
        top = Dimens.grid_2_5,
    ),
    content: @Composable ColumnScope.() -> Unit
) {
    Surface(
        modifier = modifier,
        color = Colors.primaryContainer,
        shape = RoundedCornerShape(Dimens.grid_3),
        border = BorderStroke(
            width = 1.dp,
            color = Colors.outlineVariant,
        ),
        content = {
            Column(
                modifier = columnModifier,
                content = content
            )
        })
}