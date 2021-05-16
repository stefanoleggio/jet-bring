package com.example.jet_bring.ui.liste

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.jet_bring.model.Product

class ListeViewModel : ViewModel() {


    val selectedProducts: MutableState<List<Product>> = mutableStateOf(ArrayList())

    fun addProduct(product: Product) {
        val current = ArrayList(this.selectedProducts.value)
        current.add(product)
        this.selectedProducts.value = current
    }

    fun removeProduct(product: Product) {
        val current = ArrayList(this.selectedProducts.value)
        current.remove(product)
        this.selectedProducts.value = current
    }

    fun getProducts(): List<Product> {
        return ArrayList(this.selectedProducts.value)
    }

    fun isSelectedProductsEmpty(): Boolean {
        return ArrayList(this.selectedProducts.value).size == 0
    }
}