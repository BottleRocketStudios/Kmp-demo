package com.br.kmpdemo.compose.ui.weatherDetails.wind

import com.br.kmpdemo.compose.resources.SharedRes
import dev.icerock.moko.resources.StringResource

/**
 * "degrees" slightly adjusted from normal to better match up with
 * Cardinal and Intermediate Lines.
 */
enum class WindDirectionEnum(val direction: StringResource, val degrees: Double) {
    N(SharedRes.strings.direction_n, 0.0),
    NNE(SharedRes.strings.direction_nne, 29.5),
    NE(SharedRes.strings.direction_ne, 45.0),
    ENE(SharedRes.strings.direction_ene, 60.5),
    E(SharedRes.strings.direction_e, 90.0),
    ESE(SharedRes.strings.direction_ese, 119.5),
    SE(SharedRes.strings.direction_se, 135.0),
    SSE(SharedRes.strings.direction_sse, 150.5),
    S(SharedRes.strings.direction_s, 180.0),
    SSW(SharedRes.strings.direction_ssw, 209.5),
    SW(SharedRes.strings.direction_sw, 226.0),
    WSW(SharedRes.strings.direction_wsw, 240.5),
    W(SharedRes.strings.direction_w, 270.0),
    WNW(SharedRes.strings.direction_wnw, 299.5),
    NW(SharedRes.strings.direction_nw, 316.0),
    NNW(SharedRes.strings.direction_nnw, 330.5),
    UNKNOWN(SharedRes.strings.direction_unknown, 0.0)
}

fun Double?.getWindDirection(): WindDirectionEnum {
    return when (this ?: Double.MIN_VALUE) {
        0.0 -> WindDirectionEnum.N
        in 0.1..30.0 -> WindDirectionEnum.NNE
        in 30.0..45.0 -> WindDirectionEnum.NE
        in 45.0..60.5 -> WindDirectionEnum.ENE
        in 60.5..90.0 -> WindDirectionEnum.E
        in 90.0..119.5 -> WindDirectionEnum.ESE
        in 119.5..135.0 -> WindDirectionEnum.SE
        in 135.0..150.5 -> WindDirectionEnum.SSE
        in 150.5..180.0 -> WindDirectionEnum.S
        in 180.0..209.5 -> WindDirectionEnum.SSW
        in 209.5..226.0 -> WindDirectionEnum.SW
        in 226.0..240.5 -> WindDirectionEnum.WSW
        in 240.5..270.0 -> WindDirectionEnum.W
        in 270.0..299.5 -> WindDirectionEnum.WNW
        in 299.5..316.0 -> WindDirectionEnum.NW
        in 316.0..330.5 -> WindDirectionEnum.NNW
        else -> WindDirectionEnum.UNKNOWN
    }
}