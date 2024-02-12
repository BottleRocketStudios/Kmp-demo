package com.br.kmpdemo.compose.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.br.kmpdemo.compose.previews.utils.MockData
import com.br.kmpdemo.compose.resources.theme.KMPDemoTheme
import com.br.kmpdemo.compose.ui.forecasts.ForecastTabState
import com.br.kmpdemo.compose.ui.forecasts.ForecastTabs
import com.br.kmpdemo.compose.ui.forecasts.HourlyChipList
import com.br.kmpdemo.compose.ui.forecasts.WeeklyChipList

@Preview
@Composable
fun ForecastTabsPreview() {
    KMPDemoTheme {
        ForecastTabs(listOf(
            ForecastTabState(
                itemTitle = "Hourly Forecast",
                content = { HourlyChipList(MockData.getMockHourlyForecast()) }
            ),
            ForecastTabState(
                itemTitle = "Weekly Forecast",
                content = { WeeklyChipList(MockData.getMockWeeklyForecast()) }
            )
        ))
    }
}