package com.example.jet_bring.ui.ispirazione.components


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.jet_bring.model.products
import com.example.jet_bring.ui.ispirazione.AddRicettaViewModel
import com.example.jet_bring.ui.liste.ProductModeSwitcher
import com.example.jet_bring.ui.profilo.ProfiloViewModel


@RequiresApi(Build.VERSION_CODES.R)
@ExperimentalComposeUiApi
@Preview
@Composable
fun AddRicettePreview() {
    AddRicetta(
        rememberNavController(),
        AddRicettaViewModel()
    )
}


@RequiresApi(Build.VERSION_CODES.R)
@ExperimentalComposeUiApi
@Composable
fun AddRicetta(navController: NavHostController, addRicettaViewModel: AddRicettaViewModel) {

    val scrollState = rememberScrollState()

    val profileViewModel  = ProfiloViewModel()


        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
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

                    }
                    .alpha(min(1f, 1 - (scrollState.value / 400f))),
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.error
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


                        addRicettaViewModel.ricetta.titolo?.let { InputText(text = it, addRicettaViewModel::onTitoloChange, modifier = Modifier.fillMaxWidth()) }
                        Row() {
                            addRicettaViewModel.ricetta.descrizione?.let { InputText(text = it, addRicettaViewModel::onDescrizioneChange, modifier = Modifier.fillMaxWidth(0.8f)) }
                            addRicettaViewModel.ricetta.sourceUrl?.let { AlertDialogLink(link = it, addRicettaViewModel::onLinkChange, addRicettaViewModel = addRicettaViewModel) }
                        }


                    }

            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Articoli")
            Spacer(modifier = Modifier.height(20.dp))
            Card(
                modifier = Modifier.fillMaxWidth(0.9f),
                border =  BorderStroke(2.dp, MaterialTheme.colors.onBackground),
            ){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier.height(25.dp))
                    Button(onClick = {

                        addRicettaViewModel.addRicetta()
                        navController.popBackStack()


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
            ProductModeSwitcher(
                products,
                profileViewModel,
                onButtonClick = {
                        product ->
                    if (addRicettaViewModel.listeViewModel.containsSelectedProduct(product)) {
                        addRicettaViewModel.listeViewModel.removeSelectedProduct(product)
                    } else {
                        addRicettaViewModel.listeViewModel.addSelectedProduct(product)
                    }
                },
                onDescriptionChange = {
                        product, description ->
                    addRicettaViewModel.listeViewModel.setDescription(product, description)
                },
                addRicettaViewModel.listeViewModel::containsSelectedProduct
            )
            Spacer(modifier = Modifier.height(25.dp))


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


