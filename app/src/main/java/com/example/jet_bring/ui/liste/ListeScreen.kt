package com.example.jet_bring.ui.liste
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.popUpTo

@Composable
fun ListeScreen(navController: NavHostController) {
    LazyColumn {
        // Add a single item
        item {
            MyProducts()
            CategoryCard(navController, "Frutta e verdura","liste/frutta-e-verdura")
            CategoryCard(navController, "Profilo","profilo")
        }
    }
}

@Composable
fun MyProducts() {
    Card(
        modifier = Modifier
            .padding(
                top = 30.dp,
                start = 10.dp,
                end = 10.dp,
                bottom = 15.dp
            )
            .fillMaxWidth()
            .height(100.dp),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp

    ) {
        Column(
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
        ) {
            Text(
                text = "Aggiungi qualcosa!",
                fontSize = 25.sp
            )
        }
    }
}


@Composable
fun CategoryCard(navController: NavHostController, title: String, route: String) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                top = 2.dp,
                start = 10.dp,
                end = 10.dp
            )
            .clickable {
                navController.navigate(route){
                    popUpTo = navController.graph.startDestination
                }

            }
            .fillMaxWidth(),
        elevation = 8.dp,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            ) {
            Row() {
                Column(
                ) {
                    Text(
                        text = title,
                        Modifier.padding(10.dp),
                        style = TextStyle(color = MaterialTheme.colors.onBackground, fontSize = 25.sp),
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.CenterEnd)
                ) {
                    Icon(
                        Icons.Rounded.KeyboardArrowRight,
                        contentDescription = "Expand",
                        modifier = Modifier
                        .size(60.dp)
                        .padding(5.dp)
                    )
                }
            }
        }
    }
}

