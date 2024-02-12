package com.br.kmpdemo.utils

import androidx.compose.ui.graphics.Color
import platform.UIKit.UIColor

fun Color.toIosColor(): UIColor {
    return UIColor(
        red = red.toDouble(),
        green = green.toDouble(),
        blue = blue.toDouble(),
        alpha = alpha.toDouble()
    )
}