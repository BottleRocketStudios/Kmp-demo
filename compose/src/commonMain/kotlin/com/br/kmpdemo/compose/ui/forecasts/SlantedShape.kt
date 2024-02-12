package com.br.kmpdemo.compose.ui.forecasts

import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class SlantedShape: Shape {
    override fun createOutline(size: Size, layoutDirection: LayoutDirection, density: Density) =
        Outline.Generic(Path().apply {
            val cornerRadius = 90f
            val topOffset = 20f
            moveTo(0f, cornerRadius + topOffset)
            quadraticBezierTo(topOffset, topOffset, cornerRadius + topOffset, topOffset)
            lineTo(116f, topOffset)
            lineTo(size.width - cornerRadius, size.height / 3)
            quadraticBezierTo(size.width, (size.height / 3) + 24f, size.width, size.height / 2)
            lineTo(size.width, size.height - cornerRadius)
            quadraticBezierTo(size.width, size.height, size.width - cornerRadius, size.height)
            lineTo(cornerRadius, size.height)
            quadraticBezierTo(20f, size.height, 0f, size.height - cornerRadius)
            close()
        })
}