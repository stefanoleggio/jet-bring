package com.example.jet_bring.ui.ispirazione


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
import com.example.jet_bring.ui.theme.*
import com.example.jet_bring.ui.utils.InputText
import kotlinx.coroutines.launch


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
fun AddRicetta(navController: NavHostController, listeViewModel: ListeViewModel, addRicettaViewModel: AddRicettaViewModel,profiloViewModel: ProfiloViewModel) {

    val scrollState = rememberScrollState()
    LaunchedEffect(Unit) { scrollState.animateScrollTo(100) }




    val state = rememberScrollState()
    Column(
            modifier = Modifier
                .verticalScroll(state)
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
        ) {


            Column(modifier = Modifier.padding(start= PADDING_START, end = PADDING_END)) {
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


                addRicettaViewModel.ricetta.titolo?.let {
                    InputText(
                        text = it,
                        addRicettaViewModel::onTitoloChange,
                        modifier = Modifier.fillMaxWidth(),
                        label = "Nome Modello"
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),

                ) {
                    addRicettaViewModel.ricetta.descrizione?.let {
                        InputText(
                            text = it,
                            addRicettaViewModel::onDescrizioneChange,
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .alignByBaseline(),
                            label = "Descrizione",
                        )
                    }
                    addRicettaViewModel.ricetta.sourceUrl?.let {
                        AlertDialogLink(
                            link = it,
                            addRicettaViewModel::onLinkChange,
                            addRicettaViewModel = addRicettaViewModel,
                        )
                    }
                }


            }

        }
        Spacer(modifier = Modifier.height(15.dp))

        Column(Modifier
            .padding(
                bottom = PADDING_BOTTOM,
                top = LINE_SPACE,
                start = PADDING_START,
                end = PADDING_END

            ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier/*
                    .padding(
                        bottom = PADDING_BOTTOM,
                        top = LINE_SPACE,
                        start = PADDING_START,
                        end = PADDING_END

                    )*/
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
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "${addRicettaViewModel.ricetta.persone} Persone",
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier
                            .wrapContentWidth(Alignment.CenterHorizontally)
                            .padding(start = 15.dp)
                    )
                    Row(

                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Button(
                            onClick = {
                                addRicettaViewModel.onPersonChange("minus")
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

                        ) {
                            Icon(
                                Icons.Rounded.RemoveCircleOutline,
                                contentDescription = "Localized description",
                                tint = MaterialTheme.colors.onBackground,

                                )

                        }
                        Button(
                            modifier = Modifier
                                .padding(start = 5.dp, end = 15.dp)
                                .wrapContentWidth(Alignment.End),
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

                        ) {
                            Icon(
                                Icons.Rounded.AddCircleOutline,
                                contentDescription = "",
                                tint = MaterialTheme.colors.onBackground,

                                )

                        }
                    }


                    //Spacer(modifier = Modifier.padding(80.dp))



                }


            }

            Spacer(modifier = Modifier.height(15.dp))
            Spacer(modifier = Modifier.height(PADDING_TOP))
            Column(
                modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.CenterEnd)
            ) {
                Button(
                    onClick = {

                        addRicettaViewModel.setNamePerson(profiloViewModel.user.name)
                        addRicettaViewModel.addRicetta()
                        navController.popBackStack()
                        listeViewModel.selectedRicette =
                            mutableStateOf(ArrayList(listeViewModel.setSelectedRicette()))
                    },
                    Modifier,
                    colors = ButtonDefaults.outlinedButtonColors(
                        backgroundColor = MaterialTheme.colors.secondary,
                        contentColor = MaterialTheme.colors.onSurface
                    ),
                    elevation = ButtonDefaults.elevation()
                )
                {
                    Text("Salva")
                }
            }




            Spacer(modifier = Modifier.height(PADDING_TOP))
            Spacer(modifier = Modifier.height(PADDING_END))

            ProductModeSwitcher(
                addRicettaViewModel.prodottiAddRicetta,
                profiloViewModel,
                onButtonClick = { product ->
                    if (addRicettaViewModel.isInSelectedProductsRicetta(product.id)) {
                        addRicettaViewModel.removeSelectedProductsRicetta(product.id)
                    } else {
                        addRicettaViewModel.addSelectedProductsRicetta(product)
                    }
                },
                onDescriptionChange = { product, description ->
                    addRicettaViewModel.setDescriptionVirginProducts(productId = product.id,description = description)
                },
                { product -> addRicettaViewModel.isInSelectedProductsRicetta(product.id) },
                BreakerBay,
                MaterialTheme.colors.primary
            )


            Spacer(modifier = Modifier.height(50.dp))


        }
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



