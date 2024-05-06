package com.br.kmpdemo.compose.ui.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object WeatherUtils {
    /// Use this function to translate the API return to a Float for the Barometric Pressure widget
    fun Double?.getPressureFloat(): Float? = this?.let {
        val normalizedValue = ((it - 27.0) / 5.0).toFloat()
        return normalizedValue.coerceIn(0.0F, 1.0F)
    }

    /// Reformats hour to 12-Hour format
    private fun Int.twelveHourClock() = when (this) {
        0 -> 12
        in 1..12 -> this
        else -> (this - 12)
    }

    fun LocalTime.toForecastTimeFormat() =
        "${hour.twelveHourClock()} ${if (hour < 12) "AM" else "PM"}"


//    FIXME - Try to avoid the parse from string and then reformat work. Just keep times in classes until needed for display
    /// Reformats the UTC DateTime to the user's time zone
    fun String?.convertUtcTimeForSunriseSunset(): String =
        this?.let {
            val localDateTime = Instant.parse(this).toLocalDateTime(TimeZone.currentSystemDefault())

            // Extract the hour and minute in user's time zone
            val hour = localDateTime.hour
            val minute = localDateTime.minute

            val formattedMinute = if (minute < 10) "0$minute" else minute
            return "$hour:$formattedMinute"
        } ?: ""

}