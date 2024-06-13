package com.br.kmpdemo.compose.widget.forecast

import com.br.kmpdemo.models.Forecast
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun Forecast?.toWidgetState() = ForecastWidgetState(
    location = "New York City",
    currentTemperature = this?.timelines?.hourly?.first()?.hourlyValues?.temperature ?: 0.0,
    dailyTemperatureApparentMin = this?.timelines?.daily?.first()?.dailyValues?.temperatureApparentMin ?: 0.0,
    dailyTemperatureApparentMax = this?.timelines?.daily?.first()?.dailyValues?.temperatureApparentMax ?: 0.0,
    dailyPrecipitationProbabilityAvg = this?.timelines?.daily?.first()?.dailyValues?.precipitationProbabilityAvg ?: 0.0,
    dailyCloudCoverAvg = this?.timelines?.daily?.first()?.dailyValues?.cloudCoverAvg ?: 0.0,
    futureTemperature1 = this?.timelines?.hourly?.get(1)?.hourlyValues?.temperature ?: 0.0,
    futurePrecipitationProbability1 = this?.timelines?.hourly?.get(1)?.hourlyValues?.precipitationProbability ?: 0.0,
    futureCloudCover1 = this?.timelines?.hourly?.get(1)?.hourlyValues?.cloudCover ?: 0.0,
    futureTemperature2 = this?.timelines?.hourly?.get(2)?.hourlyValues?.temperature ?: 0.0,
    futurePrecipitationProbability2 = this?.timelines?.hourly?.get(2)?.hourlyValues?.precipitationProbability ?: 0.0,
    futureCloudCover2 = this?.timelines?.hourly?.get(2)?.hourlyValues?.cloudCover ?: 0.0,
    futureTemperature3 = this?.timelines?.hourly?.get(3)?.hourlyValues?.temperature ?: 0.0,
    futurePrecipitationProbability3 = this?.timelines?.hourly?.get(3)?.hourlyValues?.precipitationProbability ?: 0.0,
    futureCloudCover3 = this?.timelines?.hourly?.get(3)?.hourlyValues?.cloudCover ?: 0.0,
)

fun Double?.toFahrenheit(): String {
    return "${(((this?.times(9) ?: 0.0) / 5) + 32).toInt()}\u2109"
}

fun getHourOfDayInAmPmFormat(hoursAhead: Long): String {
    val formatter = DateTimeFormatter.ofPattern("ha")
    val nextHour = LocalDateTime.now().plusHours(hoursAhead)
    return formatter.format(nextHour)
}