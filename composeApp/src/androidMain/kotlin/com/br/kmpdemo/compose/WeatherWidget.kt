package com.br.kmpdemo.compose

import android.content.Context
import androidx.compose.runtime.Composable
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
import com.br.kmpdemo.models.DailyValues
import com.br.kmpdemo.models.HourlyValues
import com.br.kmpdemo.usecases.forecastusecase.ForecastForCityUseCase
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object WeatherWidget : GlanceAppWidget(), KoinComponent {

    // FIXME - Reconnect to use case
    @Suppress("unused")
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

//            LaunchedEffect(cityPosition) {
//                forecastForCityUseCase.invoke(ForecastForCityUseCase.Request(widgetLocations[cityPosition]))
//                    .onSuccess { response ->
//                        threeHourForecastState.value = ThreeHourForecastState(
//                            currentHourlyValues = response.forecast?.timelines?.hourly?.first()?.hourlyValues,
//                            todayDailyValue = response.forecast?.timelines?.daily?.first()?.dailyValues,
//                            futureHourlyValues1 = response.forecast?.timelines?.hourly?.get(1)?.hourlyValues,
//                            futureHourlyValues2 = response.forecast?.timelines?.hourly?.get(2)?.hourlyValues,
//                            futureHourlyValues3 = response.forecast?.timelines?.hourly?.get(3)?.hourlyValues,
//                        )
//                    }
//            }

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
        modifier = GlanceModifier
            .background(GlanceTheme.colors.background)
            .padding(8.dp)
    ) {
        Row {
            DailyWeatherIcon(state.todayDailyValue)

            Spacer(modifier = GlanceModifier.padding(horizontal = 4.dp))

            Row(
                modifier = GlanceModifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                Column(
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = WeatherWidget.widgetLocations[cityPosition],
                        style = TextStyle(
                            color = GlanceTheme.colors.onBackground,
                            fontSize = TextUnit(22f, TextUnitType.Sp),
                            fontWeight = FontWeight.Bold
                        )
                    )
                    CurrentWeatherDescription(state.todayDailyValue)
                }
            }
        }

        Spacer(modifier = GlanceModifier.padding(vertical = 8.dp))

        Row {
            Column {
                Text(
                    text = (state.currentHourlyValues?.temperature ?: 0.0).toFahrenheit(),
                    style = TextStyle(
                        color = GlanceTheme.colors.primary,
                        fontSize = TextUnit(32f, TextUnitType.Sp),
                        fontWeight = FontWeight.Bold
                    )
                )

                Row {
                    Text(
                        text = (state.todayDailyValue?.temperatureApparentMin ?: 0.0).toFahrenheit(),
                        style = TextStyle(
                            fontSize = TextUnit(11f, TextUnitType.Sp),
                            color = GlanceTheme.colors.onBackground
                        )
                    )
                    Spacer(modifier = GlanceModifier.padding(horizontal = 4.dp))
                    Text(
                        text = (state.todayDailyValue?.temperatureApparentMax ?: 0.0).toFahrenheit(),
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
                FutureHourTemperature(1, state.futureHourlyValues1)
                Spacer(modifier = GlanceModifier.padding(horizontal = 8.dp))
                FutureHourTemperature(2, state.futureHourlyValues2)
                Spacer(modifier = GlanceModifier.padding(horizontal = 8.dp))
                FutureHourTemperature(3, state.futureHourlyValues3)
            }
        }
    }
}

@Composable
private fun FutureHourTemperature(hoursAhead: Long, hourlyValues: HourlyValues?) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = hourlyValues?.temperature.toFahrenheit(),
            style = TextStyle(
                color = GlanceTheme.colors.onBackground,
                fontSize = TextUnit(14f, TextUnitType.Sp),
            )
        )
        Spacer(modifier = GlanceModifier.padding(vertical = 1.dp))
        HourlyWeatherIcon(hourlyValues)
        Spacer(modifier = GlanceModifier.padding(vertical = 1.dp))
        Text(
            text = getHourOfDayInAmPmFormat(hoursAhead),
            style = TextStyle(
                color = GlanceTheme.colors.onBackground,
                fontSize = TextUnit(12f, TextUnitType.Sp),
            )
        )
    }
}

@Composable
fun CurrentWeatherDescription(dailyValues: DailyValues?) {
    Text(
        text = when {
            (dailyValues?.precipitationProbabilityAvg ?: 0.0) >= 50.0 -> "Rainy"
            (dailyValues?.cloudCoverAvg ?: 0.0) >= 25.0 -> "Cloudy"
            else -> "Sunny"
        },
        style = TextStyle(
            color = GlanceTheme.colors.onBackground,
            fontSize = TextUnit(10f, TextUnitType.Sp),
        )
    )
}

@Composable
fun DailyWeatherIcon(dailyValues: DailyValues?) {
//    Image(
//        provider = when {
//            (dailyValues?.precipitationProbabilityAvg ?: 0.0) >= 50.0 -> ImageProvider(R.drawable.rain)
//            (dailyValues?.cloudCoverAvg ?: 0.0) >= 25.0 -> ImageProvider(R.drawable.cloudy)
//            else -> ImageProvider(R.drawable.sunny)
//        },
//        contentDescription = "",
//        colorFilter = ColorFilter.tint(GlanceTheme.colors.tertiary),
//        modifier = GlanceModifier.size(
//            width = 48.dp,
//            height = 48.dp
//        )
//    )
}

@Composable
fun HourlyWeatherIcon(hourlyValues: HourlyValues?) {
//    Image(
//        provider = when {
//            (hourlyValues?.precipitationProbability ?: 0.0) >= 50.0 -> ImageProvider(R.drawable.rain)
//            (hourlyValues?.cloudCover ?: 0.0) >= 25.0 -> ImageProvider(R.drawable.cloudy)
//            else -> ImageProvider(R.drawable.sunny)
//        },
//        contentDescription = "",
//        colorFilter = ColorFilter.tint(GlanceTheme.colors.tertiary),
//        modifier = GlanceModifier.size(
//            width = 18.dp,
//            height = 18.dp
//        )
//    )
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
                if (cityPosition < 5) { // 6 cities in the list.
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

fun Double?.toFahrenheit(): String {
    return "${(((this?.times(9) ?: 0.0) / 5) + 32).toInt()}\u2109"
}

fun getHourOfDayInAmPmFormat(hoursAhead: Long): String {
    val formatter = DateTimeFormatter.ofPattern("ha")
    val nextHour = LocalDateTime.now().plusHours(hoursAhead)
    return formatter.format(nextHour)
}

data class ThreeHourForecastState(
    val currentHourlyValues: HourlyValues? = null,
    val todayDailyValue: DailyValues? = null,
    val futureHourlyValues1: HourlyValues? = null,
    val futureHourlyValues2: HourlyValues? = null,
    val futureHourlyValues3: HourlyValues? = null,
)
