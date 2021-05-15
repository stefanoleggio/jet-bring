package com.example.jet_bring.ui.ispirazione.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
import androidx.navigation.compose.navigate
import com.example.jet_bring.R
import com.example.jet_bring.ui.ispirazione.ricette
import kotlin.math.min
import android.view.View
import android.view.Window

import androidx.core.content.ContextCompat

import android.view.WindowManager
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RicetteDetails(navController: NavHostController, ricettaId: String?) {

    val ricetta = ricette[ricettaId?.toInt()!!]
    val scrollState = rememberScrollState()




    if (ricetta != null) {


        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,


        ) {

            Image(
                painter = painterResource(id  = R.drawable.photo_1),
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(min(1f, 1 - (scrollState.value / 400f)))
                    .height(300.dp),
                contentScale = ContentScale.Crop,
                //contentScale = ContentScale.Crop,
                contentDescription = "",

                )
            ricetta.pubblicatore?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.h6
                )
            }
            ricetta.titolo?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.h4
                )
            }
            AlignInRow(ricetta)
            Card(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .padding(
                        bottom = 6.dp,
                        top = 6.dp,
                        start = 16.dp,
                        end = 16.dp

                    )
                    .fillMaxWidth()
                    .noRippleClickable {
                        null
                    },

                elevation = 0.dp,
                //backgroundColor = MaterialTheme.colors.background
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "2 Persone",
                        color = MaterialTheme.colors.onBackground,

                    )
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
                        Icon(Icons.Rounded.AddCircle, contentDescription = "Localized description", tint = MaterialTheme.colors.onBackground )

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
                        Icon(Icons.Rounded.Delete, contentDescription = "Localized description", tint = MaterialTheme.colors.onBackground )

                    }

                }


            }
            Text(text = "ciao\n\n\n\n\n\n\n\n\n\n\n\n\n\ncisaociao\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "cisaociao\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "cisao")
        }

        Button(
            onClick = { /* Do something! */ },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = Color.Transparent
            ),
            elevation = ButtonDefaults.elevation(
                defaultElevation = 0.dp,
                pressedElevation = 0.dp,
                disabledElevation = 0.dp
            ),
            //elevation = ButtonElevation.elevation(enabled = false, interactionSource = null )

        ) {
            Icon(Icons.Filled.Close, contentDescription = "Localized description", tint = MaterialTheme.colors.onBackground, modifier = Modifier.size(24.dp) )

        }
    }
}
