package com.example.jet_bring.ui.ispirazione.components

import android.os.Build
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import kotlin.math.min
import com.example.jet_bring.ui.theme.BreakerBay


import androidx.annotation.RequiresApi
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.text.font.FontWeight
import com.example.jet_bring.ui.liste.ListeViewModel
import com.example.jet_bring.ui.liste.ProductModeSwitcher
import com.example.jet_bring.ui.profilo.ProfiloViewModel

/*
@RequiresApi(Build.VERSION_CODES.R)
@Preview
@Composable
fun DetailsPreview() {
    val listeViewModel = ListeViewModel()
    val profileViewModel  = ProfiloViewModel()
    RicetteDetails(
        rememberNavController(),
        "0",
        listeViewModel,
        profileViewModel
    )
}


 */


@ExperimentalComposeUiApi
@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RicetteDetails(navController: NavHostController, ricettaId: Long,listeViewModel: ListeViewModel,profiloViewModel:ProfiloViewModel) {
    val ricetta = listeViewModel.getRicetta(ricettaId)
    val scrollState = rememberScrollState()
    val snackbarHostState = remember { SnackbarHostState() }

    if (ricetta != null) {

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,


        ) {


            ricetta.immagine?.let {
                Image(
                    painter = painterResource(id  = ricetta.immagine!!),
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(min(1f, 1 - (scrollState.value / 400f)))
                        .height(300.dp),
                    contentScale = ContentScale.Crop,
                    //contentScale = ContentScale.Crop,
                    contentDescription = "",

                    )
            }

            ricetta.pubblicatore?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .padding(start = 5.dp, end = 5.dp, top = 15.dp, bottom = 5.dp),
                )
            }
            ricetta.titolo?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier

                        .padding(start = 5.dp, end = 5.dp, top = 5.dp, bottom = 15.dp),
                    fontWeight = FontWeight.Bold,
                )

            }
            AlignInRow(ricetta)
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .padding(
                        bottom = 20.dp,
                        top = 6.dp,
                        start = 5.dp,
                        end = 5.dp

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
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Text(
                        text = "${ricetta.persone} Persone",
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .padding(start = 5.dp)


                    )
                }


            }


            /**
             * Generazione della lista degli ingredienti
             * */
            ProductModeSwitcher(
                listeViewModel.getRicetta(ricettaId).ingredienti,
                profiloViewModel,
                onButtonClick = {product ->
                    if(listeViewModel.isInSelectedRicettaList(product.id,ricettaId))
                        listeViewModel.removeFromSelectedRicetta(product.id,ricettaId)
                    else
                        listeViewModel.addToSelectedRicetta(product.id,ricettaId)
                    Log.d(null,"${listeViewModel.getSelectedRicetta(ricettaId).ingredienti}")
                },
                onDescriptionChange = {
                        product, description ->
                    listeViewModel.setRicettaProductDescription(product.id, ricettaId, description)
                },
                {product -> listeViewModel.isInSelectedRicettaList(product.id,ricettaId)},
                BreakerBay,
                MaterialTheme.colors.primary,
            )


            Spacer(modifier = Modifier.height(150.dp))


            Button(
                shape = MaterialTheme.shapes.medium,
                onClick = {

                    listeViewModel.addselectedRicettaListToSelectedProducts(ricettaId)
                    getBack(
                        ricettaId,
                        listeViewModel::resetRicetta,
                    ) {


                        navController.navigate("liste") {
                            popUpTo = navController.graph.startDestination
                            launchSingleTop = true
                        }
                    }

                          },
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = BreakerBay
                ),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 3.dp,
                    pressedElevation = 3.dp,
                    disabledElevation = 3.dp
                ),
                modifier = Modifier
                    .padding(
                        bottom = 6.dp,
                        top = 6.dp,
                        start = 6.dp,
                        end = 6.dp

                    )
                    .fillMaxWidth()
                    .height(70.dp)

                //elevation = ButtonElevation.elevation(enabled = false, interactionSource = null )

            ) {
                Text(
                    text = "Aggiungi ${listeViewModel.getSelectedRicetta(ricettaId).ingredienti.size.toString()} ingredienti",
                    color = MaterialTheme.colors.onBackground

                )

            }

            Spacer(modifier = Modifier.height(50.dp))
        }

        /**
         * Tasto chiusura schermata
         * */
        Button(
            onClick = { getBack(ricettaId,listeViewModel::resetRicetta,navController::popBackStack) },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
            ),
            modifier = Modifier.padding(8.dp).width(51.dp).height(51.dp)

            //elevation = ButtonElevation.elevation(enabled = false, interactionSource = null )

        ) {
            Icon(Icons.Rounded.Close, contentDescription = "", tint = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize() )

        }
    }
}

/**
 * Funzione che mi riporta alla schermata principale una volta aggiunti gli ingrdienti alla lista
 */
fun getBack(ricettaId: Long, resetRicetta: (ricettaId: Long) -> Unit,goto: () -> Unit) {
    resetRicetta(ricettaId)
    goto()
}
