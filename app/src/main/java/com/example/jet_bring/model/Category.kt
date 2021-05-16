package com.example.jet_bring.model

import androidx.compose.runtime.Immutable
import com.example.jet_bring.R

@Immutable
data class Category(
    val id: Long,
    val name: String,
    val products: List<Product>
)

object CategoryRecovery {
    fun getCategory(categoryId: Long): Category {
        return categories.find { categoryId == it.id }!!
    }

    fun getName(categoryId: Long): String {
        val category: Category = categories.find { categoryId == it.id }!!
        return category.name
    }
}


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