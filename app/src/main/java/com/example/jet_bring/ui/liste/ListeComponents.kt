package com.example.jet_bring.ui.liste

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
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
import com.example.jet_bring.ui.utils.MainInputText
import com.example.jet_bring.ui.utils.MainTextButton

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun ProductButton(
    product: Product,
    onButtonClick:(Product) -> Unit,
    onDescriptionChange: (Product, String) -> Unit,
    isSelected: (Product) -> Boolean,
    selectedColor: Color,
    unselectedColor: Color,

) {
    /*
    val color by animateColorAsState(
        targetValue = if (isSelected(product)) selectedColor else unselectedColor
    )*/
    val openDescriptionAlert = remember { mutableStateOf(false)}
    val description: String = if(product.description == null) " " else product.description!!
    val descriptionText = remember { mutableStateOf(description) }

    if(openDescriptionAlert.value) {
        DescriptionAlert(openDescriptionAlert,
            descriptionText,
            onDescriptionChange = onDescriptionChange,
            product = product)
    }

    Column(
        modifier = Modifier
            .size(width = 120.dp, height = 120.dp)
            .padding(2.dp)
            .clip(shape = RoundedCornerShape(5.dp))
            .background(if (isSelected(product)) selectedColor else unselectedColor /*color*/)
            .combinedClickable(
                onClick = {
                    onButtonClick(product)
                          },
                onLongClick = {openDescriptionAlert.value = true}
            )
            .padding(7.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        if(product.description == null || product.description == "") {
            Image(
                painter = painterResource(product.icon),
                contentDescription = null,
                modifier = Modifier
                    .height(75.dp),
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
            var currentDescription = product.description!!
            if(currentDescription.length > 10)
                currentDescription = currentDescription.substring(0, 10) + "..."
            Text(
                modifier = Modifier
                    .padding(top = 0.dp),
                text = currentDescription,
                fontSize = 12.sp,
                color = Color.White
            )
        }
    }

}


@ExperimentalComposeUiApi
@Composable
fun DescriptionAlert(
    openDescriptionAlert: MutableState<Boolean>,
    descriptionText: MutableState<String>,
    onDescriptionChange: (Product, String) -> Unit,
    product: Product
) {
    AlertDialog(
        onDismissRequest = {
            openDescriptionAlert.value = false
        },
        title = {
            Text("Modifica")
        },
        text = {
            Spacer(modifier = Modifier.height(2.dp))
            MainInputText(
                onTextChange = {
                    run { descriptionText.value = it }
                },
                text = descriptionText.value,
                label = "Descrizione")
        },
        confirmButton = {
            MainTextButton(
                onClick = {
                    openDescriptionAlert.value = false
                    onDescriptionChange(product, descriptionText.value)
                },
                isBackgroundSecondary = true,
                text = "Conferma"
            )
        },
        dismissButton = {
            MainTextButton(
                onClick = {
                    openDescriptionAlert.value = false
                },
                isBackgroundSecondary = false,
                text = "Chiudi"
            )
        }
    )
}

@Composable
fun CategoryCard(navController: NavHostController, category: Category) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                top = 4.dp
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
    onDescriptionChange: (Product, String) -> Unit,
    isSelected: (Product) -> Boolean,
    selectedColor: Color,
    unselectedColor: Color
) {
    Surface(shape= RoundedCornerShape(10.dp),) {
        Column() {
            var i = 0
            productsList.forEach() {
                item ->
                ProductRow(
                    product = item,
                    onButtonClick = onButtonClick,
                    onDescriptionChange = onDescriptionChange,
                    isSelected = isSelected,
                    selectedColor = selectedColor,
                    unselectedColor = unselectedColor
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
    onDescriptionChange: (Product, String) -> Unit,
    isSelected: (Product) -> Boolean,
    selectedColor: Color,
    unselectedColor: Color,
) {
    val color by  animateColorAsState(
        targetValue = if(isSelected(product)) selectedColor else unselectedColor
    )
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
@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.R)
@ExperimentalComposeUiApi
@Composable
fun ProductGridMode(productsList: List<Product>,
                    numOfColumns: Int,
                    onButtonClick:(Product) -> Unit,
                    onDescriptionChange: (Product, String) -> Unit,
                    isSelected: (Product) -> Boolean,
                    selectedColor: Color,
                    unselectedColor: Color

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
                       ProductButton(product,onButtonClick, onDescriptionChange, isSelected,selectedColor,unselectedColor) //ProductButton(product, removeSelectedProduct = isSelectedMode, listeViewModel)
                    }
                }
            }
        }
    }
}

/**
 * funzione di scelta fra griglia e colonna
 */
@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.R)
@ExperimentalComposeUiApi
@Composable
fun ProductModeSwitcher(productsList: List<Product>,
                        profiloViewModel: ProfiloViewModel,
                        onButtonClick: (Product) -> Unit,
                        onDescriptionChange: (Product, String) -> Unit,
                        isSelected: (Product) -> Boolean,
                        selectedColor: Color,
                        unselectedColor: Color
) {
    if (profiloViewModel.isColumnMode()){
        ProductColumnMode(
            productsList = productsList,
            onButtonClick = onButtonClick,
            onDescriptionChange = onDescriptionChange,
            isSelected = isSelected,
            selectedColor = selectedColor,
            unselectedColor = unselectedColor
        )
    } else {
        ProductGridMode(
            productsList = productsList,
            numOfColumns = profiloViewModel.calculateColumnsNumber(),
            onButtonClick = onButtonClick,
            onDescriptionChange = onDescriptionChange,
            isSelected = isSelected,
            selectedColor = selectedColor,
            unselectedColor = unselectedColor

        )
    }
}

@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.R)
@ExperimentalComposeUiApi
@Composable
@Preview
fun ProductButtonPreview() {

    val listeViewModel = ListeViewModel()

    listeViewModel.getProduct(0)?.let { ProductButton(it, {},{ product, string ->  }, {true }, Color.Blue,Color.Green) }
}

@Preview
@Composable
fun CategoryCardPreview() {
    CategoryCard(rememberNavController(), ListeViewModel().getCategory(1))
}

@Preview
@Composable
fun ProductRowPreview() {
    ProductRow(product = products[0],{},{product, string ->  }, {false},Color.Blue,Color.Green)
}

@Preview
@Composable
fun ProductColumnModePreview() {
    val list =
        listOf<Product>(
        products[1],
        products[6],
    )
    ProductColumnMode(list,{},{product, string ->  },{true},Color.Blue,Color.Green)
}