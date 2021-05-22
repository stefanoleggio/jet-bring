package com.example.jet_bring.ui.ispirazione.components

import android.os.Build
import android.telecom.Call
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
import kotlin.math.min
import android.view.View
import android.view.Window
import com.example.jet_bring.ui.theme.BreakerBay

import androidx.core.content.ContextCompat

import android.view.WindowManager
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.MutableLiveData
import androidx.navigation.compose.rememberNavController
import com.example.jet_bring.model.Product
import com.example.jet_bring.model.ricette
import com.example.jet_bring.ui.ispirazione.AddRicettaViewModel
import com.example.jet_bring.ui.liste.ListeViewModel
import com.example.jet_bring.ui.liste.ProductColumnMode
import com.example.jet_bring.ui.liste.ProductGridMode
import com.example.jet_bring.ui.liste.ProductModeSwitcher
import com.example.jet_bring.ui.profilo.ProfiloViewModel
import kotlinx.coroutines.launch

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


@RequiresApi(Build.VERSION_CODES.R)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RicetteDetails(navController: NavHostController, ricettaId: String?, addRicettaViewModel: AddRicettaViewModel,listeViewModel: ListeViewModel,profiloViewModel:ProfiloViewModel) {

    val ricetta = addRicettaViewModel.getricetteList()[ricettaId?.toInt()!!]
    val scrollState = rememberScrollState()
    var aggiunti = false

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()





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

                    /*
                    Button(
                        onClick = {},
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
                            Icons.Rounded.RemoveCircleOutline,
                            contentDescription = "Localized description",
                            tint = MaterialTheme.colors.onBackground,

                        )

                    }
                    Button(
                        onClick = { ricetta.persone = ricetta.persone?.plus(1) },
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
                    */

                }


            }





            ProductModeSwitcher(
                ricetta.ingredienti,
                profiloViewModel,
                listeViewModel::removeSelectedProduct,
                listeViewModel::containsSelectedProduct
            )


            Spacer(modifier = Modifier.height(150.dp))

            SnackbarHost(
                hostState = snackbarHostState,
                Modifier.wrapContentWidth(Alignment.CenterHorizontally)
            )
            Button(
                shape = MaterialTheme.shapes.medium,
                onClick = {
                    if(aggiunti == false) {
                        for (ingrediente in ricetta.ingredienti) {
                            listeViewModel.addSelectedProduct(ingrediente)
                        }
                        aggiunti = true
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(message = "Prodotti aggiunti")
                        }
                    }else{
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(message = "Prodotti già inseriti")
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
                    text = "Aggiungi ${ricetta.ingredienti.size.toString()} ingredienti",
                    color = MaterialTheme.colors.onBackground

                )

            }

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
            Icon(Icons.Filled.Close, contentDescription = "Localized description", tint = MaterialTheme.colors.onBackground, modifier = Modifier.size(24.dp) )

        }
    }
}
