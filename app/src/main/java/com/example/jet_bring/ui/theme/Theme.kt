package com.example.jet_bring.ui.theme

import android.app.ActionBar
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import android.graphics.drawable.ColorDrawable
import androidx.compose.ui.graphics.Color.Companion.White


private val DarkColorPalette = darkColors(
    primary = Mirage,
    secondary = Paradiso,
    primaryVariant = BlueBayoux,
    secondaryVariant = TePapaGreen,
    surface = EbonyClay,
    background = LimedSpruce,
    onBackground = Ceramic,
    onSurface = Ceramic,
)

private val LightColorPalette = lightColors(
    primary = Mirage,
    secondary = Paradiso,
    primaryVariant = BlueBayoux,
    secondaryVariant = TePapaGreen,
    surface = EbonyClay,
    onSecondary = White,
    background = LimedSpruce,
    onBackground = Ceramic,
    onSurface = Ceramic,
)

@Composable
fun JetbringTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content

    )
}