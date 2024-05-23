package com.br.kmpdemo.compose.ui.shared

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.br.kmpdemo.compose.resources.theme.Dimens
import com.br.kmpdemo.compose.resources.theme.Gradients
import com.br.kmpdemo.compose.resources.theme.largeCardCorner
import com.br.kmpdemo.compose.resources.theme.outline
import com.br.kmpdemo.compose.ui.utils.SHEET_MAX_HEIGHT
import com.br.kmpdemo.compose.ui.utils.SHEET_MIN_HEIGHT
import com.br.kmpdemo.compose.ui.utils.gradientCardModifier

@Composable
fun GradientCard(
    isExpanded: Boolean,
    content: @Composable () -> Unit
) {
    OutlinedCard(
        shape = RoundedCornerShape(largeCardCorner),
        modifier = Modifier
            .heightIn(
                min = SHEET_MIN_HEIGHT.dp,
                max = SHEET_MAX_HEIGHT.dp,
                ) // Prevents sheet from expanding too much
            .fillMaxSize()
            .gradientCardModifier(
                sideGradientColors = Gradients.sideEclipseGradient,
                topGradientColors = Gradients.topEclipseGradient,
            ),
        colors = CardDefaults.outlinedCardColors(
            containerColor = Color.Transparent,
            contentColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Transparent
        ),
        border = BorderStroke(
            Dimens.grid_0_25,
            Brush.verticalGradient(
                colors = listOf(
                    outline.copy(alpha = if (isExpanded) 0f else 1f),
                    Color.Transparent,
                ),
                startY = 0f,
                endY = Dimens.grid_24.value
            )
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(
                    top = Dimens.grid_1_75,
                    bottom = Dimens.grid_1,
                )
        ) {
            content()
        }
    }
}