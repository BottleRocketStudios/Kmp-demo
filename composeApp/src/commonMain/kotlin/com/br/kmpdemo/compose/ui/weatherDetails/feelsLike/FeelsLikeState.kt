package com.br.kmpdemo.compose.ui.weatherDetails.feelsLike

import com.br.kmpdemo.compose.resources.SharedRes
import dev.icerock.moko.resources.StringResource

data class FeelsLikeState(
    val temperatureApparent: Double? = null,
    val currentTemp: Double? = null,
    val description: StringResource = temperatureApparent.getFeelsLikeDescription(currentTemp),
)

fun Double?.getFeelsLikeDescription(currentTemp: Double?): StringResource {
    return if (this != null && currentTemp != null) {
        val high = currentTemp.plus(5.0)
        val low = currentTemp.minus(5.0)
        when {
            this in low..high -> SharedRes.strings.feels_like_similar
            this > high -> SharedRes.strings.feels_like_higher
            this < low -> SharedRes.strings.feels_like_lower
            else -> SharedRes.strings.feels_like_unknown
        }
    } else {
        SharedRes.strings.feels_like_unknown
    }
}