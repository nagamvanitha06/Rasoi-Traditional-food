package com.example.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = SaffronOrange,
    onPrimary = Color.White,
    primaryContainer = SaffronDeep,
    onPrimaryContainer = Color.White,
    secondary = TurmericGold,
    onSecondary = CharcoalDark,
    secondaryContainer = Color(0xFF4A3412),
    onSecondaryContainer = TurmericLight,
    tertiary = CurryGreen,
    onTertiary = Color.White,
    background = CardamomDarkBg,
    onBackground = Color(0xFFEDE5DF),
    surface = CardamomDarkSurface,
    onSurface = Color(0xFFEDE5DF),
    surfaceVariant = Color(0xFF2C2420),
    onSurfaceVariant = Color(0xFFCDC2BB),
    outline = BorderSubtleDark
)

private val LightColorScheme = lightColorScheme(
    primary = SaffronDeep,
    onPrimary = Color.White,
    primaryContainer = SaffronLight,
    onPrimaryContainer = SaffronDeep,
    secondary = TurmericGold,
    onSecondary = Color.White,
    secondaryContainer = TurmericLight,
    onSecondaryContainer = Color(0xFF5D3E03),
    tertiary = CurryGreen,
    onTertiary = Color.White,
    tertiaryContainer = CurryGreenLight,
    onTertiaryContainer = CurryGreenDark,
    background = CardamomCream,
    onBackground = CharcoalDark,
    surface = CardamomSurface,
    onSurface = CharcoalDark,
    surfaceVariant = Color(0xFFF3ECE4),
    onSurfaceVariant = CharcoalMuted,
    outline = BorderSubtle
)

@Composable
fun RasoiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Keep intentional culinary colors
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
