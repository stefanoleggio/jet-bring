package com.example.jet_bring.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color.Companion.White


/**
 * Definizione delle color pallette per i temi
 */

private val DarkColorPalette = darkColors(
    primary = Mirage,
    onPrimary = Ceramic,
    secondary = Paradiso,
    onSecondary = LowAlphaIron,
    primaryVariant = BlueBayoux,
    secondaryVariant = TePapaGreen,
    surface = EbonyClay,
    background = LimedSpruce,
    onBackground = Ceramic,
    onSurface = Ceramic,
)

private val LightColorPalette = lightColors(
    primary = LimedSpruce,
    onPrimary = White,
    secondary = Paradiso,
    onSecondary = White,
    primaryVariant = BlueBayoux,
    secondaryVariant = BlueBayoux,
    surface = Iron,
    background = Ceramic,
    onBackground = LimedSpruce,
    onSurface = LimedSpruce,
)

private val DreamColorPalette = lightColors(
    primary = DarkSlateBlue,
    onPrimary = Ceramic,
    secondary = Citron,
    primaryVariant = OrchidCrayola,
    secondaryVariant = DarkBlueGray,
    surface = StPatricksBlue,
    onSecondary = LowAlphaIron,
    background = RoyalPurple,
    onBackground = Ceramic,
    onSurface = Ceramic,
)

@Composable
fun JetbringTheme(aTheme:Theme,isSet:Boolean, content: @Composable () -> Unit) {
    var theme = aTheme
    MaterialTheme(
        colors = theme.colors,
        typography = Typography,
        shapes = Shapes,
        content = content

    )
}
data class Theme(val colors: Colors, val description:String)

val themes = arrayListOf(
    Theme(DarkColorPalette,"Dark Theme"),
    Theme(LightColorPalette,"Light Theme"),
    Theme(DreamColorPalette,"Dream Theme")
    )