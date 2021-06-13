package com.example.jet_bring.ui.theme

import android.app.ActionBar
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import android.graphics.drawable.ColorDrawable
import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White


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
    /*
    if(isSet == false) {
        if(isSystemInDarkTheme()) theme = themes.get(0)
        else theme = themes.get(1)
    }
     */
    MaterialTheme(
        colors = theme.colors,
        typography = Typography,
        shapes = Shapes,
        content = content

    )
}
/*
enum class Theme(val colors: Colors,val description:String) {
    DARK_THEME(DarkColorPalette,"Dark Theme"),
    LIGHT_THEME(LightColorPalette,"Light Theme"),
    DREAM_THEME(DreamColorPalette,"Dream Theme")
}
*/
data class Theme(val colors: Colors, val description:String)

val themes = arrayListOf(
    Theme(DarkColorPalette,"Dark Theme"),
    Theme(LightColorPalette,"Light Theme"),
    Theme(DreamColorPalette,"Dream Theme")
    )