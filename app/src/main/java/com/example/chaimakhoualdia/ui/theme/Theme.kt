package com.example.chaimakhoualdia.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DeepBlue = Color(0xFF0B486B)
private val WarmEarth = Color(0xFFC98B53)
private val Sand = Color(0xFFF7F3EE)
private val Ink = Color(0xFF111111)

private val LightColorScheme = lightColorScheme(
    primary = DeepBlue,
    onPrimary = Color.White,
    secondary = WarmEarth,
    onSecondary = Color.White,
    background = Sand,
    onBackground = Ink,
    surface = Color.White,
    onSurface = Ink
)

private val DarkColorScheme = darkColorScheme(
    primary = DeepBlue,
    onPrimary = Color.White,
    secondary = WarmEarth,
    onSecondary = Color.White,
    background = Color(0xFF101820),
    onBackground = Color.White,
    surface = Color(0xFF1A2230),
    onSurface = Color.White
)

@Composable
fun ChaimaKhoualdiaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}