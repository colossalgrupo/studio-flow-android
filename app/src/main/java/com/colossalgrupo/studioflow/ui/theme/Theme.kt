package com.colossalgrupo.studioflow.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val StudioScheduleLightColorScheme = lightColorScheme(
    primary = EmeraldLight,
    onPrimary = Color.White,
    primaryContainer = Color(0xFFB7E4D9),
    onPrimaryContainer = Color(0xFF00201A),
    secondary = GoldLight,
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFF3DDB6),
    onSecondaryContainer = Color(0xFF2A1800),
    tertiary = RoseLight,
    onTertiary = Color.White,
    tertiaryContainer = Color(0xFFF7D3C7),
    onTertiaryContainer = Color(0xFF3A0C00),
    background = BackgroundLight,
    onBackground = OnColorLight,
    surface = SurfaceLight,
    onSurface = OnColorLight,
    surfaceVariant = Color(0xFFE3E5E1),
    onSurfaceVariant = Color(0xFF44483F)
)

private val StudioScheduleDarkColorScheme = darkColorScheme(
    primary = EmeraldDark,
    onPrimary = Color(0xFF00382E),
    primaryContainer = Color(0xFF005042),
    onPrimaryContainer = Color(0xFFB7E4D9),
    secondary = GoldDark,
    onSecondary = Color(0xFF422D00),
    secondaryContainer = Color(0xFF5C4200),
    onSecondaryContainer = Color(0xFFF3DDB6),
    tertiary = RoseDark,
    onTertiary = Color(0xFF4C1B0C),
    tertiaryContainer = Color(0xFF69291A),
    onTertiaryContainer = Color(0xFFF7D3C7),
    background = BackgroundDark,
    onBackground = OnColorDark,
    surface = SurfaceDark,
    onSurface = OnColorDark,
    surfaceVariant = Color(0xFF3F4944),
    onSurfaceVariant = Color(0xFFC0C9C2)
)

@Composable
fun StudioScheduleTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) StudioScheduleDarkColorScheme else StudioScheduleLightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = StudioScheduleTypography,
        content = content
    )
}
