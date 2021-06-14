package com.example.jet_bring

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.jet_bring.navigation.AppBottomNavigation
import com.example.jet_bring.navigation.NavigationManager
import com.example.jet_bring.navigation.Screen
import com.example.jet_bring.ui.liste.ListeViewModel
import com.example.jet_bring.ui.profilo.ProfiloViewModel

@RequiresApi(Build.VERSION_CODES.R)
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun JetbringApp(profiloViewModel: ProfiloViewModel, listeViewModel: ListeViewModel)
{
    val title = remember { mutableStateOf("Liste") }
    val backArrow = remember {mutableStateOf(false)}
    //Creazione del navController
    val navController = rememberNavController()


        val bottomNavigationItems = listOf(
            Screen.Liste,
            Screen.Ispirazione,
            Screen.Profilo,
        )
        val backgroundColor = MaterialTheme.colors.secondaryVariant
        /**
         * Creazione dello scaffold
         */
        Scaffold(
            topBar = {
                if (backArrow.value) {
                    TopAppBar(title = { Text(text = title.value) },
                        backgroundColor = backgroundColor,
                        actions = {

                        },
                        navigationIcon = {
                            IconButton(onClick = { navController.popBackStack() }) {
                                Icon(
                                    Icons.Rounded.ArrowBack,
                                    contentDescription = "Localized description"
                                )
                            }
                        }
                    )
                } else if (currentRoute(navController) != "ispirazione/ricetteDetails/{ricettaId}") {
                    TopAppBar(
                        title = { Text(text = title.value) },
                        backgroundColor = backgroundColor,
                    )
                } else {
                    TopAppBar(
                        title = { Text(text = title.value) },
                        backgroundColor = backgroundColor,

                        )
                }
            },
            bottomBar = {

                if (currentRoute(navController) != "ispirazione/ricetteDetails/{ricettaId}") {
                    AppBottomNavigation(navController, bottomNavigationItems)
                }

            },
            content = { itemPadding ->
                NavigationManager(
                    navController,
                    title,
                    itemPadding,
                    backArrow,
                    profiloViewModel,
                    listeViewModel
                )
            },
            floatingActionButton = {
                if (currentRoute(navController) == "ispirazione") {
                    FloatingActionButton(
                        onClick = {
                            navController.navigate("ispirazione/addRicetta") {
                            }
                        },
                        elevation = FloatingActionButtonDefaults.elevation(8.dp),
                        modifier = Modifier.semantics { contentDescription = "Add button" }
                    ) {
                        Icon(
                            Icons.Rounded.Add,
                            contentDescription = null,
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                }

        },
    )

}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString(KEY_ROUTE)
}