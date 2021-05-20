package com.example.jet_bring.model

import androidx.compose.runtime.Immutable

@Immutable
data class MyProduct(
    val product: Product,
    val description: String
)