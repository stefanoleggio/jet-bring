package com.example.jet_bring.ui.ispirazione.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.remember
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.navigation.NavHostController
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.jet_bring.ui.liste.ListeScreen

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
                navController.navigate("$route/${ricetta.id}"){
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
                    style = MaterialTheme.typography.h6
                )

            }
            ricetta.titolo?.let { titolo ->

                Text(
                    text = titolo,
                    modifier = Modifier
                        .fillMaxWidth(0.85f)
                        .wrapContentWidth(Alignment.Start),
                    style = MaterialTheme.typography.h4
                )

            }


            ricetta.immagine?.let { url ->
                Image(
                    painter = painterResource(id = R.drawable.photo_1),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(16.dp))
                        .padding(top = 5.dp),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
            }
            Row(

            ) {
                Icon(Icons.Rounded.Search, contentDescription = "Localized description")
                Text(
                    text = "Vedi la ricetta",

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

