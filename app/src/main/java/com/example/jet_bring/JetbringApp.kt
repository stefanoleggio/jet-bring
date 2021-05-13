package com.example.jet_bring

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
        content = {
            itemPadding -> NavigationManager(navController, title,itemPadding)
                  },
    )


}