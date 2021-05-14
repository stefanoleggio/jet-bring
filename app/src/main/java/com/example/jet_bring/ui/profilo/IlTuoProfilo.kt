package com.example.jet_bring.ui.profilo

import android.graphics.drawable.Icon
import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.jet_bring.ui.theme.JetbringTheme

@ExperimentalComposeUiApi
@Composable
fun IlTuoProfilo() {
    val user  = UserData()
    val (username:String, modifiedName) = remember {
        mutableStateOf(user.name)
    }
    val (userEmail:String, modifiedEmail) = remember {
        mutableStateOf(user.email)
    }
    JetbringTheme() {
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
                    modifier = Modifier.size(100.dp),
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
                text = username,
                modifiedName
            )
            ProfileInputRow(
                icon = Icons.Rounded.Email,
                title = "Qual è la tua email?",
                text = userEmail,
                modifiedEmail
            )
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
            Column(Modifier.padding(start = padding)) {
                Text(text = title)
                InputText(text = text, onModifiedText, modifier = Modifier.fillMaxWidth())
            }
        }

    }
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun IlTuoProfiloPreview() {
    IlTuoProfilo()
}

@ExperimentalComposeUiApi
@Preview
@Composable
fun ProfileInputRowPreview() {
    ProfileInputRow(Icons.Rounded.Person,"cosa mi vuoi dire?","ciao",{})
}
