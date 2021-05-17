package com.example.jet_bring.ui.liste

import androidx.compose.foundation.*
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
    scafPaddingValues: PaddingValues,
    listeViewModel: ListeViewModel
)
{
    val category: Category = listeViewModel.getCategory(categoryId)
    val productsPerRow = category.products.chunked(listeViewModel.calculateColumnsNumber())

    Column(
        Modifier.verticalScroll(rememberScrollState())
            .padding(top = 40.dp, bottom = 40.dp, start = 10.dp, end = 10.dp)
            .wrapContentSize(Alignment.CenterEnd)
    ) {
        Column {
            for (products in productsPerRow) {
                Row() {
                    for (product in products) {
                        ProductButton(product, removeSelectedProduct = false, listeViewModel)
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(scafPaddingValues.calculateBottomPadding()))
    }
}