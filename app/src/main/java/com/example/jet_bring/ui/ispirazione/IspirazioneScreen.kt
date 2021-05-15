package com.example.jet_bring.ui.ispirazione

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.ui.foundation.VerticalScroller
import com.example.jet_bring.R
import com.example.jet_bring.ui.ispirazione.components.Ricetta
import com.example.jet_bring.ui.ispirazione.components.RicetteCard

val ricette = arrayOf(
    Ricetta(0,"Pasta", "Carlotta e il bassotto", R.drawable.photo_1, "", 5, "Sano e delizioso", listOf("piselli", "zucchine"), "2/2/2010"),
    Ricetta(1,"Sfoglia di fragole", "Misya", R.drawable.photo_2, "", 502, "Rende di buon umore",  listOf("broccoli", "pasta"), "2/2/2010"),
    Ricetta(2,"Pasta", "RicetteMagazine", R.drawable.photo_1, "", 5, "La pasta piu' buona", listOf("cipolla", "pasta"), "2/2/2010"),
    Ricetta(3,"Pasta", "RicetteMagazine", R.drawable.photo_1, "", 7, "La pasta piu' buona",  listOf("broccoli", "pasta"), "2/2/2010"),
)


@Composable
fun IspirazioneScreen(navController: NavHostController, scafPaddingValues: PaddingValues) {



    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
    ){
        ricette.forEach { ricetta ->
            RicetteCard(ricetta = ricetta, navController,"ispirazione/ricetteDetails")
        }
        Spacer(modifier = Modifier.height(scafPaddingValues.calculateBottomPadding()))



    }

}

