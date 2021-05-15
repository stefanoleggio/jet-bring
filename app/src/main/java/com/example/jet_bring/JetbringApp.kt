package com.example.jet_bring

import androidx.compose.foundation.layout.Row
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.jet_bring.navigation.AppBottomNavigation
import com.example.jet_bring.navigation.NavigationManager
import com.example.jet_bring.navigation.Screen
import com.example.jet_bring.ui.profilo.ProfiloViewModel
import com.example.jet_bring.ui.profilo.UserData

@ExperimentalComposeUiApi
@Composable
fun JetbringApp() {
    val title = remember { mutableStateOf("Liste") }
    val backArrow = remember {mutableStateOf(false)}
    val navController = rememberNavController()

    val bottomNavigationItems = listOf(
        Screen.Liste,
        Screen.Ispirazione,
        Screen.Profilo,
    )
    Scaffold(
        topBar = {
            if(backArrow.value) {
                TopAppBar(title = { Text(text = title.value) },
                    backgroundColor = MaterialTheme.colors.primaryVariant,
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Rounded.Email, contentDescription = "Localized description")

                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.navigateUp() }) {
                            Icon(
                                Icons.Rounded.ArrowBack,
                                contentDescription = "Localized description"
                            )
                        }
                    }
                )
            } else {
                TopAppBar(title = { Text(text = title.value) },
                    backgroundColor = MaterialTheme.colors.primaryVariant,
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Rounded.Email, contentDescription = "Localized description")

                        }
                    }
                )
            }
        },
        bottomBar = {
            AppBottomNavigation(navController, bottomNavigationItems)
        },
        content = {
            itemPadding -> NavigationManager(
                navController,
                title,
                itemPadding,
                backArrow
            )
                  },
    )


}

@ExperimentalComposeUiApi
@Composable
@Preview
fun JetBringAppPreview() {
    JetbringApp()
}