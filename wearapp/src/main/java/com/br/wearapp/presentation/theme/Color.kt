package com.br.wearapp.presentation.theme

import androidx.compose.ui.graphics.Color
import androidx.wear.compose.material.Colors

val black = Color(0xFF000000)
val white = Color(0xFFFFFFFF)
val grey_60_Opacity = Color(0x993C3C43)
val grey_30_Opacity = Color(0x4D3C3C43)
val grey_18_Opacity = Color(0x2E3C3C43)
val bluishGrey_60_Opacity = Color(0x99EBEBF5)
val bluishGrey_30_Opacity = Color(0x4DEBEBF5)
val bluishGrey_18_Opacity = Color(0x2EEBEBF5)
val purple = Color(0xFF343069)
val containerPurple = Color(0xFF282453)
val selectedPurple = Color(0xFF48319D)
val darkBlue = Color(0xFF1F1D47)
val brightViolet = Color(0xFFC427FB)
val palePurple = Color(0xFFE0D9FF)
val lightBlue = Color(0xFF40CBD8)
val outline = Color(0xFF6C4ACE)
val outlineVariant = Color(0xFF5747A5)

val WearAppColorPalette: Colors = Colors(
    primary = purple,
    onPrimary = white,
    secondary = darkBlue,
    onSecondary = bluishGrey_60_Opacity,
    background = palePurple,
    onBackground = white,
    surface = purple,
    onSurface = white,
    onSurfaceVariant = white,
)