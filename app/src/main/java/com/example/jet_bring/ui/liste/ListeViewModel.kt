package com.example.jet_bring.ui.liste

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.jet_bring.model.Category
import com.example.jet_bring.model.Product
import com.example.jet_bring.model.categories

class ListeViewModel : ViewModel() {


    val selectedProducts: MutableState<List<Product>> = mutableStateOf(ArrayList())

    /**
     *
     * Category model methods
     *
     */

    fun getCategories(): List<Category> {
        return categories
    }

    fun getCategory(categoryId: Long): Category {
        return categories.find { categoryId == it.id }!!
    }

    fun getCategoryName(categoryId: Long): String {
        val category: Category = categories.find { categoryId == it.id }!!
        return category.name
    }

    /**
     *
     * Selected products methods
     *
     */

    fun addSelectedProduct(product: Product) {
        val current = ArrayList(this.selectedProducts.value)
        current.add(product)
        this.selectedProducts.value = current
    }

    fun removeSelectedProduct(product: Product) {
        val current = ArrayList(this.selectedProducts.value)
        current.remove(product)
        this.selectedProducts.value = current
    }

    fun getSelectedProducts(): List<Product> {
        return ArrayList(this.selectedProducts.value)
    }

    fun isSelectedProductsEmpty(): Boolean {
        return ArrayList(this.selectedProducts.value).size == 0
    }
}