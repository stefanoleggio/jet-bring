package com.example.jet_bring.ui.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jet_bring.ui.ispirazione.components.InputText

@ExperimentalComposeUiApi
@Composable
fun MainInputText(
    onTextChange: (String) -> Unit,
    text: String,
    label: String
) {
    OutlinedTextField(
        value = text,
        label = { Text(text = label) },
        onValueChange = onTextChange,
        modifier = Modifier.fillMaxWidth(),
    )
}


@ExperimentalComposeUiApi
@Preview
@Composable
fun MainInputTextPreview() {
    MainInputText(onTextChange = {}, text = "ciao", label = "Descrizione")
}

