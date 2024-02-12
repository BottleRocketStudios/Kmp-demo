package com.br.kmpdemo.compose.ui.home

import WeatherDetails
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.br.kmpdemo.compose.ui.forecasts.ForecastTabState
import com.br.kmpdemo.compose.ui.forecasts.ForecastTabs
import com.br.kmpdemo.compose.ui.forecasts.HourlyChipList
import com.br.kmpdemo.compose.ui.forecasts.WeeklyChipList
import com.br.kmpdemo.compose.ui.shared.GradientCard
import com.br.kmpdemo.compose.resources.SharedRes
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun HomeBottomSheet(state: HomeState, sheetState: Boolean) {
    with(state) {
        GradientCard(sheetState) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                content = {
                    ForecastTabs(
                        listOf(
                            ForecastTabState(
                                itemTitle = stringResource(SharedRes.strings.hourly_forecast),
                                content = { HourlyChipList(hourlyForecasts) }
                            ),
                            ForecastTabState(
                                itemTitle = stringResource(SharedRes.strings.weekly_forecast),
                                content = { WeeklyChipList(weeklyForecasts) }
                            )
                        )
                    )
                    WeatherDetails(state.weatherDetailsState)
                }
            )
        }
    }
}