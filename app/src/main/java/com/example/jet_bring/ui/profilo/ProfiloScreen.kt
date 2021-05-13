package com.example.jet_bring.ui.profilo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ProfiloScreen(navController: NavHostController,scafPaddingValues: PaddingValues) {
    LazyColumn(itemPadding = scafPaddingValues) {

        item() {Text(
            text = "Profilo",
            style = TextStyle(color = MaterialTheme.colors.onBackground, fontSize = 36.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        )
    }
    }
    items(20) {
        Text(
            text = "Profilo $it",
            style = TextStyle(color = MaterialTheme.colors.onBackground, fontSize = 36.sp),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        )
    }
}