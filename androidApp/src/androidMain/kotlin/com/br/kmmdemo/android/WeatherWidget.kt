package com.br.kmmdemo.android

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.glance.Button
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.action.ActionParameters
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.action.ActionCallback
import androidx.glance.appwidget.action.actionRunCallback
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.state.updateAppWidgetState
import androidx.glance.background
import androidx.glance.currentState
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.br.kmmdemo.usecases.forecastusecase.ForecastForCityUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


object WeatherWidget : GlanceAppWidget(), KoinComponent {

    private val forecastForCityUseCase: ForecastForCityUseCase by inject()

    val cityKey = intPreferencesKey("cityKey")

    val widgetLocations = listOf(
        "New York City",
        "Addison",
        "Los Angeles",
        "Chicago",
        "Houston",
        "Philadelphia"
    )

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        provideContent {
            val cityPosition = currentState(key = cityKey) ?: 0

            val threeHourForecastState = remember { mutableStateOf(ThreeHourForecastState()) }

            LaunchedEffect(cityPosition) {
                forecastForCityUseCase.invoke(ForecastForCityUseCase.Request(widgetLocations[cityPosition]))
                    .onSuccess { response ->
                        threeHourForecastState.value = ThreeHourForecastState(
                            currentTemp = response.forecast?.timelines?.hourly?.first()?.hourlyValues?.temperature ?: 0.0,
                            todayHigh = response.forecast?.timelines?.daily?.first()?.dailyValues?.temperatureApparentMax ?: 0.0,
                            todayLow = response.forecast?.timelines?.daily?.first()?.dailyValues?.temperatureApparentMin ?: 0.0,
                            futureHour1Average = response.forecast?.timelines?.hourly?.get(1)?.hourlyValues?.temperature ?: 0.0,
                            futureHour2Average = response.forecast?.timelines?.hourly?.get(1)?.hourlyValues?.temperature ?: 0.0,
                            futureHour3Average = response.forecast?.timelines?.hourly?.get(1)?.hourlyValues?.temperature ?: 0.0,
                        )
                    }
            }

            GlanceTheme {
                Column(
                    modifier = GlanceModifier.padding(8.dp).fillMaxSize()
                ) {
                    ThreeDayForecast(cityPosition, threeHourForecastState.value)

                    Spacer(modifier = GlanceModifier.height(4.dp))

                    Row(
                        modifier = GlanceModifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Button(
                            text = "Change City", onClick = actionRunCallback(ChangeCityCallback::class.java)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ThreeDayForecast(cityPosition: Int, state: ThreeHourForecastState) {
    Column(
        modifier = GlanceModifier.background(GlanceTheme.colors.background).padding(8.dp)
    ) {
        Row(
            modifier = GlanceModifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = WeatherWidget.widgetLocations[cityPosition],
                style = TextStyle(
                    color = GlanceTheme.colors.onBackground,
                    fontSize = TextUnit(18f, TextUnitType.Sp),
                )
            )
        }

        Spacer(modifier = GlanceModifier.padding(vertical = 8.dp))

        Row {
            Column {
                Text(
                    text = state.currentTemp.toFahrenheit(),
                    style = TextStyle(
                        color = GlanceTheme.colors.secondary,
                        fontSize = TextUnit(32f, TextUnitType.Sp),
                        fontWeight = FontWeight.Bold
                    )
                )

                Row {
                    Text(
                        text = state.todayLow.toFahrenheit(),
                        style = TextStyle(
                            fontSize = TextUnit(11f, TextUnitType.Sp),
                            color = GlanceTheme.colors.onBackground
                        )
                    )
                    Spacer(modifier = GlanceModifier.padding(horizontal = 4.dp))
                    Text(
                        text = state.todayHigh.toFahrenheit(),
                        style = TextStyle(
                            fontSize = TextUnit(11f, TextUnitType.Sp),
                            color = GlanceTheme.colors.onBackground
                        )
                    )
                }
            }

            Spacer(modifier = GlanceModifier.padding(horizontal = 12.dp))

            Row(
                modifier = GlanceModifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalAlignment = Alignment.CenterVertically
            ) {
                FutureHourTemperature(1, state.futureHour1Average)
                Spacer(modifier = GlanceModifier.padding(horizontal = 8.dp))
                FutureHourTemperature(2, state.futureHour2Average)
                Spacer(modifier = GlanceModifier.padding(horizontal = 8.dp))
                FutureHourTemperature(3, state.futureHour3Average)
            }
        }
    }
}

@Composable
private fun FutureHourTemperature(hoursAhead: Long, futureHourAverage: Double) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = futureHourAverage.toFahrenheit(),
            style = TextStyle(
                color = GlanceTheme.colors.onBackground,
                fontSize = TextUnit(12f, TextUnitType.Sp),
            )
        )
        Spacer(modifier = GlanceModifier.padding(vertical = 6.dp))
        Text(
            getHourOfDayInAmPmFormat(hoursAhead),
            style = TextStyle(
                color = GlanceTheme.colors.onBackground,
                fontSize = TextUnit(12f, TextUnitType.Sp),
            )
        )
    }
}

class WeatherWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = WeatherWidget

}

object ChangeCityCallback : ActionCallback {
    override suspend fun onAction(context: Context, glanceId: GlanceId, parameters: ActionParameters) {
        updateAppWidgetState(context, glanceId) { mutablePreferences ->
            val cityPosition = mutablePreferences[WeatherWidget.cityKey]
            if (cityPosition != null) {
                if (cityPosition < 6) { // 6 cities in the list.
                    mutablePreferences[WeatherWidget.cityKey] = cityPosition + 1
                } else {
                    mutablePreferences[WeatherWidget.cityKey] = 0
                }
            } else {
                mutablePreferences[WeatherWidget.cityKey] = 0
            }
            WeatherWidget.update(context, glanceId)
        }
    }
}

fun Double.toFahrenheit(): String {
    return "${((this * 9 / 5) + 32).toInt()}\u2109"
}

fun getHourOfDayInAmPmFormat(hoursAhead: Long): String {
    val formatter = DateTimeFormatter.ofPattern("ha")
    val nextHour = LocalDateTime.now().plusHours(hoursAhead)
    return formatter.format(nextHour)
}

data class ThreeHourForecastState(
    val cityPosition: Int = 0,
    val currentTemp: Double = 0.0,
    val todayHigh: Double = 0.0,
    val todayLow: Double = 0.0,
    val futureHour1Average: Double = 0.0,
    val futureHour2Average: Double = 0.0,
    val futureHour3Average: Double = 0.0,
)