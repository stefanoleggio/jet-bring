package com.example.jet_bring

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import androidx.compose.material.Icon
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.jet_bring.ui.theme.JetbringTheme


val lightGrey: Color = Color(0xff4c636b)
val darkGrey: Color = Color(0xff303e47)


class MainActivity : AppCompatActivity() {

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

@Composable
fun ListeScreen() {
    Text(text = "Liste",
        style = TextStyle(color = MaterialTheme.colors.primary, fontSize = 36.sp),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(darkGrey))
    LazyColumn {

        // Add a single item
        item {
            Text(
                text = "First item",
                style = TextStyle(color = Color.Black, fontSize = 36.sp),
            )

        }

        // Add 5 items
        items(5) { index ->
            Text(
                text = "Item: $index",
                style = TextStyle(color = Color.Black, fontSize = 36.sp),
            )
        }

        // Add another single item
        item {
            Text(
                text = "Last item",
                style = TextStyle(color = Color.Black, fontSize = 36.sp),
            )
        }
    }

}

@Composable
fun IspirazioneScreen() {
    LazyColumn {
        // Add a single item
        item {
            Card(
                backgroundColor = lightGrey,

            ) {

                Text(
                    text = "Jetpack Compose"
                )
            }
        }


        // Add another single item
        item {
            Card {
                Text(
                    text = "Jetpack Compose"
                )
            }
        }
    }

}

@Composable
fun ProfiloScreen() {
    Text(text = "Profilo",
        style = TextStyle(color = Color.Black, fontSize = 36.sp),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray))
}

