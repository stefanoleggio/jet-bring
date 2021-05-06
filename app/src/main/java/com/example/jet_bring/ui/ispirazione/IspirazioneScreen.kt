package com.example.jet_bring.ui.ispirazione

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.jet_bring.lightGrey

@Composable
fun IspirazioneScreen() {
    LazyColumn {
        // Add a single item
        item {
            Card(
                backgroundColor = lightGrey,

                ) {

                Text(
                    text = "Jetpack Compose"
                )
            }
        }


        // Add another single item
        item {
            Card {
                Text(
                    text = "Jetpack Compose"
                )
            }
        }
    }

}