package com.example.jet_bring

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.KEY_ROUTE
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.jet_bring.navigation.AppBottomNavigation
import com.example.jet_bring.navigation.NavigationManager
import com.example.jet_bring.navigation.Screen

@Composable
fun JetbringApp() {
    val title = remember { mutableStateOf("Liste") }
    val navController = rememberNavController()

    val bottomNavigationItems = listOf(
        Screen.Liste,
        Screen.Ispirazione,
        Screen.Profilo,
    )
    Scaffold(
        topBar = {
            if (currentRoute(navController) != "ispirazione/ricetteDetails/{ricettaId}") {
                TopAppBar(title = { Text(text = title.value) },
                    backgroundColor = MaterialTheme.colors.primaryVariant,
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Rounded.Email, contentDescription = "Localized description")
                        }
                    })
            }
        },
        bottomBar = {

            if (currentRoute(navController) != "ispirazione/ricetteDetails/{ricettaId}") {
                AppBottomNavigation(navController, bottomNavigationItems)
            }

        },
        content = {
            itemPadding -> NavigationManager(navController, title,itemPadding)
                  },
    )


}
@Composable
public fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.arguments?.getString(KEY_ROUTE)
}