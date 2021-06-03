package com.example.jet_bring.ui.profilo

import android.graphics.drawable.Icon
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.outlinedButtonColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jet_bring.R
import com.example.jet_bring.ui.theme.JetbringTheme
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun IlTuoProfilo(
                profiloViewModel: ProfiloViewModel
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
        Column {
            //Spacer(modifier = Modifier.padding(padding))
            Row(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface)
                    .padding(padding),
                horizontalArrangement = Arrangement.Center
            ) {
                Surface(
                    modifier = Modifier
                        .size(100.dp)
                        .clickable {},
                    shape = CircleShape,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),

                    ) {
                    Image(
                        painterResource(R.drawable.jet_bringicon),
                        contentDescription = null
                    )
                }
            }

            ProfileInputRow(
                icon = Icons.Rounded.Person,
                title = "Come ti chiamano i tuoi amici?",
                text = profiloViewModel.temp.name,
                onModifiedText = profiloViewModel::editProfileName
            )
            ProfileInputRow(
                icon = Icons.Rounded.Email,
                title = "Qual è la tua email?",
                text = profiloViewModel.temp.email,
                onModifiedText = profiloViewModel::editProfileEmail
            )
            SnackbarHost(
                hostState = snackbarHostState,
                Modifier.wrapContentWidth(Alignment.CenterHorizontally)
            )
            Row(
                Modifier
                    .padding(padding)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Button(
                    onClick = {
                        profiloViewModel.onSaveDone()
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(message = "Salvataggio Dati")
                        }
                              },
                    Modifier,
                    colors = outlinedButtonColors(
                        backgroundColor = MaterialTheme.colors.secondary,
                        contentColor = MaterialTheme.colors.onSurface
                    )
                )
                {
                    Text("Salva")
                }
            }
        }
}

@ExperimentalComposeUiApi
@Composable
fun ProfileInputRow(
    icon: ImageVector,
    title: String,
    text: String,
    onModifiedText: (String) -> Unit
) {
    Row(
        Modifier.padding(top = padding,start = padding,end = padding),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = "Localized description",
            tint = MaterialTheme.colors.onBackground,
        )
        Spacer(modifier = Modifier.padding(padding/2))
        Surface(
            Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colors.surface,
            ) {
            Column() {
                Text(
                    text = title,
                    modifier =Modifier.padding(start = padding)
                )
                InputText(text = text, onModifiedText, modifier = Modifier.fillMaxWidth())
            }
        }

    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun IlTuoProfiloPreview() {
    IlTuoProfilo(ProfiloViewModel())
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun ProfileInputRowPreview() {
    ProfileInputRow(Icons.Rounded.Person,"cosa mi vuoi dire?","ciao",{})
}
