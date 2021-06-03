package com.example.jet_bring.ui.ispirazione.components


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import kotlin.math.min

import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.jet_bring.model.products
import com.example.jet_bring.ui.ispirazione.AddRicettaViewModel
import com.example.jet_bring.ui.liste.ListeViewModel
import com.example.jet_bring.ui.liste.ProductModeSwitcher
import com.example.jet_bring.ui.profilo.ProfiloViewModel
import com.example.jet_bring.ui.theme.BreakerBay


@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.R)
@ExperimentalComposeUiApi
@Preview
@Composable
fun AddRicettePreview() {
    AddRicetta(
        rememberNavController(),
        ListeViewModel(),
        AddRicettaViewModel(),
        ProfiloViewModel()
    )
}


@ExperimentalFoundationApi
@RequiresApi(Build.VERSION_CODES.R)
@ExperimentalComposeUiApi
@Composable
fun AddRicetta(navController: NavHostController, listeViewModel: ListeViewModel,addRicettaViewModel: AddRicettaViewModel, profiloViewModel: ProfiloViewModel) {

    val scrollState = rememberScrollState()
    LaunchedEffect(Unit) { scrollState.animateScrollTo(100) }


    val state = rememberScrollState()
    Column(
            modifier = Modifier
                .verticalScroll(state )
                .fillMaxSize(),
            horizontalAlignment = CenterHorizontally,


            ) {

            Card(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier

                    .fillMaxWidth()
                    .height(380.dp)
                    .noRippleClickable {
                        //navController.navigate("$route/${ricetta.id}")

                    },
                elevation = 0.dp,
                //backgroundColor = MaterialTheme.colors.background
            ){


                    Column() {
                        Spacer(modifier = Modifier.height(90.dp))
                        Icon(
                            Icons.Rounded.Image,
                            contentDescription = "Add image",
                            tint = MaterialTheme.colors.onBackground,
                            modifier = Modifier
                                .size(60.dp)
                                .align(CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(100.dp))


                        addRicettaViewModel.ricetta.titolo?.let { InputText(text = it, addRicettaViewModel::onTitoloChange, modifier = Modifier.fillMaxWidth(), label = "Nome Modello") }
                        Row() {
                            addRicettaViewModel.ricetta.descrizione?.let { InputText(text = it, addRicettaViewModel::onDescrizioneChange, modifier = Modifier.fillMaxWidth(0.8f), label = "Descrizione") }
                            addRicettaViewModel.ricetta.sourceUrl?.let { AlertDialogLink(link = it, addRicettaViewModel::onLinkChange, addRicettaViewModel = addRicettaViewModel,) }
                        }


                    }

            }
            Spacer(modifier = Modifier.height(15.dp))


            Card(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .padding(
                        bottom = 20.dp,
                        top = 6.dp,
                        start = 15.dp,
                        end = 15.dp

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
                        text = "${addRicettaViewModel.ricetta.persone} Persone",
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .padding(start = 15.dp)
                    )
                    Spacer(modifier = Modifier.padding(80.dp))
                    Button(
                        onClick = {
                                  addRicettaViewModel.onPersonChange("minus")
                        },
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = MaterialTheme.colors.background
                        ),
                        modifier = Modifier
                            .wrapContentWidth(Alignment.End)

                    ) {
                        Icon(
                            Icons.Rounded.RemoveCircleOutline,
                            contentDescription = "Localized description",
                            tint = MaterialTheme.colors.onBackground,

                        )

                    }
                    Button(
                        onClick = {
                            addRicettaViewModel.onPersonChange("plus")
                        },
                        colors = ButtonDefaults.textButtonColors(
                            backgroundColor = MaterialTheme.colors.background
                        ),
                        elevation = ButtonDefaults.elevation(
                            defaultElevation = 0.dp,
                            pressedElevation = 0.dp,
                            disabledElevation = 0.dp
                        ),
                        modifier = Modifier
                            .wrapContentWidth(Alignment.End)
                        //elevation = ButtonElevation.elevation(enabled = false, interactionSource = null )

                    ) {
                        Icon(
                            Icons.Rounded.AddCircleOutline
                            , contentDescription = "Localized description",
                            tint = MaterialTheme.colors.onBackground,

                        )

                    }


                }


            }

            Spacer(modifier = Modifier.height(15.dp))
            Card(
                modifier = Modifier.fillMaxWidth(0.50f),
                border =  BorderStroke(2.dp, MaterialTheme.colors.onBackground),
            ){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(25.dp))
                    Button(onClick = {

                        addRicettaViewModel.addRicetta()
                        navController.popBackStack()
                        listeViewModel.selectedRicette = mutableStateOf(ArrayList(listeViewModel.setSelectedRicette()))
                        addRicettaViewModel.resetRicetta()


                    }) {
                        Icon(
                            Icons.Rounded.Save,
                            contentDescription = "Add image",
                            tint = MaterialTheme.colors.onBackground,
                            modifier = Modifier
                                .size(60.dp)
                                .align(CenterVertically)
                        )
                        Text(text = "Salva", )
                    }


                    Spacer(modifier = Modifier.height(25.dp))
                }



            }

            Text(text = "Articoli")
            Spacer(modifier = Modifier.height(15.dp))
            // Mostra la griglia di tutti i prodotti disponibili
            // Smoothly scroll
            Spacer(modifier = Modifier.height(20.dp))
            ProductModeSwitcher(
                products,
                profiloViewModel,
                onButtonClick = {
                        product ->
                    if (  addRicettaViewModel.listeViewModel.isInSelectedProduct(product.id)) {
                        addRicettaViewModel.listeViewModel.removeSelectedProduct(product.id)
                    } else {
                        addRicettaViewModel.listeViewModel.addSelectedProduct(product)
                    }
                },
                onDescriptionChange = {
                        product, description ->
                    addRicettaViewModel.listeViewModel.setDescription(product.id, description)
                },
                { product-> addRicettaViewModel.listeViewModel.isInSelectedProduct(product.id) },
                BreakerBay,
                MaterialTheme.colors.background
            )




            Spacer(modifier = Modifier.height(50.dp))


        }


        Button(
            onClick = { navController.popBackStack() },
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
            Icon(Icons.Filled.Close, contentDescription = "", tint = MaterialTheme.colors.onBackground, modifier = Modifier.size(24.dp) )

        }










}



