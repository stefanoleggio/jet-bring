package com.example.jet_bring.ui.ispirazione.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.navigation.NavHostController
import com.example.jet_bring.R
import com.example.jet_bring.ui.ispirazione.ricette
import kotlin.math.min
@Composable
fun RicetteDetails(navController: NavHostController, ricettaId: String?) {

    val ricetta = ricette[ricettaId?.toInt()!!]
    val scrollState = rememberScrollState()

    if (ricetta != null) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)

        ) {

            Image(
                painter = painterResource(id = R.drawable.empty_plate),
                modifier = Modifier
                    //.fillMaxWidth()
                    .clip(shape = RoundedCornerShape(16.dp))
                    .alpha(min(1f, 1 - (scrollState.value / 400f))),
                //contentScale = ContentScale.Crop,
                contentDescription = "",

                )
            ricetta.pubblicatore?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6
                )
            }
            ricetta.titolo?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h4
                )
            }
            Row() {
                Icon(Icons.Rounded.Search, contentDescription = "Localized description")
                Text(
                    text = "Vedi la ricetta"
                )
                Icon(Icons.Rounded.Share, contentDescription = "Localized description")
                Text(
                    text = "Condividi"
                )
                Icon(Icons.Rounded.Favorite, contentDescription = "Localized description")
                Text(
                    text = ricetta.voti.toString()
                )

            }


        }


    }



}

