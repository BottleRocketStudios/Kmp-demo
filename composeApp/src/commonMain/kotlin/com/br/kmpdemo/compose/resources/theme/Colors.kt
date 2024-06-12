package com.br.kmpdemo.compose.resources.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

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

//// Gradient Colors ////
val lightPurple = Color(0xFF5936B4)
val darkPurple = Color(0xFF362A84)
val sliderBlue = Color(0xFF3858B2)
val sliderPink = Color(0xFFAA59E2)
val sliderPurple = Color(0xFFE64495)
val eclipsePurpleLight = Color(0xFF8A72CA)
val eclipsePurple = Color(0xFF5936B4)
val sunriseWhite = Color(0xFF8AA1CB)
val sunriseBlue = Color(0xFF07275B)
val indicatorDarkPurple = Color(0xFF592d85)
val indicatorLightPurple = Color(0xFF8e57e2)

val kmpLightColors = lightColorScheme(
    primary = purple,
    onPrimary = white,
    primaryContainer = containerPurple,
    onPrimaryContainer = white,
    secondary = darkBlue,
    onSecondary = bluishGrey_60_Opacity,
    tertiary = palePurple,
    onTertiary = bluishGrey_30_Opacity,
    background = palePurple,
    onBackground = white,
    surface = purple,
    onSurface = white,
    surfaceVariant = selectedPurple,
    onSurfaceVariant = white,
    inverseOnSurface = lightBlue,
    outline = outline,
    outlineVariant = outlineVariant,
)

val kmpDarkColors = darkColorScheme(
    primary = purple,
    onPrimary = white,
    primaryContainer = containerPurple,
    onPrimaryContainer = white,
    secondary = darkBlue,
    onSecondary = bluishGrey_60_Opacity,
    tertiary = brightViolet,
    onTertiary = bluishGrey_30_Opacity,
    background = purple,
    onBackground = white,
    surface = purple,
    surfaceVariant = selectedPurple,
    onSurface = white,
    onSurfaceVariant = white,
    inverseOnSurface = lightBlue,
    outline = outline,
    outlineVariant = outlineVariant,
)

data class GradientColors(
    val weatherWidgetGradient: List<Color>,
    val sliderTrackGradient: List<Color>,
    val sideEclipseGradient: List<Color>,
    val topEclipseGradient: List<Pair<Float, Color>>,
    val sunriseGradient: List<Color>,
    val indicatorGradient: List<Color>
)

@Suppress("FunctionName")
fun KmpGradientColors(
    weatherWidgetGradient: List<Color> = listOf(lightPurple, darkPurple),
    sliderTrackGradient: List<Color> = listOf(sliderBlue, sliderPink, sliderPurple),
    sideEclipseGradient: List<Color> = listOf(
        eclipsePurpleLight.copy(alpha = 0.32f),
        purple.copy(alpha = 0.0f)
    ),
    topEclipseGradient: List<Pair<Float, Color>> = listOf(
        0.0f to eclipsePurpleLight.copy(alpha = 0.60f),
        0.5f to eclipsePurpleLight.copy(alpha = 0.235f),
        0.798f to eclipsePurpleLight.copy(alpha = 0.0392f),
        0.9f to eclipsePurple.copy(alpha = 0.0f),
        1.0f to Color.Transparent
    ),
    sunriseGradient: List<Color> = listOf(sunriseBlue, sunriseWhite, sunriseBlue),
    indicatorGradient: List<Color> = listOf(indicatorDarkPurple, indicatorLightPurple, indicatorDarkPurple)
): GradientColors = GradientColors(
    weatherWidgetGradient = weatherWidgetGradient,
    sliderTrackGradient = sliderTrackGradient,
    sideEclipseGradient = sideEclipseGradient,
    topEclipseGradient = topEclipseGradient,
    sunriseGradient = sunriseGradient,
    indicatorGradient = indicatorGradient,
)