package com.br.kmpdemo

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.br.kmpdemo.compose.resources.SharedRes
import dev.icerock.moko.resources.compose.fontFamilyResource
import dev.icerock.moko.resources.compose.stringResource

@Composable
fun MapScreen() {
    Surface(
        color = Color.Yellow,
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ) {
        Text(
            stringResource(SharedRes.strings.map),
            color = Color.Green,
            fontSize = 24.sp,
            fontFamily = fontFamilyResource(SharedRes.fonts.Montserrat.medium),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 24.dp),
        )
    }
}