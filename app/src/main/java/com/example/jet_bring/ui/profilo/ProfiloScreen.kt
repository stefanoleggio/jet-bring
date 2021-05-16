package com.example.jet_bring.ui.profilo


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
import androidx.compose.runtime.Composable
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
import com.example.jet_bring.R
import com.example.jet_bring.ui.theme.JetbringTheme
val padding = 16.dp
@Composable
fun ProfiloScreen(
    navController: NavHostController,
    scafPaddingValues: PaddingValues,
    profiloViewModel: ProfiloViewModel
) {
    val fontSize = 24.sp
    JetbringTheme() {
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

            item{SettingsTable(tableDataList.get(0).title,tableDataList.get(0).iconTitle,navController)

            }
            item{Spacer(Modifier.padding(padding))}
            items(items = tableDataList.subList(1, tableDataList.lastIndex)) {
                Table(it.title,it.iconTitle,navController)
                Spacer(Modifier.padding(padding))
            }

        }
        /*
    Text(
        text = "Profilo",
        style = TextStyle(color = MaterialTheme.colors.onBackground, fontSize = 36.sp),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    )
     */
    }
}

@Composable
fun SettingsTable(
    title: String,
    iconTitle: ImageVector,
    navController: NavHostController
)   {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape= RoundedCornerShape(10.dp),
        color = MaterialTheme.colors.surface

    ) {
        Column {
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

            TwoButtonsRow(
                "Aspetto della lista",
                "Tema",
                {},
                {},
                Icons.Default.ShoppingCart,
                Icons.Default.ShoppingCart
            ) /*TODO aggiungere azioni bottoni e icone corrette*/
            ClickableBox(title = "Impostazioni Lista",
                navController = rememberNavController(),
                route = "casamia",
                Icons.Default.List
            )
            ClickableBox(title = "Altre Impostazioni",
                navController = rememberNavController(),
                route = "casatua"
            )

        }
    }
}

@Composable
@Preview
fun ProfiloScreenPreview() {
    ProfiloScreen(navController = rememberNavController(), PaddingValues(0.dp,0.dp),
        ProfiloViewModel()
    )
}

@Composable
@Preview
fun SettingsTablePreview() {
    SettingsTable(
        title = "Impostazioni",
        iconTitle = Icons.Default.Settings,
        navController = rememberNavController()
    )
}


