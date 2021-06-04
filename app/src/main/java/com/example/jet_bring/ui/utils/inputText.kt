package com.example.jet_bring.ui.utils

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction

@ExperimentalComposeUiApi
@Composable

fun InputText(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onImeAction: () -> Unit = {},
    label: String? = null
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = text,
        label = { label?.let { Text(it) } },
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            textColor = MaterialTheme.colors.onBackground,
            unfocusedLabelColor = MaterialTheme.colors.onBackground,
            focusedLabelColor = MaterialTheme.colors.secondary,
            focusedIndicatorColor = MaterialTheme.colors.secondary,
            unfocusedIndicatorColor = MaterialTheme.colors.onBackground,
        ),
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = modifier
    )
}


