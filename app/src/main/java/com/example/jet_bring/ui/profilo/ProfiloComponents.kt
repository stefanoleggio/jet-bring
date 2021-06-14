package com.example.jet_bring.ui.profilo

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.jet_bring.R
import com.example.jet_bring.ui.theme.*

/**
 * funzione componente che genera una Table riutilizzabile per la finestra del profilo
 */
@Composable
fun Table(
    title: String,
    iconTitle:ImageVector,
    navController: NavHostController,
    boxesArgs: List<CBoxArg>
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
            Spacer(modifier = Modifier.padding(PADDING_TOP/4))
            Surface(
                Modifier
                    .padding(PaddingValues(
                        start = PADDING_START,
                        end = PADDING_END/4
                    )),
                color = MaterialTheme.colors.background
            ){
                Spacer( modifier = Modifier
                    .padding(1.dp)
                    .fillMaxWidth()
                )
            }

            boxesArgs.forEach() {
                ClickableBox(title = it.title, navController = navController, route = it.link)
            }

        }
    }
}

/**
 * funzione che genera la riga di header utilizzata dalle funzioni table e SettingsTable per creare
 * il titolo delle tavole di modifica dei componenti profilo
 */
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
                    start = PADDING_START,
                    top = PADDING_TOP,
                    end = 5.dp
                )
            )
    )
    {
        Icon(
            icon,
            contentDescription = null)
        Spacer(modifier = Modifier.padding(LINE_SPACE))
        Text(text = title)
        //
    }
}

/**
 * Funzione componente che genera i box universali utilizzati da Table e settingsTable che,
 * se cliccati permettono di muoversi all'interno dell'applicazione
 */
@Composable
fun ClickableBox(
    title: String,
    navController: NavHostController,
    route: String,
    icon: ImageVector? = null,
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
        if(icon!= null) {
            Row(Modifier.padding(PADDING_START, PADDING_BOTTOM/2),
                verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null,)
                Spacer(modifier = Modifier.padding(LINE_SPACE))
                Text(text = title)
            }
        }else {
            Text(
                text = title,
                Modifier.padding(PADDING_START, PADDING_TOP / 2)
            )
        }
    }
}
/**
 * Funzione componente che genera una riga contenente due bottoni,
 * utilizzata da settingsTable per i bottoni Tema e AspettoLIsta
 */
@Composable
fun TwoButtonsRow(b1Text: String,b2Text:String,b1Click:() -> Unit,b2Click:() -> Unit, icon1: Int,icon2:Int) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(PADDING_TOP)) {
        MyButton(bText = b1Text, bClick = b1Click, icon = icon1, modifier = Modifier.weight(1f))
        Spacer(Modifier.padding(PADDING_TOP/3))
        MyButton(bText = b2Text, bClick = b2Click, icon = icon2, modifier = Modifier.weight(1f))
    }
}
/**
 * Funzione componente che genera un bottone custom riutilizzabile all'interno dell'applicazione
 */
@Composable
fun MyButton(bText: String,bClick:() -> Unit, icon: Int,modifier:Modifier) {
    Button(
        onClick = bClick,
        modifier
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                modifier = Modifier.height(20.dp),
                tint = MaterialTheme.colors.onPrimary
            )
            Spacer(modifier = Modifier.padding(LINE_SPACE))
            Text(bText,color = MaterialTheme.colors.onPrimary, style = MaterialTheme.typography.body1
            )
        }
    }
}

/**
 * Funzione componente che genera un pannello contenente un numero di RadialButton definito da
 */
@Composable
fun ChoosingTab(
    selectedState: Int,
    onStateChange: (Int) -> Unit,
    states: List<String>,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        states.forEachIndexed() { index, it ->
            RadioButtonRow((selectedState == index),onStateChange,it,index)
            if(index != states.size-1) {
                Spacer(modifier = Modifier.padding(LINE_SPACE))
            }
        }
        }
}
@Composable
fun RadioButtonRow(
    selected: Boolean,
    onStateChange: (Int) -> Unit,
    description: String,
    index: Int
) {
    Row(Modifier
        .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            onClick = { onStateChange(index)},
            selected = selected,
            modifier = Modifier.semantics {  contentDescription = description }
        )
        Spacer(Modifier.padding(LINE_SPACE))
        Text(text = description)
    }
}

@Composable
@Preview
fun TablePreview() {
    val testList = listOf<CBoxArg>(
        CBoxArg("ciao","null",),
        CBoxArg("ciaone","null",),
        CBoxArg("ciaotto","null",),
        CBoxArg("ciaello","null",)
    )
    Table(
        "tablePreview",
        Icons.Default.Settings,
        rememberNavController(),
        testList
    )
}

@Composable
@Preview
fun ClickableBoxPreview() {
    ClickableBox("Clickable Box Preview", rememberNavController(),"liste")
}
@Composable
@Preview
fun ClickableBoxIconPreview() {
    ClickableBox("Clickable Box Preview", rememberNavController(),"liste",Icons.Default.Add)
}

@Composable
@Preview
fun TwoButtonsRowPreview() {
    TwoButtonsRow("Aspetto della lista", "miao", {},{}, R.drawable.ic_pixels, R.drawable.ic_half_circle)
}

@Composable
@Preview
fun ChoosingTabPreview() {
    ChoosingTab(3,{}, listOf("verde","giallo","azzurro","blu"),)
}