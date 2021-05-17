package com.example.jet_bring.ui.liste
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.example.jet_bring.model.*

@ExperimentalFoundationApi
@Composable
fun ListeScreen(navController: NavHostController, listeViewModel: ListeViewModel) {
    Column() {
        MyProductsCard(listeViewModel)
        LazyColumn {
            items(listeViewModel.getCategories()) { category->
                CategoryCard(navController, category)
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MyProductsCard(listeViewModel: ListeViewModel) {
    Card(
        modifier = Modifier
            .padding(
                top = 30.dp,
                start = 10.dp,
                end = 10.dp,
                bottom = 15.dp
            )
            .fillMaxWidth()
            .defaultMinSize(minHeight = 100.dp),
        backgroundColor = MaterialTheme.colors.background,
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
        ) {
            if(listeViewModel.isSelectedProductsEmpty()) {
                Text(
                    text = "Aggiungi qualcosa!",
                    fontSize = 25.sp
                )
            } else {
                LazyVerticalGrid(
                    cells = GridCells.Adaptive(minSize = 100.dp),
                    modifier = Modifier
                        .padding(10.dp)

                ) {
                    items(listeViewModel.getSelectedProducts()) { product ->
                        ProductButton(product, removeSelectedProduct = true, listeViewModel)
                    }
                }
            }
        }
    }
}



