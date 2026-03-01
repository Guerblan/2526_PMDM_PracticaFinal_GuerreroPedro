package com.pedro.pideyaapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val AppColors = lightColorScheme(

    primary = RojoPimenton,
    secondary = AmarilloAzafran,
    tertiary = VerdeOliva,

    background = CremaFondo,
    surface = CremaSuperficie,

    onPrimary = Color.White,
    onBackground = MarronTexto,
    onSurface = MarronTexto
)

@Composable
fun PideYaAppTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = AppColors,
        typography = Typography,
        content = content
    )
}