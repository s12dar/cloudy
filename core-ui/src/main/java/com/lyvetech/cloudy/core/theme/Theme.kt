package com.lyvetech.cloudy.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColors(
    primary = black,
    primaryVariant = black,
    onPrimary = taupe100,
    secondary = teal200,
    secondaryVariant = teal700,
    onSecondary = black,
    surface = taupe100,
    onSurface = black,
    background = taupe100,
    onBackground = black,
)

private val DarkColorPalette = darkColors(
    primary = gray700,
    primaryVariant = gray700,
    onPrimary = taupe100,
    secondary = teal200,
    secondaryVariant = teal200,
    onSecondary = black,
    surface = black,
    onSurface = taupe100,
    background = gray900,
    onBackground = taupe100,
)

@Composable
fun CloudyTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = Shapes,
        content = content
    )
}