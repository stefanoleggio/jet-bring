package com.example.jet_bring

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.rounded.Email
import com.example.jet_bring.ui.ispirazione.IspirazioneScreen
import com.example.jet_bring.ui.liste.ListeScreen
import com.example.jet_bring.ui.profilo.ProfiloScreen
import com.example.jet_bring.ui.theme.JetbringTheme


val lightGrey: Color = Color(0xff4c636b)
val darkGrey: Color = Color(0xff303e47)


class JetBringActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetbringTheme  {
                val navController = rememberNavController()
                val title = remember { mutableStateOf("Liste") }
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {

                    Scaffold(
                        topBar = {
                            TopAppBar(title = { Text(text = title.value) },
                                backgroundColor = lightGrey,
                                actions = {
                                    IconButton(onClick = {}) {
                                        Icon(Icons.Rounded.Email, contentDescription = "Localized description")

                                    }
                                })
                        },

                        bottomBar = {
                            val items = listOf(Screen.Liste, Screen.Ispirazione, Screen.Profilo)
                            BottomNavigation(backgroundColor = lightGrey) {
                                val navBackStackEntry by navController.currentBackStackEntryAsState()
                                val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)
                                items.forEach {
                                    BottomNavigationItem(

                                        icon = {
                                            Icon(
                                                it.icon,
                                                contentDescription = "Localized description"
                                            )
                                        },
                                        selected = currentRoute == it.route,
                                        label = { Text(text = it.label) },
                                        onClick = {
                                            navController.popBackStack(
                                                navController.graph.startDestination, false
                                            )
                                            if (currentRoute != it.route) {
                                                navController.navigate(it.route)
                                            }
                                        })
                                }
                            }
                        }
                    ) {
                        ScreenController(navController, title)
                    }
                }
            }
        }
    }
}

@Composable
fun ScreenController(navController: NavHostController, topBarTitle: MutableState<String>) {
    NavHost(navController = navController, startDestination = "Liste") {
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
