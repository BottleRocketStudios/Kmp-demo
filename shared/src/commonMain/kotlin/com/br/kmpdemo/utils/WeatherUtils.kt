package com.br.kmpdemo.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object WeatherUtils {
    /// Use this function to translate the API return to a Float for the Barometric Pressure widget
    fun Double?.getPressureFloat(): Float? = this?.let {
        val normalizedValue = ((it - 27.0) / 5.0).toFloat()
        return normalizedValue.coerceIn(0.0F, 1.0F)
    }

    /// Use the next to functions to find the forecast for today or this hour
    fun String.isToday() = toReformattedDate() == Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .toString()
            .toReformattedDate()

    fun String.isThisHour(): Boolean = this.toForecastTimeFormat() == Clock.System.now()
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .toString()
            .toForecastTimeFormat()

    /// Reformats the UTC DateTime to the correct display format for forecasts
    fun String.toForecastTimeFormat(): String? =
        split("T").getOrNull(1)?.split(":")?.let { (hour, _, _) ->
            val formattedHour =
                (if (hour.toInt() > 9) hour else hour.substring(1)).toInt().reformatHour()
            val meridiem = if (hour.toInt() < 12) "AM" else "PM"
            return "$formattedHour $meridiem"
        }

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

    /// Reformats hour to 12-Hour format
    private fun Int.reformatHour() = when (this) {
        0 -> "12"
        in 1..12 -> this
        else -> (this - 12).toString()
    }

    /// Gets the date only from the DateTime returned from the API
    fun String.toReformattedDate(): String? = split("T").getOrNull(0)

    /// Gets the day of the week from the DateTime returned from the API
//    fun String.toDayOfWeek(): String? =
//        try {
//            LocalDate.parse(this).dayOfWeek.toString().take(3)
//        } catch (e: Exception) {
//            null
//        }

    /// Returns ONLY the city name from the location string
//    fun String.extractCityName(): String = split(",")[1].trim()


}