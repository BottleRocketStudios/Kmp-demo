package com.br.kmpdemo.compose.ui.shared

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.br.kmpdemo.compose.resources.theme.Dimens
import com.br.kmpdemo.compose.resources.theme.Gradients

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KMMDemoSlider(
    boxModifier: Modifier = Modifier
        .padding(vertical = Dimens.grid_2)
        .clip(RoundedCornerShape(Dimens.grid_3))
        .fillMaxWidth()
        .height(Dimens.grid_0_5)
        .background(Brush.horizontalGradient(Gradients.sliderTrackGradient)),
    initValue: Float,
    valueRange: ClosedFloatingPointRange<Float>,
    onValueChange: (Float) -> Unit = {},
) {
    Box(modifier = boxModifier) {
        Slider(
            value = initValue,
            valueRange = valueRange,
            onValueChange = onValueChange,
            colors = SliderDefaults.colors(
                thumbColor = Color.White,
                activeTrackColor = Color.Transparent,
                inactiveTrackColor = Color.Transparent
            ),
            modifier = Modifier.matchParentSize(),
            thumb = {
                Box(
                    Modifier
                        .padding(Dimens.grid_1)
                        .size(Dimens.grid_0_5)
                        .background(Color.White, CircleShape)
                )
            }
        )
    }
}