package com.br.kmpdemo.compose.resources.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.br.kmpdemo.compose.resources.SharedRes
import dev.icerock.moko.resources.compose.fontFamilyResource

// https://www.figma.com/file/g9qhhNwi3LTI2P2vKJ0srE/Weather-App-UI-Design-(Community)?node-id=2%3A190&mode=dev

@Composable
fun getKmpTypography() = Typography(
    // Default/Regular/LargeTitle
    displayLarge = TextStyle(
        fontFamily = fontFamilyResource(SharedRes.fonts.Montserrat.regular),
        fontSize = 34.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        lineHeight = 41.sp,
        letterSpacing = 0.374.sp
    ),
    // Default/Regular/Headline
    headlineSmall = TextStyle(
        fontFamily = fontFamilyResource(SharedRes.fonts.Montserrat.regular),
        fontSize = 17.sp,
        fontWeight = FontWeight.SemiBold,
        fontStyle = FontStyle.Normal,
        lineHeight = 22.sp,
        letterSpacing = 0.sp
    ),
    // Default/Regular/Title1
    titleLarge = TextStyle(
        fontFamily = fontFamilyResource(SharedRes.fonts.Montserrat.regular),
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        lineHeight = 34.sp,
        letterSpacing = 0.364.sp
    ),
    // Default/Regular/Title2
    titleMedium = TextStyle(
        fontFamily = fontFamilyResource(SharedRes.fonts.Montserrat.regular),
        fontSize = 22.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        lineHeight = 28.sp,
        letterSpacing = 0.35.sp
    ),
    // Default/Regular/Title3
    titleSmall = TextStyle(
        fontFamily = fontFamilyResource(SharedRes.fonts.Montserrat.regular),
        fontSize = 20.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        lineHeight = 24.sp,
        letterSpacing = 0.38.sp
    ),
    // Default/Regular/Body
    bodyLarge = TextStyle(
        fontFamily = fontFamilyResource(SharedRes.fonts.Montserrat.regular),
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        lineHeight = 22.sp,
        letterSpacing = 0.sp
    ),
    // Default/Regular/Callout
    bodyMedium = TextStyle(
        fontFamily = fontFamilyResource(SharedRes.fonts.Montserrat.regular),
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        lineHeight = 21.sp,
        letterSpacing = 0.sp
    ),
    // Default/Regular/Subheadline
    bodySmall = TextStyle(
        fontFamily = fontFamilyResource(SharedRes.fonts.Montserrat.regular),
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
    // Default/Regular/Footnote
    labelLarge = TextStyle(
        fontFamily = fontFamilyResource(SharedRes.fonts.Montserrat.regular),
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        lineHeight = 18.sp,
        letterSpacing = 0.sp
    ),
    // Default/Regular/Caption
    labelMedium = TextStyle(
        fontFamily = fontFamilyResource(SharedRes.fonts.Montserrat.regular),
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        lineHeight = 16.sp
    ),
    // Default/Regular/Caption2
    labelSmall = TextStyle(
        fontFamily = fontFamilyResource(SharedRes.fonts.Montserrat.regular),
        fontSize = 11.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Normal,
        lineHeight = 13.sp,
        letterSpacing = 0.066.sp
    )
)

fun TextStyle.light() = copy(fontWeight = FontWeight.Light)
fun TextStyle.semiBold() = copy(fontWeight = FontWeight.SemiBold)
fun TextStyle.bold() = copy(fontWeight = FontWeight.Bold)
fun TextStyle.letterSpacing(spacing: TextUnit) = copy(letterSpacing = spacing)
fun TextStyle.size(size: TextUnit) = copy(fontSize = size)