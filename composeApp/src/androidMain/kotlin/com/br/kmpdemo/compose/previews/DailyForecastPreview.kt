package com.br.kmpdemo.compose.previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.br.kmpdemo.compose.resources.theme.KmpDemoTheme
import com.br.kmpdemo.compose.ui.forecasts.DailyForecastChip
import com.br.kmpdemo.compose.ui.forecasts.ForecastChip
import com.br.kmpdemo.compose.ui.forecasts.ForecastState
import com.br.kmpdemo.compose.ui.forecasts.WeatherEnum


private val dailyForecast = ForecastState(
    dayTime = "TUE",
    precipProbability = 30,
    temperature = 63,
    weatherIcon = WeatherEnum.DAY_FOG,
)

@Composable
@Preview
fun PreviewTodayDailyForecast() =
    KmpDemoTheme {
        ForecastChip(state = dailyForecast.copy(isNow = true)) { state ->
            DailyForecastChip(state)
        }
    }

@Composable
@Preview
fun PreviewDailyForecast() =
    KmpDemoTheme {
        ForecastChip(state = dailyForecast) { state ->
            DailyForecastChip(state)
        }
    }

@Composable
@Preview
fun PreviewDailyForecastERROR() =
    KmpDemoTheme {
        val errorForecast = ForecastState(weatherIcon = WeatherEnum.HAIL)
        ForecastChip(state = errorForecast) { state ->
            DailyForecastChip(state)
        }
    }
