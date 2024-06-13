package com.br.kmpdemo.compose.widget.forecast

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.preview.ExperimentalGlancePreviewApi
import androidx.glance.preview.Preview
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import com.br.kmpdemo.compose.R
import com.br.kmpdemo.compose.previews.previewdata.forecastWidgetPreviewData
import com.br.kmpdemo.compose.resources.theme.Dimens
import com.br.kmpdemo.compose.resources.theme.KmpDemoGlanceColors
import com.br.kmpdemo.repositories.WeatherRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

/**
 * A widget that displays the daily weather forecast.
 */
object WeatherWidget : GlanceAppWidget(), KoinComponent {

    private val weatherRepo: WeatherRepository by inject()

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val widgetState = remember { mutableStateOf<ForecastWidgetState?>(null) }

            LaunchedEffect(true) {
                weatherRepo.getDailyForecast(location = "New York City", units = "imperial")
                    .onSuccess { forecast ->
                        widgetState.value = forecast.toWidgetState()
                    }
                    .onFailure {
                        // Show the preview data when the request fails.
                        widgetState.value = forecastWidgetPreviewData
                    }
            }

            GlanceTheme(colors = KmpDemoGlanceColors.colors) {
                Column(
                    modifier = GlanceModifier.fillMaxSize()
                ) {
                    widgetState.value?.let { ForecastWidget(it) }
                }
            }
        }
    }
}

/**
 * Composable function that creates a Forecast Widget.
 *
 * @param state The [ForecastWidgetState] object containing the data for the widget.
 */
