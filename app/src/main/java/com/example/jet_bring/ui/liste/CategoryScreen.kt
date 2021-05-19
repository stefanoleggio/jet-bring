package com.example.jet_bring.ui.liste

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jet_bring.model.Category

@ExperimentalFoundationApi
@Composable
fun CategoryScreen(
    navController: NavHostController,
    categoryId: Long,
    scafPaddingValues: PaddingValues,
    listeViewModel: ListeViewModel
)
{
    val category: Category = listeViewModel.getCategory(categoryId)
    val productsPerRow = category.products.chunked(listeViewModel.calculateColumnsNumber())

    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(top = 40.dp, bottom = 40.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.Start
            ) {
                for (products in productsPerRow) {
                    Row(
                    ) {
                        for (product in products) {
                            ProductButton(product, removeSelectedProduct = false, listeViewModel)
                        }
                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(scafPaddingValues.calculateBottomPadding()))
    }
}

