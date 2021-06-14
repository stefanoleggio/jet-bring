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
import androidx.compose.material.icons.filled.Settings
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.jet_bring.R
import com.example.jet_bring.ui.theme.*

@ExperimentalAnimationApi
@Composable
fun ProfiloScreen(
    navController: NavHostController,
    scafPaddingValues: PaddingValues,
    profiloViewModel: ProfiloViewModel,
) {

    val fontSize = 24.sp
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                scafPaddingValues.calculateStartPadding(LayoutDirection.Ltr).plus(PADDING_START),
                scafPaddingValues.calculateTopPadding().plus(PADDING_TOP),
                scafPaddingValues.calculateEndPadding(LayoutDirection.Ltr).plus(PADDING_END),
                scafPaddingValues.calculateBottomPadding().plus(PADDING_BOTTOM)
            )
        ) {

            item{Text(
                "Hi, ${profiloViewModel.user.name}",
                style = TextStyle(color = MaterialTheme.colors.onBackground, fontSize = fontSize )
            )}
            item{Spacer(Modifier.size(PADDING_TOP))}

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
                        elevation = 4.dp
                        ) {
                        Image(
                            painterResource(profiloViewModel.user.profileIcon),
                            contentDescription = null
                        )

                    }
                    Spacer(modifier = Modifier.padding(PADDING_TOP/2))
                    Text(text = profiloViewModel.user.name,
                    color = MaterialTheme.colors.onBackground
                        )
                    Text(text = profiloViewModel.user.email,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
            item {Spacer(Modifier.size(PADDING_TOP))}

            item{SettingsTable(tableDataList.get(0).title,tableDataList.get(0).iconTitle,profiloViewModel)

            }
            item{Spacer(Modifier.padding(PADDING_TOP))}

            items(items = tableDataList.subList(1, tableDataList.size)) {
                Table(it.title,it.iconTitle,navController,it.list)
                Spacer(Modifier.padding(PADDING_TOP))
            }

        }
}

@ExperimentalAnimationApi
@Composable
fun SettingsTable(
    title: String,
    iconTitle: ImageVector,
    profiloViewModel: ProfiloViewModel
)   {
    var expandedChoice by remember { mutableStateOf<String?>(null) }
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape= RoundedCornerShape(10.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    ) {
        Column(Modifier.animateContentSize() ) {
            HeaderBox(title = title, icon = iconTitle)
            Spacer(modifier = Modifier.padding(PADDING_TOP/4))
            Surface(
                Modifier
                    .padding(PaddingValues(
                        start = PADDING_START,
                        end = PADDING_END
                    )),
                color = MaterialTheme.colors.background
            ){
                Spacer( modifier = Modifier
                    .padding(1.dp)
                    .fillMaxWidth()
                )
            }

            TwoButtonsRow(
                "Aspetto lista",
                "Tema",
                {
                    if(expandedChoice.equals("aspettoDellaLista")) expandedChoice = null
                    else   expandedChoice = "aspettoDellaLista"
                },
                {
                    if(expandedChoice.equals("tema")) expandedChoice = null
                    else   expandedChoice = "tema"
                },
                R.drawable.ic_pixels,
                R.drawable.ic_half_circle
            )
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
                        selectedState = themes.indexOf(profiloViewModel.getSelectedTheme()),
                        onStateChange = profiloViewModel::setTheme,
                        states = profiloViewModel.getThemeList(),
                        modifier = Modifier.padding(start = PADDING_START, end = PADDING_END)
                    )
                }

            }
            Spacer(modifier = Modifier.padding(LINE_SPACE))
        }
    }
}

@ExperimentalAnimationApi
@Composable
@Preview
fun ProfiloScreenPreview() {
    ProfiloScreen(navController = rememberNavController(), PaddingValues(0.dp,0.dp),
        ProfiloViewModel()
    )
}

@ExperimentalAnimationApi
@Composable
@Preview
fun SettingsTablePreview() {
    SettingsTable(
        title = "Impostazioni",
        iconTitle = Icons.Default.Settings,
        ProfiloViewModel()
    )
}


