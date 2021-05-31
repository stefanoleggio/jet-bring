package com.example.jet_bring.ui.profilo


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import androidx.ui.layout.Expanded
import com.example.jet_bring.R
import com.example.jet_bring.ui.liste.ListeViewModel
import com.example.jet_bring.ui.theme.JetbringTheme
import com.example.jet_bring.ui.theme.PADDING_END
import com.example.jet_bring.ui.theme.PADDING_START
import com.example.jet_bring.ui.theme.themes

val padding = 16.dp
@ExperimentalAnimationApi
@Composable
fun ProfiloScreen(
    navController: NavHostController,
    scafPaddingValues: PaddingValues,
    profiloViewModel: ProfiloViewModel,
    listeViewModel: ListeViewModel,
) {

    val fontSize = 24.sp
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                scafPaddingValues.calculateStartPadding(LayoutDirection.Ltr).plus(padding),
                scafPaddingValues.calculateTopPadding().plus(padding),
                scafPaddingValues.calculateEndPadding(LayoutDirection.Ltr).plus(padding),
                scafPaddingValues.calculateBottomPadding().plus(padding)
            )
        ) {

            item{Text(
                "Hi, ${profiloViewModel.user.name}",
                style = TextStyle(color = MaterialTheme.colors.onBackground, fontSize = fontSize )
            )}
            item{Spacer(Modifier.size(padding))}

            item {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        onClick = {
                            navController.navigate("profilo/ilTuoProfilo") {
                            }
                        },

                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,) {
                    Surface(
                        modifier = Modifier
                            .size(150.dp),
                        shape = CircleShape,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),

                        ) {
                        Image(
                            painterResource(R.drawable.jet_bringicon),
                            contentDescription = null
                        )

                    }
                    Spacer(modifier = Modifier.padding(padding/2))
                    Text(text = profiloViewModel.user.name,
                    color = MaterialTheme.colors.onBackground
                        ) /*TODO inserire userData.name qui quando pronto*/
                    Text(text = profiloViewModel.user.email,
                        color = MaterialTheme.colors.onBackground
                    ) /*TODO inserire userData.email qui quando pronto*/
                }
            }
            item {Spacer(Modifier.size(padding))}

            item{SettingsTable(tableDataList.get(0).title,tableDataList.get(0).iconTitle,navController,listeViewModel = listeViewModel,profiloViewModel)

            }
            item{Spacer(Modifier.padding(padding))}
            items(items = tableDataList.subList(1, tableDataList.lastIndex)) {
                Table(it.title,it.iconTitle,navController,it.list)
                Spacer(Modifier.padding(padding))
            }

        }
}

@ExperimentalAnimationApi
@Composable
fun SettingsTable(
    title: String,
    iconTitle: ImageVector,
    navController: NavHostController,
    listeViewModel: ListeViewModel,
    profiloViewModel: ProfiloViewModel
)   {
    var expandedChoice by remember { mutableStateOf<String?>(null) }
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape= RoundedCornerShape(10.dp),
        color = MaterialTheme.colors.surface

    ) {
        Column(Modifier.animateContentSize() ) {
            HeaderBox(title = title, icon = iconTitle)
            Spacer(modifier = Modifier.padding(padding/4))
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

            TwoButtonsRow(
                "Aspetto della lista",
                "Tema",
                {
                    if(expandedChoice.equals("aspettoDellaLista")) expandedChoice = null
                    else   expandedChoice = "aspettoDellaLista"
                },
                {
                    if(expandedChoice.equals("tema")) expandedChoice = null
                    else   expandedChoice = "tema"
                },
                Icons.Default.ShoppingCart,
                Icons.Default.ShoppingCart
            ) /*TODO aggiungere azioni bottoni e icone corrette*/
            AnimatedVisibility(visible = (expandedChoice != null)) {
                if(expandedChoice == "aspettoDellaLista") {
                    ChoosingTab(
                        selectedState = if (profiloViewModel.isColumnMode()) 1 else 0,
                        onStateChange = profiloViewModel::setMode,
                        states = listOf("Grid Mode", "Column Mode"),
                        modifier = Modifier.padding(start = PADDING_START, end = PADDING_END)
                    )
                } else if (expandedChoice == "tema") {
                    ChoosingTab(
                        selectedState = themes.indexOf(profiloViewModel.getTheme()),
                        onStateChange = profiloViewModel::setTheme,
                        states = listOf(themes.toString()),
                        modifier = Modifier.padding(start = PADDING_START, end = PADDING_END)
                    )
                }
            }
            ClickableBox(title = "Impostazioni Lista",
                navController = rememberNavController(),
                route = "casamia",
                Icons.Default.List
            )
            ClickableBox(title = "Altre Impostazioni",
                navController = rememberNavController(),
                route = "casatua"
            )
            /*
            TwoButtonsRow(
                "Column Mode",
                "Grid Mode",
                {profiloViewModel.columnMode()},
                {profiloViewModel.gridMode()},
                Icons.Default.ShoppingCart,
                Icons.Default.ShoppingCart
            )
             */
        }
    }
}

@ExperimentalAnimationApi
@Composable
@Preview
fun ProfiloScreenPreview() {
    ProfiloScreen(navController = rememberNavController(), PaddingValues(0.dp,0.dp),
        ProfiloViewModel(), ListeViewModel()
    )
}

@ExperimentalAnimationApi
@Composable
@Preview
fun SettingsTablePreview() {
    SettingsTable(
        title = "Impostazioni",
        iconTitle = Icons.Default.Settings,
        navController = rememberNavController(),
        ListeViewModel(),
        ProfiloViewModel()
    )
}


