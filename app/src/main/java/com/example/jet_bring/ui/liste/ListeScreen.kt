package com.example.jet_bring.ui.liste
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.jet_bring.ui.profilo.ProfiloViewModel
import com.example.jet_bring.R
import com.example.jet_bring.ui.theme.PADDING_BOTTOM
import com.example.jet_bring.ui.theme.PADDING_END
import com.example.jet_bring.ui.theme.PADDING_START
import com.example.jet_bring.ui.theme.PADDING_TOP

@Composable
fun ListeScreen(
    navController: NavHostController,
    scafPaddingValues: PaddingValues,
    listeViewModel: ListeViewModel,
    profiloViewModel: ProfiloViewModel)
{
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(top = PADDING_TOP, bottom = PADDING_BOTTOM, start = PADDING_START, end = PADDING_END)
            .wrapContentSize(Alignment.CenterEnd)
            .padding(scafPaddingValues)
    ) {
        MyProductsCard(listeViewModel,profiloViewModel)
        Column {
            for(category in listeViewModel.getCategories()) {
                CategoryCard(navController, category)
            }
        }
    }
}

@Composable
fun MyProductsCard(listeViewModel: ListeViewModel,profiloViewModel:ProfiloViewModel) {
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
                ) {
                    Image(
                        painter = painterResource(R.drawable.liste_box),
                        contentDescription = null,
                        modifier = Modifier.size(85.dp)
                    )
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
            ProductModeSwitcher(
                listeViewModel.getSelectedProducts(),
                profiloViewModel,
                listeViewModel::removeSelectedProduct,
                listeViewModel::containsSelectedProduct
            )
        }
    }
}


