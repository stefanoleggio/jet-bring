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
import com.example.jet_bring.R
import com.example.jet_bring.ui.theme.*
import com.example.jet_bring.ui.utils.InputText
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
                    .padding(PADDING_TOP),
                horizontalArrangement = Arrangement.Center
            ) {
                Surface(
                    modifier = Modifier
                        .size(100.dp),
                    shape = CircleShape,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),
                    elevation = 4.dp
                    ) {
                    Image(
                        painterResource(profiloViewModel.user.profileIcon),
                        contentDescription = null
                    )
                }
            }
            profiloViewModel.temp.name.let {
                ProfileInputRow(
                    icon = Icons.Rounded.Person,
                    text = profiloViewModel.temp.name,
                    label = "Come ti chiamano i tuoi amici?",
                    onTextChange = profiloViewModel::editProfileName
                )
            }
            profiloViewModel.temp.email.let {
                ProfileInputRow(
                    icon = Icons.Rounded.Email,
                    label = "Qual è la tua email?",
                    text = profiloViewModel.temp.email,
                    onTextChange = profiloViewModel::editProfileEmail
                )
            }
             /*
            profiloViewModel.temp.name.let{
                InputText(
                    text = it,
                    profiloViewModel::editProfileName,
                    label = "Come ti chiamano i tuoi amici?"
                )
            }
            profiloViewModel.temp.email.let{
                InputText(
                    text = it,
                    profiloViewModel::editProfileEmail,
                    label = "Qual è la tua email?"
                )
            }
            */
            Row(
                Modifier
                    .padding(PADDING_TOP)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Button(
                    onClick = {
                        profiloViewModel.onSaveDone()
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Salvataggio Dati",
                                duration = SnackbarDuration.Short
                            )
                        }
                              },
                    Modifier,
                    colors = outlinedButtonColors(
                        backgroundColor = MaterialTheme.colors.secondary,
                        contentColor = MaterialTheme.colors.onSurface
                    ),
                    elevation = ButtonDefaults.elevation()
                )
                {
                    Text("Salva", color = Color.White)
                }
            }
            SnackbarHost(
                hostState = snackbarHostState,
                Modifier.wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
}

@ExperimentalComposeUiApi
@Composable
fun ProfileInputRow(
    icon: ImageVector,
    text: String,
    onTextChange: (String) -> Unit,
    label: String,
) {
    Row(
        Modifier.padding(top = PADDING_TOP,start = PADDING_START,end = PADDING_END),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            icon,
            contentDescription = "Localized description",
            tint = MaterialTheme.colors.onBackground,
        )
        Spacer(modifier = Modifier.padding(PADDING_TOP/2))
        Surface(
            Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colors.surface,
            elevation = 4.dp
            ) {
            Column() {
                InputText(
                    text = text,
                    onTextChange = onTextChange,
                    label = label,
                    modifier = Modifier
                        .padding(bottom = PADDING_BOTTOM, start = PADDING_START, end = PADDING_END)
                        .fillMaxWidth()
                )
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
    ProfileInputRow(Icons.Rounded.Person,"ciao",{},"cosa mi vuoi dire?")
}