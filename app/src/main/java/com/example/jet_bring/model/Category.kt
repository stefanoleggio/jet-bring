package com.example.jet_bring.model

import androidx.compose.runtime.Immutable

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
        products = products.subList(0, 3)
    ),
    Category(
        id = 2,
        name = "Panetteria",
        products = products.subList(3, 5)
    )
)