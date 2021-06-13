package com.example.jet_bring.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
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
import com.example.jet_bring.ui.liste.CategoryScreen
import com.example.jet_bring.ui.liste.ListeScreen
import com.example.jet_bring.ui.profilo.IlTuoProfilo
import com.example.jet_bring.ui.profilo.ProfiloScreen
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.unit.dp
import com.example.jet_bring.ui.ispirazione.AddRicetta
import com.example.jet_bring.ui.ispirazione.AddRicettaViewModel
import com.example.jet_bring.ui.ispirazione.RicetteDetails
import com.example.jet_bring.ui.liste.ListeViewModel
import com.example.jet_bring.ui.profilo.ProfiloViewModel

@RequiresApi(Build.VERSION_CODES.R)
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@Composable
fun NavigationManager (
    navController: NavHostController,
    topBarTitle: MutableState<String>,
    screenPadding: PaddingValues = PaddingValues(0.dp),
    backArrow: MutableState<Boolean>,
    profiloViewModel: ProfiloViewModel,
    listeViewModel: ListeViewModel
){

    NavHost(navController, startDestination = "liste") {

        /**
         *
         * Main routes
         *
         */

        composable("liste") {

            animationFade {
                ListeScreen(navController, screenPadding, listeViewModel,profiloViewModel)
            }
            topBarTitle.value = "Liste"
            backArrow.value = false
        }

        composable("ispirazione") {
            animationFade {
                IspirazioneScreen(navController, screenPadding)
            }

            topBarTitle.value = "Ispirazione"
            backArrow.value = false
        }

        composable("profilo") {
            animationFade {
                ProfiloScreen(
                    navController,
                    screenPadding,
                    profiloViewModel
                )
            }
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
            animationSlide {
                CategoryScreen(navController, categoryId, screenPadding, listeViewModel,profiloViewModel)
            }
            topBarTitle.value = listeViewModel.getCategoryName(categoryId)
            backArrow.value = true
        }

        /**
         *
         * Ispirazione routes
         *
         */

        composable(
            "ispirazione/ricetteDetails/{ricettaId}",
            arguments =  listOf(navArgument("ricettaId") { type = NavType.StringType;defaultValue = "0" }
        )
        ) { backStackEntry ->
            val ricettaId : Long = backStackEntry.arguments?.getString("ricettaId")!!.toLong()
            animationSlide {
                RicetteDetails(navController, ricettaId,listeViewModel, profiloViewModel)
            }
            topBarTitle.value = "Ricetta"

        }
        composable("ispirazione/addRicetta") {
            animationSlideVertically {
                AddRicetta(navController, listeViewModel, AddRicettaViewModel() ,profiloViewModel)
            }

            topBarTitle.value = "Aggiungi ricetta"
        }



        /**
         *
         * Profilo routes
         *
         */

        composable("profilo/ilTuoProfilo") {
            profiloViewModel.notSaved()

            animationSlide{
                IlTuoProfilo(
                    profiloViewModel
                )
            }
            topBarTitle.value = "Il tuo Profilo"
            backArrow.value = true


        }
    }
}

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Liste: Screen("liste", "Liste", Icons.Default.List)
    object Ispirazione: Screen("ispirazione", "Ispirazione", Icons.Default.Create)
    object Profilo: Screen("profilo", "Profilo", Icons.Default.Person)
}


@Composable
fun AppBottomNavigation(
    navController: NavHostController,
    items: List<Screen>
) {

    BottomNavigation(backgroundColor = MaterialTheme.colors.primary) {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            BottomNavigationItem(

                icon = {
                    val color by animateColorAsState(
                        targetValue = if (screen.route == currentRoute) colors.onPrimary else colors.onSecondary
                    )
                    Icon(
                        screen.icon,
                        tint = color,
                        contentDescription = ""
                    )
                },
                label = {
                    Text(text = screen.label )
                },
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

@ExperimentalAnimationApi
@Composable
fun animationSlide(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = slideInHorizontally(
            // Offsets the content by 1/3 of its width to the left, and slide towards right
            initialOffsetX = { fullWidth -> fullWidth },
            // Overwrites the default animation with tween for this slide animation.
            animationSpec = tween(durationMillis = 100)
        ) + fadeIn(
            // Overwrites the default animation with tween
            animationSpec = tween(durationMillis = 100)
        ) ,
        exit = slideOutHorizontally(
            // Offsets the content by 1/3 of its width to the left, and slide towards right
            targetOffsetX = { fullWidth -> fullWidth },
            // Overwrites the default animation with tween for this slide animation.
            animationSpec = tween(durationMillis = 100)
        ) + fadeOut(
            // Overwrites the default animation with tween
            animationSpec = tween(durationMillis = 100)
        ),
        content = content,
        initiallyVisible = false
    )
}

@ExperimentalAnimationApi
@Composable
fun animationSlideVertically(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = slideInVertically(
            initialOffsetY = { 2000 },
            animationSpec = tween(durationMillis = 100)
        )+ fadeIn(initialAlpha = 0.6f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut(),
        content = content,
        initiallyVisible = false
    )
}


@ExperimentalAnimationApi
@Composable
fun animationFade(content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = true,
        enter = fadeIn(animationSpec = tween(durationMillis = 150)),
        exit = fadeOut(animationSpec = tween(durationMillis = 150)),
        content = content,
        initiallyVisible = false
    )
}