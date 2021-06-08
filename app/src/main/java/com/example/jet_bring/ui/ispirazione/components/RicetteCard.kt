package com.example.jet_bring.ui.ispirazione.components


import android.content.res.Configuration
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jet_bring.R
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.remember
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.jet_bring.model.Ricetta
import com.example.jet_bring.model.getProduct
import com.example.jet_bring.ui.ispirazione.paddingBasedOnOrientation

/**
 * Funzione che nasconde l'effetto di tocco sulla card della ricetta
 * */
inline fun Modifier.noRippleClickable(crossinline onClick: ()->Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

/**
 * 
 * Funzione che ritorna una card delle ricette
 *
 * */

@Composable
fun RicetteCard (
    ricetta: Ricetta,
    navController: NavHostController,
    route: String
) {

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
                navController.navigate("$route/${ricetta.id}") {
                }
            },


        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.background


    ){
        Column() {
            ricetta.pubblicatore?.let { titolo ->

                Text(
                    text = titolo,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start)
                        .padding(start = 5.dp, end = 5.dp),
                    fontSize = 15.sp
                )

            }
            ricetta.descrizione?.let { descrizione ->

                Text(
                    text = descrizione,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 5.dp, end = 5.dp),
                    fontSize = 15.sp

                )

            }
            ricetta.titolo?.let { titolo ->

                Text(
                    text = titolo,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start)
                        .padding(start = 5.dp, end = 5.dp, top = 5.dp, bottom = 3.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

            }

            ricetta.immagine?.let { url ->

                Image(
                    painter = painterResource(id = ricetta.immagine!!),
                    modifier = Modifier
                        .fillMaxWidth(0.97F)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .height(275.dp)
                        .align(Alignment.CenterHorizontally),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
            }
            AlignInRow(ricetta)

        }

        /**
         * Funzioni per disegnare elementi grafici
         * */
        Canvas(
            //Rettangolo grigio
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = paddingBasedOnOrientation().dp, top = 98.dp
                )){
            drawRect(

                color = Color.LightGray,
                size = androidx.compose.ui.geometry.Size(130F, 60F)
            )
            //Cerchio grigio con parametri del centro
            val canvasWidth = size.width
            val canvasHeight = size.height
            drawCircle(
                color = Color.LightGray,
                center = Offset(x = 136f , y = 30f),
                radius = 30.00f
            )
        }
        Text(text = "Ricetta" ,modifier = Modifier
            .fillMaxWidth()
            .padding(start = (paddingBasedOnOrientation()+4).dp, top = 99.dp),
            color = Color.Black,
            fontFamily = FontFamily.Cursive
        )

    }
}


