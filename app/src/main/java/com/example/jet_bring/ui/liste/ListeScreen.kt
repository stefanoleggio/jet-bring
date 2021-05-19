package com.example.jet_bring.ui.liste
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
fun ListeScreen(
    navController: NavHostController,
    scafPaddingValues: PaddingValues,
    listeViewModel: ListeViewModel )
{
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(top = 40.dp, bottom = 40.dp, start = 10.dp, end = 10.dp)
            .wrapContentSize(Alignment.CenterEnd)
    ) {
        MyProductsCard(listeViewModel)
        Column {
            for(category in listeViewModel.getCategories()) {
                CategoryCard(navController, category)
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun MyProductsCard(listeViewModel: ListeViewModel) {
    Column(
        modifier = Modifier
            .padding(bottom = 20.dp),
    ) {
        if (listeViewModel.isSelectedProductsEmpty()) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 100.dp),
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    Text(
                        text = "Niente da comprare",
                        fontSize = 25.sp
                    )
                    Text(
                        text = "Sfoglia il nostro catalogo",
                        fontSize = 15.sp
                    )
                }

            }
        } else {
            val selectedProducts = listeViewModel.getSelectedProducts()
            val columnsNumber = listeViewModel.calculateColumnsNumber()
            val productsPerRow = selectedProducts.chunked(columnsNumber)
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = if(selectedProducts.size < columnsNumber) Alignment.Start else Alignment.CenterHorizontally
            ) {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    for (products in productsPerRow) {
                        Row() {
                            for (product in products) {
                                ProductButton(product, removeSelectedProduct = true, listeViewModel)
                            }
                        }
                    }
                }
            }
        }
    }
}



