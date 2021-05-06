package com.example.jet_bring.ui.liste

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.jet_bring.darkGrey

@Composable
fun ListeScreen() {
    Text(text = "Liste",
        style = TextStyle(color = MaterialTheme.colors.primary, fontSize = 36.sp),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(darkGrey))
    LazyColumn {

        // Add a single item
        item {
            Text(
                text = "First item",
                style = TextStyle(color = Color.Black, fontSize = 36.sp),
            )

        }

        // Add 5 items
        items(5) { index ->
            Text(
                text = "Item: $index",
                style = TextStyle(color = Color.Black, fontSize = 36.sp),
            )
        }

        // Add another single item
        item {
            Text(
                text = "Last item",
                style = TextStyle(color = Color.Black, fontSize = 36.sp),
            )
        }
    }

}