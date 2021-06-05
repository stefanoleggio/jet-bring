package com.example.jet_bring.ui.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview

@ExperimentalComposeUiApi
@Composable
fun MainInputText(
    onTextChange: (String) -> Unit,
    text: String,
    label: String
) {

    OutlinedTextField(
        value = text,
        label = { Text(text = label, color = Color.White) },
        onValueChange = onTextChange,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White),
        modifier = Modifier.fillMaxWidth(),
    )
}


@ExperimentalComposeUiApi
@Preview
@Composable
fun MainInputTextPreview() {
    MainInputText(onTextChange = {}, text = "ciao", label = "Descrizione")
}