@Composable
fun ForecastWidget(state: ForecastWidgetState) {
    Column(
        modifier = GlanceModifier
            .padding(Dimens.grid_1)
            .fillMaxWidth()
            .background(GlanceTheme.colors.background),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ForecastWidgetHeader(
            state.location,
            state.dailyPrecipitationProbabilityAvg,
            state.dailyCloudCoverAvg
        )

        Spacer(modifier = GlanceModifier.padding(vertical = Dimens.grid_1))

        Row {
            ForecastWidgetCurrentTemperature(state.currentTemperature, state.dailyTemperatureApparentMin, state.dailyTemperatureApparentMax)

            Spacer(modifier = GlanceModifier.padding(horizontal = Dimens.grid_1_5))

            FutureHourTemperatures(state)
        }

        Spacer(modifier = GlanceModifier.height(Dimens.grid_2))
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Preview(widthDp = 225, heightDp = 165)
@Composable
fun ForecastWidgetPreview() {
    GlanceTheme(colors = KmpDemoGlanceColors.colors) {
        ForecastWidget(
            forecastWidgetPreviewData
        )
    }
}

/**
 * Composable function to display the header of the Forecast widget.
 *
 * @param location The location for which the forecast is displayed.
 * @param dailyPrecipitationProbabilityAvg The average daily precipitation probability.
 * @param dailyCloudCoverAvg The average daily cloud cover.
 */
@Composable
private fun ForecastWidgetHeader(
    location: String,
    dailyPrecipitationProbabilityAvg: Double,
    dailyCloudCoverAvg: Double
) {
    Row(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WeatherIcon(
            precipitationProbabilityAvg = dailyPrecipitationProbabilityAvg,
            cloudCoverAvg = dailyCloudCoverAvg,
            isTitleImage = true
        )

        Spacer(modifier = GlanceModifier.padding(horizontal = Dimens.grid_0_5))

        Row {
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = location,
                    style = TextStyle(
                        color = GlanceTheme.colors.inverseOnSurface,
                        fontSize = TextUnit(22f, TextUnitType.Sp),
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = when {
                        (dailyPrecipitationProbabilityAvg) >= 50.0 -> "Rainy"
                        (dailyCloudCoverAvg) >= 25.0 -> "Cloudy"
                        else -> "Sunny"
                    },
                    style = TextStyle(
                        color = GlanceTheme.colors.onBackground,
                        fontSize = TextUnit(10f, TextUnitType.Sp),
                    )
                )
            }
        }
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Preview
@Composable
private fun ForecastWidgetHeaderPreview() {
    GlanceTheme(colors = KmpDemoGlanceColors.colors) {
        ForecastWidgetHeader(
            forecastWidgetPreviewData.location,
            forecastWidgetPreviewData.dailyPrecipitationProbabilityAvg,
            forecastWidgetPreviewData.dailyCloudCoverAvg
        )
    }
}

/**
 * Composable function for displaying the current temperature, minimum and maximum apparent temperatures in a column.
 *
 * @param currentTemperature The current temperature value.
 * @param dailyTemperatureApparentMin The minimum apparent temperature value.
 * @param dailyTemperatureApparentMax The maximum apparent temperature value.
 */
@Composable
private fun ForecastWidgetCurrentTemperature(
    currentTemperature: Double,
    dailyTemperatureApparentMin: Double,
    dailyTemperatureApparentMax: Double,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = (currentTemperature).toFahrenheit(),
            style = TextStyle(
                color = GlanceTheme.colors.secondary,
                fontSize = TextUnit(32f, TextUnitType.Sp),
                fontWeight = FontWeight.Bold
            )
        )

        Row {
            Text(
                text = (dailyTemperatureApparentMin).toFahrenheit(),
                style = TextStyle(
                    fontSize = TextUnit(11f, TextUnitType.Sp),
                    color = GlanceTheme.colors.inverseOnSurface
                )
            )

            Spacer(modifier = GlanceModifier.padding(horizontal = Dimens.grid_0_5))

            Text(
                text = (dailyTemperatureApparentMax).toFahrenheit(),
                style = TextStyle(
                    fontSize = TextUnit(11f, TextUnitType.Sp),
                    color = GlanceTheme.colors.inverseOnSurface
                )
            )
        }
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Preview
@Composable
private fun ForecastWidgetCurrentTemperaturePreview() {
    GlanceTheme(colors = KmpDemoGlanceColors.colors) {
        ForecastWidgetCurrentTemperature(
            forecastWidgetPreviewData.currentTemperature,
            forecastWidgetPreviewData.dailyTemperatureApparentMin,
            forecastWidgetPreviewData.dailyTemperatureApparentMax
        )
    }
}

/**
 * Composable function to display future hour temperatures.
 *
 * @param state The state object containing the forecast data.
 */
@Composable
private fun FutureHourTemperatures(state: ForecastWidgetState) {
    Row {
        FutureHourTemperature(1, state.futureTemperature1, state.futurePrecipitationProbability1, state.futureCloudCover1)
        Spacer(modifier = GlanceModifier.padding(horizontal = Dimens.grid_0_5))
        FutureHourTemperature(2, state.futureTemperature2, state.futurePrecipitationProbability2, state.futureCloudCover2)
        Spacer(modifier = GlanceModifier.padding(horizontal = Dimens.grid_0_5))
        FutureHourTemperature(3, state.futureTemperature3, state.futurePrecipitationProbability3, state.futureCloudCover3)
    }

}

@OptIn(ExperimentalGlancePreviewApi::class)
@Preview
@Composable
private fun FutureHourTemperaturesPreview() {
    GlanceTheme(colors = KmpDemoGlanceColors.colors) {
        FutureHourTemperatures(
            forecastWidgetPreviewData
        )
    }
}

/**
 * Composable to display the temperature, precipitation probability, cloud cover, and hour of day for a future time.
 *
 * @param hoursAhead The number of hours in the future for which to display the weather data.
 * @param futureTemperature The temperature in degrees Fahrenheit.
 * @param futurePrecipitationProbability The probability of precipitation as a percentage.
 * @param futureCloudCover The cloud cover as a percentage.
 */
@Composable
private fun FutureHourTemperature(
    hoursAhead: Long,
    futureTemperature: Double?,
    futurePrecipitationProbability: Double,
    futureCloudCover: Double
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = futureTemperature.toFahrenheit(),
            style = TextStyle(
                color = GlanceTheme.colors.onBackground,
                fontSize = TextUnit(14f, TextUnitType.Sp),
            ),
        )

        Spacer(modifier = GlanceModifier.padding(vertical = Dimens.grid_0_25))

        WeatherIcon(futurePrecipitationProbability, futureCloudCover)

        Spacer(modifier = GlanceModifier.padding(vertical = Dimens.grid_0_25))

        Text(
            text = getHourOfDayInAmPmFormat(hoursAhead),
            style = TextStyle(
                color = GlanceTheme.colors.onBackground,
                fontSize = TextUnit(12f, TextUnitType.Sp),
            )
        )
    }
}

@OptIn(ExperimentalGlancePreviewApi::class)
@Preview()
@Composable
fun FutureHourTemperaturePreview() {
    GlanceTheme(colors = KmpDemoGlanceColors.colors) {
        FutureHourTemperature(
            2,
            forecastWidgetPreviewData.futureTemperature1,
            forecastWidgetPreviewData.futurePrecipitationProbability1,
            forecastWidgetPreviewData.futureCloudCover1
        )
    }
}

/**
 * Composable function to display a weather icon based on precipitation probability and cloud cover.
 *
 * @param precipitationProbabilityAvg The average precipitation probability.
 * @param cloudCoverAvg The average cloud cover.
 * @param isTitleImage Whether the icon is used as a title image.
 */
@Composable
fun WeatherIcon(
    precipitationProbabilityAvg: Double,
    cloudCoverAvg: Double,
    isTitleImage: Boolean = false
) {
    Image(
        provider = when {
            (precipitationProbabilityAvg) >= 50.0 -> ImageProvider(R.drawable.rain_light)
            (cloudCoverAvg) >= 25.0 -> ImageProvider(R.drawable.overcast_cloudy)
            else -> ImageProvider(R.drawable.sunny)
        },
        contentDescription = "",
        modifier = GlanceModifier.size(if (isTitleImage) Dimens.grid_6 else Dimens.grid_3)
    )
}

/**
 * The `WeatherWidgetReceiver` class is a subclass of `GlanceAppWidgetReceiver`.
 * It is responsible for providing the `GlanceAppWidget` instance that will be used to render the weather widget.
 *
 */
class WeatherWidgetReceiver : GlanceAppWidgetReceiver() {
    override val glanceAppWidget: GlanceAppWidget
        get() = WeatherWidget
}