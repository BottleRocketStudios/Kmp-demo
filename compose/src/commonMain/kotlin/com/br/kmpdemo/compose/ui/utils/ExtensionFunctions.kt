package com.br.kmpdemo.compose.ui.utils

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.unit.dp

fun Modifier.gradientCardModifier(
    sideGradientColors: List<Color>,
    topGradientColors: List<Pair<Float, Color>>
) = fillMaxSize()
    .drawWithContent {
        drawContent()
        drawRoundRect(
            color = Color.Black.copy(1.0f),
            cornerRadius = CornerRadius(x = 9f, y = 9f),
            topLeft = Offset(
                x = (this.size.width / 2 - ((this.size.width * 0.17f) / 2)),
                y = this.size.width * 0.035f
            ),
            size = Size(this.size.width * 0.17f, 18f),
            style = Stroke(width = 1.dp.toPx())
        )
        drawRoundRect(
            color = Color.Black.copy(0.3f),
            cornerRadius = CornerRadius(x = 9f, y = 9f),
            topLeft = Offset(
                x = (this.size.width / 2 - ((this.size.width * 0.17f) / 2)),
                y = this.size.width * 0.035f
            ),
            size = Size(this.size.width * 0.17f, 18f),
            style = Fill
        )
    }
    .drawBehind {
        inset(0f, 0f, (-1 * this.size.width), 0f) {
            drawCircle(brush = Brush.radialGradient(sideGradientColors))
        }
        inset(0f, this.size.height * -1.19f, 0f, 0f) {
            drawCircle(Brush.radialGradient(*topGradientColors.toTypedArray()))
        }
    }