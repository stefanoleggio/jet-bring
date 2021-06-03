package com.example.jet_bring.ui.ispirazione

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.jet_bring.R
import com.example.jet_bring.model.*
import com.example.jet_bring.ui.ispirazione.components.RicetteCard




@Composable
fun IspirazioneScreen(navController: NavHostController, scafPaddingValues: PaddingValues, addRicettaViewModel: AddRicettaViewModel) {





    Column(
        modifier = Modifier.verticalScroll(rememberScrollState()),
    ){
        ricette.forEach { ricetta ->
            RicetteCard(ricetta = ricetta, navController,"ispirazione/ricetteDetails")
        }
        Spacer(modifier = Modifier.height(scafPaddingValues.calculateBottomPadding()))
    }

}

