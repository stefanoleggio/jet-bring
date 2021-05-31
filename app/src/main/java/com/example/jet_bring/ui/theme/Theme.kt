package com.example.jet_bring.ui.theme

import android.app.ActionBar
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import android.graphics.drawable.ColorDrawable
import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color.Companion.White


private val DarkColorPalette = darkColors(
    primary = Mirage,
    onPrimary = White,
    secondary = Paradiso,
    onSecondary = White,
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

private val DreamColorPalette = lightColors(
    primary = Mirage,
    secondary = Mediumspringgreen,
    primaryVariant = Springgreen,
    secondaryVariant = TePapaGreen,
    surface = Lightseagreen,
    onSecondary = White,
    background = Darkgreen,
    onBackground = Ceramic,
    onSurface = Ceramic,
)

@Composable
fun JetbringTheme(aTheme:Theme,isSet:Boolean, content: @Composable() () -> Unit) {
    var theme = aTheme
    /*
    var themeName = aThemeName
    if(themeName == null) {
        if (isSystemInDarkTheme()) themeName = "DARK_THEME"
        else themeName = "LIGHT_THEME"
    }
    val theme = Theme.valueOf(themeName)*/
    /*
    val colors = if (darkTheme) {

        LightColorPalette
    } else {
        LightColorPalette
    }
     */
    if(isSet == false) {
        if(isSystemInDarkTheme()) theme = Theme.DARK_THEME
        else theme = Theme.LIGHT_THEME
    }
    MaterialTheme(
        colors = theme.colors,
        typography = Typography,
        shapes = Shapes,
        content = content

    )
}

enum class Theme(val colors: Colors) {
    DARK_THEME(DarkColorPalette),
    LIGHT_THEME(LightColorPalette),
    DREAM_THEME(DreamColorPalette)
}

val themes = listOf(
    Theme.DARK_THEME,
    Theme.LIGHT_THEME,
    Theme.DREAM_THEME
)