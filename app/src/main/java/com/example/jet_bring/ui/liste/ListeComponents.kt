package com.example.jet_bring.ui.liste

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.example.jet_bring.model.Category
import com.example.jet_bring.model.Product
import com.example.jet_bring.ui.theme.BreakerBay
import com.example.jet_bring.ui.theme.Roman

@Composable
fun ProductButton(
    product: Product,
    removeSelectedProduct: Boolean,
    listeViewModel: ListeViewModel
) {

    val selected = listeViewModel.containsSelectedProduct(product)

    val color = if(selected) Roman else BreakerBay

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
                    if(selected)
                        listeViewModel.removeSelectedProduct(product)
                    else
                        listeViewModel.addSelectedProduct(product)
                }
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

@Composable
fun CategoryCard(navController: NavHostController, category: Category) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                top = 4.dp,
                start = 10.dp,
                end = 10.dp
            )
            .clickable {
                navController.navigate("liste/" + category.id.toString()) {
                    //popUpTo = navController.graph.startDestination
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
                        text = category.name,
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

/**
 * funzione per istanziare prodotti su riga singola
 */
@Composable
fun productColumnMode() {}

/**
 * funzione per istanziare prodotti su griglia
 */
@Composable
fun productGridMode() {}

/**
 * funzione di scelta fra griglia e colonna
 */
@Composable
fun productModeSwitcher() {}