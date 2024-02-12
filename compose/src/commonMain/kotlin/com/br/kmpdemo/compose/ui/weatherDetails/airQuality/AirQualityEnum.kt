package com.br.kmpdemo.compose.ui.weatherDetails.airQuality

import com.br.kmpdemo.compose.resources.SharedRes
import dev.icerock.moko.resources.StringResource

enum class AirQualityEnum(val level: Float, val quality: StringResource) {
    GREEN(0.1f, SharedRes.strings.aqi_1), // 0-50
    YELLOW(0.2f, SharedRes.strings.aqi_2), // 51-100
    ORANGE(0.3f, SharedRes.strings.aqi_3),// 101-150
    RED(0.4f, SharedRes.strings.aqi_4), // 151-200
    PURPLE(0.5f, SharedRes.strings.aqi_5), // 201-300
    MAROON(0.6f, SharedRes.strings.aqi_6), // Over 300
    UNKNOWN(0.0f, SharedRes.strings.unknown_aqi)
}