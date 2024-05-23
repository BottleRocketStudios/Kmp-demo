package com.br.kmpdemo.compose.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.br.kmpdemo.compose.ui.utils.MockData
import com.br.kmpdemo.compose.resources.theme.KmpDemoTheme
import com.br.kmpdemo.compose.ui.forecasts.HourlyChipList

@Composable
@Preview
fun HourlyChipListPreview() {
    KmpDemoTheme {
        HourlyChipList(MockData.getMockHourlyForecast())
    }
}