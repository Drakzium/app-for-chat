package com.noonechat.app.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// System font stack as baseline; brand display weights applied consistently.
// Custom font files can replace FontFamily.SansSerif without touching call sites.
private val BrandSans = FontFamily.SansSerif

val NoOneTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = BrandSans,
        fontWeight = FontWeight.Bold,
        fontSize = 40.sp,
        lineHeight = 46.sp,
        letterSpacing = (-0.5).sp,
        color = PrimaryText
    ),
    headlineLarge = TextStyle(
        fontFamily = BrandSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 34.sp,
        color = PrimaryText
    ),
    headlineMedium = TextStyle(
        fontFamily = BrandSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        color = PrimaryText
    ),
    titleLarge = TextStyle(
        fontFamily = BrandSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        color = PrimaryText
    ),
    titleMedium = TextStyle(
        fontFamily = BrandSans,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        color = PrimaryText
    ),
    bodyLarge = TextStyle(
        fontFamily = BrandSans,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        color = PrimaryText
    ),
    bodyMedium = TextStyle(
        fontFamily = BrandSans,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        color = SecondaryText
    ),
    labelLarge = TextStyle(
        fontFamily = BrandSans,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        color = PrimaryText
    ),
    labelSmall = TextStyle(
        fontFamily = BrandSans,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 14.sp,
        color = SecondaryText
    )
)
