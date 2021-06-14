package com.example.jet_bring.ui.ispirazione

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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import kotlin.math.min
import androidx.annotation.RequiresApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.jet_bring.ui.liste.ListeViewModel
import com.example.jet_bring.ui.liste.ProductModeSwitcher
import com.example.jet_bring.ui.profilo.ProfiloViewModel
import com.example.jet_bring.ui.theme.*



/**
 * Funzione usata come scermata per la visualizzazione delle ricette nel dettaglio
 * */

@ExperimentalComposeUiApi
@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RicetteDetails(navController: NavHostController, ricettaId: Long,listeViewModel: ListeViewModel,profiloViewModel:ProfiloViewModel) {
    val ricetta = listeViewModel.getRicetta(ricettaId)
    val scrollState = rememberScrollState()

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
                    contentDescription = "",

                    )
            }
            Column(Modifier.padding()) {
                ricetta.pubblicatore?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .padding(
                                start = PADDING_START,
                                end = PADDING_END,
                                top = PADDING_TOP,
                                bottom = 0.dp
                            ),
                    )
                }
                ricetta.titolo?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(
                            start = PADDING_START,
                            end = PADDING_END,
                            top = PADDING_TOP,
                            bottom = PADDING_BOTTOM
                        ),
                        fontWeight = FontWeight.Bold,
                    )

                }
                AlignInRow(ricetta)
                Spacer(modifier = Modifier.height(PADDING_TOP))
                Card(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .padding(
                            bottom = PADDING_BOTTOM,
                            top = PADDING_TOP,
                            start = PADDING_START,
                            end = PADDING_END

                        )
                        .fillMaxWidth()
                        .noRippleClickable {
                            null
                        },

                    elevation = 0.dp,
                    //backgroundColor = MaterialTheme.colors.background
                ) {
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
                                .padding(start = 15.dp)


                        )
                    }


                }


                /**
                 * Generazione della lista degli ingredienti
                 * */
                Column(modifier = Modifier.padding(start = PADDING_START, end = PADDING_END)) {
                    ProductModeSwitcher(
                        listeViewModel.getRicetta(ricettaId).ingredienti,
                        profiloViewModel,
                        onButtonClick = { product ->
                            if (listeViewModel.isInSelectedRicettaList(product.id, ricettaId))
                                listeViewModel.removeFromSelectedRicetta(product.id, ricettaId)
                            else
                                listeViewModel.addToSelectedRicetta(product.id, ricettaId)
                            Log.d(null, "${listeViewModel.getSelectedRicetta(ricettaId).ingredienti}")
                        },
                        onDescriptionChange = { product, description ->
                            listeViewModel.setRicettaProductDescription(
                                product.id,
                                ricettaId,
                                description
                            )
                        },
                        { product -> listeViewModel.isInSelectedRicettaList(product.id, ricettaId) },
                        BreakerBay,
                        MaterialTheme.colors.primary,
                    )
                }



                Spacer(modifier = Modifier.height(150.dp))

                /**
                 * Bottone che ci permette di aggiungere gli elementi alla lista principale
                 * */
                Button(
                    shape = MaterialTheme.shapes.medium,
                    modifier = Modifier
                        .padding(start = PADDING_START, end = PADDING_END)
                        .fillMaxWidth()
                        .height(70.dp),
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
                        backgroundColor = MaterialTheme.colors.secondary
                    ),
                    elevation = ButtonDefaults.elevation(
                        defaultElevation = 3.dp,
                        pressedElevation = 3.dp,
                        disabledElevation = 3.dp
                    ),
                ) {
                    Text(
                        text = "Aggiungi ${listeViewModel.getSelectedRicetta(ricettaId).ingredienti.size.toString()} ingredienti",
                        color = Color.White

                    )

                }

                Spacer(modifier = Modifier.height(50.dp))
            }
        }

        /**
         * Tasto chiusura schermata
         * */
        Button(
            onClick = { getBack(ricettaId,listeViewModel::resetRicetta,navController::popBackStack) },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
            ),
            modifier = Modifier
                .padding(8.dp)
                .width(51.dp)
                .height(51.dp)

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
