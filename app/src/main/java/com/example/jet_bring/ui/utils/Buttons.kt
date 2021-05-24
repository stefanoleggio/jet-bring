package com.example.jet_bring.ui.utils

import androidx.annotation.MainThread
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainTextButton(
    onClick: () -> Unit,
    text: String,
    isBackgroundSecondary: Boolean
) {
    if(isBackgroundSecondary) {
        TextButton(
            onClick = onClick,
            modifier = Modifier.background(MaterialTheme.colors.secondary)
        ) {
            Text(text, color = MaterialTheme.colors.onBackground)
        }
    } else {
        TextButton(
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