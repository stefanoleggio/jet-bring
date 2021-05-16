package com.example.jet_bring.model

import androidx.compose.runtime.Immutable
import com.example.jet_bring.R

@Immutable
data class Product(
    val id : Long,
    val name: String,
    val icon: Int
)

val products = listOf(
    Product(
        id = 1,
        name = "Mela",
        icon = R.drawable.ic_apple
    ),
    Product(
        id = 2,
        name = "Pera",
        icon = R.drawable.ic_apple
    ),
    Product(
        id = 3,
        name = "Arancia",
        icon = R.drawable.ic_apple
    ),
    Product(
        id = 4,
        name = "Pane",
        icon = R.drawable.ic_apple
    ),
    Product(
        id = 5,
        name = "Cornetti",
        icon = R.drawable.ic_apple
    )
)