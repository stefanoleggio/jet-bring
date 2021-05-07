package com.example.jet_bring.ui.ispirazione

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun IspirazioneScreen() {
    LazyColumn {
        // Add a single item
        item {
            Card(
                backgroundColor = MaterialTheme.colors.surface,

                ) {

                Text(
                    text = "Jetpack Compose",
                    color = MaterialTheme.colors.onSurface
                )
            }
        }


        // Add another single item
        item {
            Card {
                Text(
                    text = "Jetpack Compose",
                    color = MaterialTheme.colors.onSurface
                )
            }
        }
    }

}