package com.example.jet_bring.ui.ispirazione.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Link
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AlignInRow(ricetta: Ricetta,) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {




        Button(
            onClick = { /* Do something! */ },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = MaterialTheme.colors.background,
            ),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),

            ) {
            Icon(Icons.Rounded.Link, contentDescription = "Localized description", tint = MaterialTheme.colors.onBackground)
            Text(
                text = "Vedi la ricetta",
                color = MaterialTheme.colors.onBackground
            )
        }
        Button(
            onClick = { /* Do something! */ },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = MaterialTheme.colors.background
            ),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            //elevation = ButtonElevation.elevation(enabled = false, interactionSource = null )

        ) {
            Icon(Icons.Rounded.Share, contentDescription = "Localized description", tint = MaterialTheme.colors.onBackground )
            Text(
                text = "Condividi",
                color = MaterialTheme.colors.onBackground
            )
        }
        Button(onClick = { /* Do something! */ },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = MaterialTheme.colors.background,
            ),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
        ) {
            Icon(Icons.Rounded.Favorite, contentDescription = "Localized description", tint = MaterialTheme.colors.onBackground)
            Text(
                text = ricetta.voti.toString(),
                color = MaterialTheme.colors.onBackground
            )
        }



    }
}
