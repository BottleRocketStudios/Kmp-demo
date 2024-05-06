package com.br.kmpdemo.compose.ui.weatherDetails.uvIndex

import com.br.kmpdemo.compose.resources.SharedRes
import dev.icerock.moko.resources.StringResource

enum class UVIndexEnum(
    val sliderValue: Float,
    val indexValue: StringResource,
    val description: StringResource,
) {
    LOW(0.1f, SharedRes.strings.uv_index_1, SharedRes.strings.uv_index_low),
    MODERATE(0.2f, SharedRes.strings.uv_index_2, SharedRes.strings.uv_index_mod),
    HIGH(0.3f, SharedRes.strings.uv_index_3, SharedRes.strings.uv_index_high),
    VERY_HIGH(0.4f, SharedRes.strings.uv_index_4, SharedRes.strings.uv_index_very_high),
    EXTREME(0.5f, SharedRes.strings.uv_index_5, SharedRes.strings.uv_index_extreme),
    UNKNOWN(0f, SharedRes.strings.uv_index_unknown, SharedRes.strings.unknown_uv)
}

fun Double?.uvIndexEnum() =
    when (this ?: Double.MIN_VALUE) {
        in 0.0..2.9 -> UVIndexEnum.LOW
        in 3.0..5.9 -> UVIndexEnum.MODERATE
        in 6.0..7.9 -> UVIndexEnum.HIGH
        in 8.0..10.9 -> UVIndexEnum.VERY_HIGH
        in 11.0..Double.MAX_VALUE -> UVIndexEnum.EXTREME
        else -> UVIndexEnum.UNKNOWN
    }