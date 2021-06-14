package com.example.jet_bring.ui.liste

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.jet_bring.model.Category
import com.example.jet_bring.ui.profilo.ProfiloViewModel
import com.example.jet_bring.ui.theme.*


/**
 * Funzione che definisce lo screen della categoria
 * L'id della categoria viene passato come parametro dal navigation
 */
@ExperimentalComposeUiApi
@RequiresApi(Build.VERSION_CODES.R)
@ExperimentalFoundationApi
@Composable
fun CategoryScreen(
    navController: NavHostController,
    categoryId: Long,
    scafPaddingValues: PaddingValues,
    listeViewModel: ListeViewModel,
    profiloViewModel: ProfiloViewModel
)
{
    val category: Category = listeViewModel.getCategory(categoryId)

    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .padding(
                top = PADDING_TOP,
                bottom = scafPaddingValues
                    .calculateBottomPadding()
                    .plus(PADDING_BOTTOM),
                start = PADDING_START,
                end = PADDING_END
            )
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        /**
         * Visualizzo i prodotti a seconda della modalità selezionata nel ProfiloViewModel
         */
        ProductModeSwitcher(
            category.products,
            profiloViewModel,
            onButtonClick = {
                product ->

                    if (listeViewModel.isInSelectedProduct(product.id)) {
                        listeViewModel.removeSelectedProduct(product.id)
                    } else {
                        listeViewModel.addSelectedProduct(product)
                    }
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
