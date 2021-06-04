package com.example.jet_bring.ui.ispirazione.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Link
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import com.example.jet_bring.ui.ispirazione.AddRicettaViewModel
import com.example.jet_bring.ui.utils.InputText


@ExperimentalComposeUiApi
@Composable
fun AlertDialogLink(link: String, onModifiedText: (String) -> Unit, addRicettaViewModel: AddRicettaViewModel) {

    Column {
        val openDialog = remember { mutableStateOf(false)  }

        Button(onClick = {
            openDialog.value = true
        }) {
            Icon(Icons.Filled.Link, contentDescription = "", tint = MaterialTheme.colors.onBackground, modifier = Modifier.size(30.dp) )
        }

        if (openDialog.value) {

            AlertDialog(
                onDismissRequest = {
                    // Dismiss the dialog when the user clicks outside the dialog or on the back
                    // button. If you want to disable that functionality, simply use an empty
                    // onCloseRequest.
                    openDialog.value = false
                },
                title = {
                    Text(text = "Aggiungi Link")
                },
                text = {
                    InputText(text = link, onModifiedText, modifier = Modifier.fillMaxWidth())

                },

                confirmButton = {
                    Button(

                        onClick = {
                            addRicettaViewModel.onSaveDone()
                            openDialog.value = false
                        }) {
                        Text("ANNULLA")
                    }
                },
                dismissButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                        }) {
                        Text("OK")
                    }
                }
            )
        }
    }
}

/**TODO : Mattere input text in comune*/

