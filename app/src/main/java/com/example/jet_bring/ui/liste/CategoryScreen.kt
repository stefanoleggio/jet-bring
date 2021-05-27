package com.example.jet_bring.ui.liste

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jet_bring.model.Category
import com.example.jet_bring.model.Product
import com.example.jet_bring.ui.profilo.ProfiloViewModel
import com.example.jet_bring.ui.theme.*

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
            .padding(top = PADDING_TOP,
                bottom = scafPaddingValues
                    .calculateBottomPadding()
                    .plus(PADDING_BOTTOM),
                start = PADDING_START,
                end = PADDING_END
            )
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProductModeSwitcher(
            category.products,
            profiloViewModel,
            onButtonClick = {
                product ->
                    if (listeViewModel.containsSelectedProduct(product)) {
                        listeViewModel.removeSelectedProduct(product)
                    } else {
                        listeViewModel.addSelectedProduct(product)
                    }
            },
            onDescriptionChange = {
                    product, description ->
                listeViewModel.setDescription(product, description)
            },
            listeViewModel::containsSelectedProduct,
            Roman,
            BreakerBay
        )
    }

}
