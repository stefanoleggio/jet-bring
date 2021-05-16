package com.example.jet_bring.ui.liste

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jet_bring.model.Category
import com.example.jet_bring.model.Product
import com.example.jet_bring.ui.theme.BreakerBay
import com.example.jet_bring.ui.theme.Roman

@ExperimentalFoundationApi
@Composable
fun CategoryScreen(
    navController: NavHostController,
    categoryId: Long,
    listeViewModel: ListeViewModel
)
{
    val category: Category = listeViewModel.getCategory(categoryId)
    LazyVerticalGrid(
        cells = GridCells.Adaptive(minSize = 120.dp),
        modifier = Modifier
            .padding(10.dp)

    ) {
        items(category.products) { product ->
            val isProductSelected = rememberSaveable { mutableStateOf(false) }
            ProductButton(product, isProductSelected, removeSelectedProduct = false, listeViewModel)
        }
    }
}

@Composable
fun ProductButton(
    product: Product,
    isProductSelected: MutableState<Boolean>,
    removeSelectedProduct: Boolean,
    listeViewModel: ListeViewModel
) {

    val color = if(removeSelectedProduct) { if(!isProductSelected.value) Roman else BreakerBay } else { if(!isProductSelected.value) BreakerBay else Roman }

    Column(
        modifier = Modifier
            .padding(2.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .defaultMinSize(minHeight = 120.dp)
            .background(color)
            .clickable {

                if(removeSelectedProduct) {
                    listeViewModel.removeSelectedProduct(product)
                }
                else {
                    if(!isProductSelected.value)
                        listeViewModel.addSelectedProduct(product)
                    else
                        listeViewModel.removeSelectedProduct(product)
                }
                isProductSelected.value = !isProductSelected.value

            },
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Row() {
                Image(
                    painter = painterResource(product.icon),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(product.name)

            }
        }
    }
}