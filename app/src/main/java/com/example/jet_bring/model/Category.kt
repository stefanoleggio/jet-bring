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
        products = listOf(
            Product(
                name = "Aglio",
                icon = R.drawable.ic_apple
            ),
            Product(
                name = "Albicocche",
                icon = R.drawable.ic_apple
            ),
            Product(
                name = "Ananas",
                icon = R.drawable.ic_apple
            ),
            Product(
                name = "Anguria",
                icon = R.drawable.ic_apple
            )
        )
    ),
    Category(
        id = 2,
        name = "Panetteria",
        products = listOf(
            Product(
                name = "Pane",
                icon = R.drawable.ic_apple
            ),
            Product(
                name = "Cornetti",
                icon = R.drawable.ic_apple
            ),
            Product(
                name = "Panini",
                icon = R.drawable.ic_apple
            )
        )
    )
)

