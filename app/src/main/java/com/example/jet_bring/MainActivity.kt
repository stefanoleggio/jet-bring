package com.example.jet_bring

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.rounded.Email
import com.example.jet_bring.ui.ispirazione.IspirazioneScreen
import com.example.jet_bring.ui.liste.ListeScreen
import com.example.jet_bring.ui.profilo.ProfiloScreen
import com.example.jet_bring.ui.theme.JetbringTheme


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetbringTheme  {
                MainScreen()
            }
        }
    }
}


@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val title = remember { mutableStateOf("Liste") }

    val bottomNavigationItems = listOf(
        Screen.Liste,
        Screen.Ispirazione,
        Screen.Profilo,
    )
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = title.value) },
                backgroundColor = MaterialTheme.colors.primaryVariant,
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Rounded.Email, contentDescription = "Localized description")

                    }
                })
        },
        bottomBar = {
            AppBottomNavigation(navController, bottomNavigationItems)
        },
    ) {
        MainScreenNavigationConfigurations(navController,title)
    }
}

@Composable
private fun MainScreenNavigationConfigurations(
    navController: NavHostController,
    topBarTitle: MutableState<String>
) {
    NavHost(navController, startDestination = "Liste") {
        composable("Liste") {
            ListeScreen()
            topBarTitle.value = "Liste"
        }

        composable("Ispirazione") {
            IspirazioneScreen()
            topBarTitle.value = "Ispirazione"
        }

        composable("Profilo") {
            ProfiloScreen()
            topBarTitle.value = "Profilo"
        }
    }
}

@Composable
private fun AppBottomNavigation(
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
                    // This if check gives us a "singleTop" behavior where we do not create a
                    // second instance of the composable if we are already on that destination
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route)
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