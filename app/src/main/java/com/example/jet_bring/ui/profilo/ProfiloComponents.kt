package com.example.jet_bring.ui.profilo

import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.jet_bring.ui.theme.JetbringTheme




@Composable
fun Table(
    title: String,
    iconTitle:ImageVector,
    navController: NavHostController
)   {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape= RoundedCornerShape(10.dp),
        color = MaterialTheme.colors.surface

    ) {
        Column(
        ) {
            HeaderBox(title = title, icon = iconTitle)
            Spacer(modifier = Modifier.padding(padding/4))
            Surface(
                Modifier
                    .padding(PaddingValues(
                        start = padding,
                        end = padding/4
                    )),
                color = MaterialTheme.colors.background
            ){
                Spacer( modifier = Modifier
                    .padding(1.dp)
                    .fillMaxWidth()
                )
            }

            ClickableBox(title = "liste",navController,"liste")
            //ClickableBox(title = "My new Box", onItemClicked = ListeScreen())
        }
    }
}

@Composable
 fun HeaderBox(

    title: String,
    icon: ImageVector,

    ) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .padding(
                PaddingValues(
                    start = padding,
                    top = padding,
                    end = 5.dp
                )
            )
    )
    {
        Icon(
            icon,
            contentDescription = null)
        Spacer(modifier = Modifier.padding(padding/4))
        Text(text = title)
        //
    }
}


@Composable
fun ClickableBox(
    title: String,
    navController: NavHostController,
    route: String
) {
    Surface(
        Modifier
            .fillMaxWidth()
            .clickable {
                navController.navigate(route) {
                    popUpTo = navController.graph.startDestination
                }
            }
    ) {
        Text(text = title,
            Modifier.padding(padding,padding/2))
    }
}

@ExperimentalComposeUiApi
@Composable
fun InputText(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    onImeAction: () -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = text,
        onValueChange = onTextChange,
        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.Transparent),
        maxLines = 1,
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyboardController?.hide()
        }),
        modifier = modifier
    )
}

@Composable
@Preview
fun TablePreview() {
    Table(
        "tablePreview",
        Icons.Default.Settings,
        rememberNavController()

    )
}

@Composable
@Preview
fun ClickableBoxPreview() {
    ClickableBox("Clickable Box Preview", rememberNavController(),"liste")
}