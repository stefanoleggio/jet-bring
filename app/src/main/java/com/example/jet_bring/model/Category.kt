package com.example.jet_bring.model

import androidx.compose.runtime.Immutable

/**
 * Data class per le Categorie
 * Ogni categoria ha una lista di prodotti, in particolare una subList della lista products
 */
@Immutable
data class Category(
    val id: Long,
    val name: String,
    val products: List<Product>
)

val categories = listOf(
    Category(
        id = 1,
        name = "Frutta e verdura",
        products = products.subList(0, 15)
    ),
    Category(
        id = 2,
        name = "Panetteria",
        products = products.subList(15, 18)
    ),
    Category(
        id = 3,
        name = "Latte e formaggi",
        products = products.subList(18, 23)
    ),
    Category(
        id = 4,
        name = "Carne e pesce",
        products = products.subList(23, 28)
    ),
)