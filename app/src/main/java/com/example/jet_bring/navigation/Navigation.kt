package com.example.jet_bring.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import com.example.jet_bring.ui.ispirazione.IspirazioneScreen
import com.example.jet_bring.ui.ispirazione.components.Ricetta
import com.example.jet_bring.ui.ispirazione.components.RicetteDetails
import com.example.jet_bring.ui.liste.CategoryScreen
import com.example.jet_bring.ui.liste.ListeScreen
import com.example.jet_bring.ui.profilo.IlTuoProfilo
import com.example.jet_bring.ui.profilo.ProfiloScreen
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.dp
import com.example.jet_bring.ui.liste.ListeViewModel
import com.example.jet_bring.ui.profilo.ProfiloViewModel

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun NavigationManager (
    navController: NavHostController,
    topBarTitle: MutableState<String>,
    screenPadding: PaddingValues = PaddingValues(0.dp),
    backArrow: MutableState<Boolean>
){
    /**
     *
     * ViewModels
     *
     */

    val listeViewModel = ListeViewModel()

    val profileViewModel  = ProfiloViewModel()

    NavHost(navController, startDestination = "liste") {

        /**
         *
         * Main routes
         *
         */

        composable("liste") {
            ListeScreen(navController, listeViewModel)
            topBarTitle.value = "Liste"
            backArrow.value = false
        }

        composable("ispirazione") {
            IspirazioneScreen(navController, screenPadding)
            topBarTitle.value = "Ispirazione"
            backArrow.value = false
        }

        composable("profilo") {
            ProfiloScreen(
                navController,
                screenPadding,
                profileViewModel
            )
            topBarTitle.value = "Profilo"
            backArrow.value = false
        }

        /**
         *
         * Liste routes
         *
         */

        composable(
            "liste/{categoryId}") { backStackEntry ->
            val categoryId: Long = backStackEntry.arguments?.getString("categoryId")!!.toLong()
            CategoryScreen(navController, categoryId, listeViewModel)
            topBarTitle.value = listeViewModel.getCategoryName(categoryId)
            backArrow.value = true
        }

        /**
         *
         * Ispirazione routes
         *
         */

        composable("ispirazione/ricetteDetails/{ricettaId}", arguments =  listOf(navArgument("ricettaId") { type = NavType.StringType   ; defaultValue = "1"})
                ) { backStackEntry ->
            RicetteDetails(navController, backStackEntry.arguments!!.getString("ricettaId"))
            topBarTitle.value = "Ricetta"
        }
        composable("ispirazione/addRicetta") {
            //AddRicetta(navController, addRicettaViewModel, )
            topBarTitle.value = "Aggiungi ricetta"
        }



        /**
         *
         * Profilo routes
         *
         */

        composable("profilo/ilTuoProfilo") {
            profileViewModel.notSaved()
            IlTuoProfilo(
                profileViewModel
            )
            topBarTitle.value = "Il tuo Profilo"
            backArrow.value = true
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