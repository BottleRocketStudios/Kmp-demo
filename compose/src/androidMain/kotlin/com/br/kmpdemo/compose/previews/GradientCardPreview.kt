package com.br.kmpdemo.compose.previews

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.br.kmpdemo.compose.resources.theme.KMPDemoTheme
import com.br.kmpdemo.compose.ui.shared.GradientCard

@Preview
@Composable
fun GradientCardPreview() {
    KMPDemoTheme {
        Surface {
            GradientCard(isExpanded = true) {
                ForecastTabsPreview()
            }
        }
    }
}
