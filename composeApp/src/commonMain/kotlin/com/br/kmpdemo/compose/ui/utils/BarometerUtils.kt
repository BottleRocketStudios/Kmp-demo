package com.br.kmpdemo.compose.ui.utils

import androidx.compose.ui.geometry.Offset
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

fun Offset.getBarometerLineOffset(pressure: Float, radius: Float): Offset {
    val radian = (pressure * 360f) * (PI / 180).toFloat()
    return Offset(
        x = x + radius * cos(radian),
        y = y + radius * sin(radian)
    )
}