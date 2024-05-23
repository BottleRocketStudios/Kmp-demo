package com.br.kmpdemo.compose.ui.forecasts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.br.kmpdemo.compose.resources.theme.Colors
import com.br.kmpdemo.compose.resources.theme.Gradients
import com.br.kmpdemo.compose.resources.theme.KmpDemoTheme
import com.br.kmpdemo.compose.resources.theme.bold
import com.br.kmpdemo.compose.ui.utils.MockData
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ForecastTabs(forecasts: List<ForecastTabState>) {
    var tabIndex by remember { mutableStateOf(0) }
    val tabs = forecasts.map { it.itemTitle }
    val gradient = Brush.horizontalGradient(
        colorStops = arrayOf(
            Pair(0.16F, Gradients.indicatorGradient[0]),
            Pair(0.42F, Gradients.indicatorGradient[1]),
            Pair(1F, Gradients.indicatorGradient[0])
        ),
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(
            tabs = {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        text = {
                            Text(
                                text = title,
                                color = Colors.onPrimary.copy(alpha = if (index == tabIndex) 1.0F else 0.5F),
                                style = MaterialTheme.typography.bodySmall.bold(),
                            )
                        },
                        selected = tabIndex == index,
                        onClick = { tabIndex = index },
                    )
                }
            },
            divider = {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Gradients.indicatorGradient[0])
                )
            },
            selectedTabIndex = tabIndex,
            containerColor = Color.Transparent,
            indicator = { tabPositions ->
                Box(
                    Modifier
                        .tabIndicatorOffset(tabPositions[tabIndex])
                        .height(2.dp)
                        .background(gradient),
                )
            },
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(vertical = 24.dp)
                .background(Color.Transparent),
            content = { forecasts[tabIndex].content() }
        )
    }
}

@Preview()
@Composable
fun ForecastTabsPreview() {
    KmpDemoTheme {
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