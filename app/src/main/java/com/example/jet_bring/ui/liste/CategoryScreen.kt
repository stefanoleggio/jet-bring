package com.example.jet_bring.ui.liste

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.jet_bring.model.Category
import com.example.jet_bring.model.Product
import com.example.jet_bring.ui.profilo.ProfiloViewModel

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
            .padding(top = 40.dp,
                bottom = scafPaddingValues
                    .calculateBottomPadding()
                    .plus(40.dp),
                start = 10.dp,
                end = 10.dp
            )
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProductModeSwitcher(listeViewModel,profiloViewModel,false,category)
    }
}