package com.noonechat.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val NoOneDarkColors = darkColorScheme(
    primary = PrimaryBlue,
    onPrimary = PrimaryText,
    secondary = ElectricBlue,
    onSecondary = PrimaryText,
    tertiary = Cyan,
    background = Background,
    onBackground = PrimaryText,
    surface = SecondaryBackground,
    onSurface = PrimaryText,
    surfaceVariant = CardBackground,
    onSurfaceVariant = SecondaryText,
    error = Error,
    onError = PrimaryText,
    outline = Divider
)

val BrandGradient = Brush.linearGradient(
    colors = listOf(PrimaryBlue, ElectricBlue, Cyan)
)

val SurfaceGlow = Brush.verticalGradient(
    colors = listOf(Color(0x331677FF), Color.Transparent)
)

@Composable
fun NoOneChatTheme(content: @Composable () -> Unit) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = android.graphics.Color.parseColor("#080B14")
            window.navigationBarColor = android.graphics.Color.parseColor("#080B14")
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = NoOneDarkColors,
        typography = NoOneTypography,
        shapes = NoOneShapes,
        content = content
    )
}
