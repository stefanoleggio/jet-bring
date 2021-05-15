package com.example.jet_bring.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.jet_bring.ui.ispirazione.IspirazioneScreen
import com.example.jet_bring.ui.liste.CategoryScreen
import com.example.jet_bring.ui.liste.ListeScreen
import com.example.jet_bring.ui.profilo.IlTuoProfilo
import com.example.jet_bring.ui.profilo.ProfiloScreen
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.example.jet_bring.ui.profilo.ProfiloViewModel
import com.example.jet_bring.ui.profilo.UserData

@ExperimentalComposeUiApi
@Composable
fun NavigationManager (
    navController: NavHostController,
    topBarTitle: MutableState<String>,
    screenPadding: PaddingValues,
){
    val profileViewModel = ProfiloViewModel()
    NavHost(navController, startDestination = "liste") {

        /*
        *
        *   Routes principali
        *
        */

        composable("liste") {
            ListeScreen(navController)
            topBarTitle.value = "Liste"
        }

        composable("ispirazione") {
            IspirazioneScreen(navController)
            topBarTitle.value = "Ispirazione"
        }

        composable("profilo") {
            ProfiloScreen(
                navController,
                screenPadding,
                profileViewModel
            )
            topBarTitle.value = "Profilo"
        }

        /*
        *
        *   Routes Liste
        *
        */

        composable("liste/frutta-e-verdura") {
            CategoryScreen(navController)
            topBarTitle.value = "Frutta e verdura"
        }

        /*
        *
        *   Routes Profilo
        *
        */
        composable("profilo/InfoJetBring") {
            CategoryScreen(navController)
            topBarTitle.value = "info su jet-Bring"
        }
        composable("profilo/ilTuoProfilo") {
            profileViewModel.notSaved()
            IlTuoProfilo(
                profileViewModel
            )
            topBarTitle.value = "Il tuo Profilo"
        }

    }
}

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Liste: Screen("Liste", "Liste", Icons.Default.List)
    object Ispirazione: Screen("Ispirazione", "Ispirazione", Icons.Default.Create)
    object Profilo: Screen("Profilo", "Profilo", Icons.Default.Person)
}


@Composable
fun AppBottomNavigation(
    navController: NavHostController,
    items: List<Screen>
) {
    BottomNavigation {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        screen.icon,
                        contentDescription = ""
                    )
                },
                label = { Text(text = screen.label) },
                selected = currentRoute == screen.route,
                onClick = {
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo = navController.graph.startDestination
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString(KEY_ROUTE)
}
