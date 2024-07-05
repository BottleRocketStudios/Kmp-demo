package com.br.kmpdemo.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.br.kmpdemo.compose.resources.theme.KmpDemoTheme
import com.br.kmpdemo.compose.ui.forecasts.WeeklyChipList
import com.br.kmpdemo.compose.ui.utils.MockData

class FirstScreenshotTest {

    @Composable
    @Preview(showBackground = true)
    fun WeeklyChipListPreview() {
        KmpDemoTheme {
            WeeklyChipList(MockData.getMockWeeklyForecast())
        }
    }

}