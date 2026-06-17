package com.pedro.pideyaapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AppColors = darkColorScheme(
    primary = Ember,
    secondary = Gold,
    tertiary = Mint,
    background = Night,
    surface = Midnight,
    surfaceVariant = Panel,
    primaryContainer = EmberSoft.copy(alpha = 0.22f),
    secondaryContainer = Gold.copy(alpha = 0.18f),
    tertiaryContainer = Mint.copy(alpha = 0.18f),
    onPrimary = Color.White,
    onSecondary = Night,
    onTertiary = Night,
    onPrimaryContainer = TextPrimary,
    onSecondaryContainer = TextPrimary,
    onTertiaryContainer = TextPrimary,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onSurfaceVariant = TextSecondary,
    outline = Divider
)

@Composable
fun LaJuaniTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AppColors,
        typography = Typography,
        content = content
    )
}
