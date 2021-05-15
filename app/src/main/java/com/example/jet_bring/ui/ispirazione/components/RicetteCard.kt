package com.example.jet_bring.ui.ispirazione.components


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
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.jet_bring.ui.ispirazione.ricette
import com.example.jet_bring.ui.liste.ListeScreen
import kotlin.math.min

/**Sezione di navigazione*/
object MainDestinations {
    const val MAINSCREEN = "mainscreen"
    const val CHILDSCREEN = "childscreen"
    const val CHILD_TITLE_KEY = "childscreen_titletkey"
}


inline fun Modifier.noRippleClickable(crossinline onClick: ()->Unit): Modifier = composed {
    clickable(indication = null,
        interactionSource = remember { MutableInteractionSource() }) {
        onClick()
    }
}

/**
 * Funzione che ritorna una card delle ricette
 *
 * */


@Composable
fun RicetteCard (
    ricetta: Ricetta,
    navController: NavHostController,
    route: String
) {

    val interactionSource = remember { MutableInteractionSource() }
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
                        .wrapContentWidth(Alignment.Start),
                    fontSize = 15.sp
                )

            }
            ricetta.descrizione?.let { descrizione ->

                Text(
                    text = descrizione,
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontSize = 15.sp

                )

            }
            ricetta.titolo?.let { titolo ->

                Text(
                    text = titolo,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start),
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

            }

            val image = "R.drawable." + ricetta.immagine
            ricetta.immagine?.let { url ->
                Image(
                    painter = painterResource(id = ricetta.immagine!!),
                    modifier = Modifier
                        .fillMaxWidth(0.97F)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .height(265.dp)
                        .padding(top = 5.dp)
                        .align( Alignment.CenterHorizontally ),

                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
            }
            AlignInRow(ricetta)

        }

    }
}


