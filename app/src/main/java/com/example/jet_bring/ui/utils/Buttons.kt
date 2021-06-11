package com.example.jet_bring.ui.utils

import androidx.annotation.MainThread
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainTextButton(
    onClick: () -> Unit,
    text: String,
    isBackgroundSecondary: Boolean
) {
    if(isBackgroundSecondary) {
        Button(
            onClick = onClick,
            elevation = ButtonDefaults.elevation(),
            colors = ButtonDefaults.outlinedButtonColors(
                backgroundColor = MaterialTheme.colors.secondary,
                contentColor = MaterialTheme.colors.onSurface
            )
        ) {
            Text(text, color = MaterialTheme.colors.onBackground)
        }
    } else {
        TextButton(
            shape = RoundedCornerShape(16.dp),
            onClick = onClick
        ) {
            Text(text, color = MaterialTheme.colors.onBackground)
        }
    }

}

@ExperimentalComposeUiApi
@Preview
@Composable
fun MainButtonTextPreview() {
    MainTextButton(onClick = {}, text = "Testo", isBackgroundSecondary = true)
}