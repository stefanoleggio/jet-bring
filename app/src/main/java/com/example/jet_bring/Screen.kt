package com.example.jet_bring
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector




sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Liste: Screen("Liste", "Liste", Icons.Default.List)
    object Ispirazione: Screen("Ispirazione", "Ispirazione", Icons.Default.Create)
    object Profilo: Screen("Profilo", "Profilo", Icons.Default.Person)
}