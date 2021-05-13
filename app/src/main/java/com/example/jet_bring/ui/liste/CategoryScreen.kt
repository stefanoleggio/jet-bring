package com.example.jet_bring.ui.liste

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jet_bring.R
import com.example.jet_bring.model.Category
import com.example.jet_bring.model.CategoryRecovery
import com.example.jet_bring.ui.theme.BreakerBay
import java.util.*

@ExperimentalFoundationApi
@Composable
fun CategoryScreen(navController: NavHostController, categoryId: Long) {
    val category: Category = CategoryRecovery.getCategory(categoryId)
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        modifier = Modifier
            .padding(10.dp)
    ) {
        items(category.products) { product ->
            Card(
                modifier = Modifier
                    .padding(2.dp)
                    .clickable {

                    },
                    backgroundColor = BreakerBay,
                    shape = RoundedCornerShape(4.dp),
                    elevation = 5.dp

            ){
                Column(modifier = Modifier.padding(10.dp)) {
                    Row() {
                        Image(
                            painter = painterResource(product.icon),
                            contentDescription = null
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(top = 10.dp)
                            .align(CenterHorizontally)
                    ) {
                        Text(product.name)
                    }
                }

            }
        }
    }
}