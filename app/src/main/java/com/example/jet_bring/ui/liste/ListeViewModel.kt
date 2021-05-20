package com.example.jet_bring.ui.liste

import android.content.res.Resources
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import com.example.jet_bring.model.Category
import com.example.jet_bring.model.Product
import com.example.jet_bring.model.categories
import com.example.jet_bring.model.products
import java.lang.Math.ceil
import java.lang.Math.floor

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
     * Product model methods
     *
     */

    fun getProduct(productId: Int): Product {
        return products.get(productId)
    }

    /*TODO per stefano: ho fatto questa funzione che a seconda del valore passato decide se passare la lista di selezionati o quella della categoria
    *  non è bello lo so però funziona, se ti viene un metodo migliore fammi sapere */
    /**
     *
     */
    fun getProducts(category: Category?=null): List<Product> {
        if (category== null) return getSelectedProducts() else return category.products
    }

    /**
     *
     * Custom grid
     *
     */
    /*
    fun onClickProductButton() {
        if (removeSelectedProduct) {
            listeViewModel.removeSelectedProduct(product)
        } else {
            if (selected)
                listeViewModel.removeSelectedProduct(product)
            else
                listeViewModel.addSelectedProduct(product)
        }
    }
*/

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

    fun containsSelectedProduct(product: Product): Boolean {
        val current = ArrayList(this.selectedProducts.value)
        return current.contains(product)
    }

    fun getSelectedProducts(): List<Product> {
        return ArrayList(this.selectedProducts.value)
    }

    fun isSelectedProductsEmpty(): Boolean {
        return ArrayList(this.selectedProducts.value).size == 0
    }

}