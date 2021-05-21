package com.example.jet_bring.ui.liste

import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.jet_bring.model.Category
import com.example.jet_bring.model.Product
import com.example.jet_bring.model.products
import com.example.jet_bring.ui.profilo.ProfiloViewModel
import com.example.jet_bring.ui.theme.BreakerBay
import com.example.jet_bring.ui.theme.Roman

@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun ProductButton(
    product: Product,
    onButtonClick:(Product) -> Unit,
    isSelected: (Product) -> Boolean
) {
    val color by  animateColorAsState(targetValue = if(isSelected(product)) Roman else BreakerBay)

    Column(
        modifier = Modifier
            .size(width = 120.dp, height = 120.dp)
            .padding(2.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(color)
            .clickable {
                onButtonClick(product)
            }
            .padding(7.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        if(product.description == null) {
            Image(
                painter = painterResource(product.icon),
                contentDescription = null,
                modifier = Modifier
                    .height(80.dp),
            )
            Text(
                modifier = Modifier
                    .padding(top = 5.dp),
                text = product.name,
                color = Color.White
            )
        } else {
            Image(
                painter = painterResource(product.icon),
                contentDescription = null,
                modifier = Modifier
                    .height(60.dp),
            )

            Text(
                modifier = Modifier
                    .padding(top = 5.dp, bottom = 0.dp),
                text = product.name,
                color = Color.White
            )
            Text(
                modifier = Modifier
                    .padding(top = 0.dp),
                text = product.description,
                fontSize = 12.sp,
                color = Color.White
            )
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
fun ProductColumnMode(
    productsList: List<Product>,
    onButtonClick:(Product) -> Unit,
    isSelected: (Product) -> Boolean
) {
    Surface(shape= RoundedCornerShape(10.dp),) {
        Column() {
            var i = 0
            productsList.forEach() {
                item ->
                ProductRow(
                    product = item,
                    onButtonClick = onButtonClick,
                    isSelected = isSelected
                )
                if((i < productsList.size - 1) && (i>=0)) {
                    Spacer(
                        modifier = Modifier
                            .padding(2.dp)
                            .background(MaterialTheme.colors.background)
                    )
                }
                i++
            }
        }
    }
}
/*TODO sistemare altezza riga, aggiungere bottone per modificare item*/
@Composable
fun ProductRow(
    product: Product,
    onButtonClick: (Product) -> Unit,
    isSelected: (Product) -> Boolean
) {
    val color by  animateColorAsState(targetValue = if(isSelected(product)) Roman else BreakerBay)//

    Row(modifier = Modifier
        .fillMaxWidth()
        .background(color)
        .padding(2.dp)
        .clickable {
            onButtonClick(product)
        },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(product.icon),
            contentDescription = null,
            contentScale = ContentScale.Inside,
            modifier = Modifier.size(64.dp) /*TODO sostituire con valore fissato in layout class*/
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(
            text = product.name,
            Modifier.weight(8f),
            color = MaterialTheme.colors.onSurface
            )
    }


}

/**
 * funzione per istanziare prodotti su griglia
 */
@Composable
fun ProductGridMode(productsList: List<Product>,
                    numOfColumns: Int,
                    onButtonClick:(Product) -> Unit,
                    isSelected: (Product) -> Boolean
) {
    val productsPerRow = productsList.chunked(numOfColumns)
    Column(

        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
        /*if(listeViewModel.getSelectedProducts().size < listeViewModel.calculateColumnsNumber())
            Alignment.Start
        else Alignment.CenterHorizontally*/
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            for (products in productsPerRow) {
                Row() {
                    for (product in products) {
                       ProductButton(product,onButtonClick,isSelected) //ProductButton(product, removeSelectedProduct = isSelectedMode, listeViewModel)
                    }
                }
            }
        }
    }
}

/**
 * funzione di scelta fra griglia e colonna
 */
@Composable
fun ProductModeSwitcher(productsList: List<Product>,
                        profiloViewModel: ProfiloViewModel,
                        onButtonClick: (Product) -> Unit,
                        isSelected: (Product) -> Boolean
) {
    if (profiloViewModel.isColumnMode()){
        ProductColumnMode(
            productsList = productsList,
            onButtonClick = onButtonClick,
            isSelected = isSelected
        )
    } else {
        ProductGridMode(
            productsList = productsList,
            numOfColumns = profiloViewModel.calculateColumnsNumber(),
            onButtonClick = onButtonClick,
            isSelected = isSelected
        )
    }
}

@Preview
@Composable
fun ProductButtonPreview() {

    val listeViewModel = ListeViewModel()

    ProductButton(listeViewModel.getProduct(0), {}, {true })
}

@Preview
@Composable
fun CategoryCardPreview() {
    CategoryCard(rememberNavController(), ListeViewModel().getCategory(1))
}

@Preview
@Composable
fun ProductRowPreview() {
    ProductRow(product = products[0],{},{false})
}

@Preview
@Composable
fun ProductColumnModePreview() {
    val list =
        listOf<Product>(
        products[1],
        products[6],
    )
    ProductColumnMode(list,{},{true})
}