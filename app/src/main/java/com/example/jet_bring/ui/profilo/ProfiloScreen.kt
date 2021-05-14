package com.example.jet_bring.ui.profilo


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.example.jet_bring.ui.theme.JetbringTheme
val padding = 16.dp
@Composable
fun ProfiloScreen(
    navController: NavHostController,
    scafPaddingValues: PaddingValues
) {
    val fontSize = 24.sp
    val name = "Ciccio"

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
                "Hi, ${name}",
                style = TextStyle(color = MaterialTheme.colors.onBackground, fontSize = fontSize )
            )}
            item{Spacer(Modifier.size(padding))}

            item {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        navController.navigate("profilo/ilTuoProfilo") {
                            popUpTo = navController.graph.startDestination
                        }
                    },
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
                }
            }
            item {Spacer(Modifier.size(padding))}
            items(items = tableDataList) {
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
/*
@Composable
@Preview
fun ScreenPreview() {
    ProfiloScreen(navController = rememberNavController(), PaddingValues(0.dp,0.dp))
}
*/

