package com.example.jet_bring.ui.liste
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jet_bring.ui.profilo.ProfiloViewModel
import com.example.jet_bring.R
import com.example.jet_bring.ui.theme.*

/**
 * Funzione principale per Liste Screen
 * Vengono passati i due viewModel profiloViewModel e listeViewModel per accedere ai dati
 */
@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.R)
@ExperimentalComposeUiApi
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
            .padding(
                top = PADDING_TOP,
                bottom = PADDING_BOTTOM,
                start = PADDING_START,
                end = PADDING_END
            )
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

/**
 * Funzione che definisce la card che contiene i prodotti selezionati
 */
@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.R)
@ExperimentalComposeUiApi
@Composable
fun MyProductsCard(listeViewModel: ListeViewModel,profiloViewModel:ProfiloViewModel) {
    Column(
        modifier = Modifier
            .padding(bottom = 20.dp),
    ) {
        //Controllo se ci sono dei prodotti selezionati
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
                        style = MaterialTheme.typography.h1
                    )
                    Text(
                        text = "Sfoglia il nostro catalogo",
                        style = MaterialTheme.typography.body1
                    )
                }

            }
        } else {
            /**
             * Richiamo anche qui il ProductModeSwitcher per stampare i prodotti nella modalità selezionata (Column mode oppure Grid mode)
             */
            ProductModeSwitcher(
                listeViewModel.getSelectedProducts(),
                profiloViewModel,
                onButtonClick = {
                    product ->
                    listeViewModel.removeSelectedProduct(product.id)

                },
                onDescriptionChange = {
                    product, description ->
                    listeViewModel.setDescription(product.id, description)
                },
                { product-> listeViewModel.isInSelectedProduct(product.id) },
                Roman,
                BreakerBay
            )
        }
    }
}


