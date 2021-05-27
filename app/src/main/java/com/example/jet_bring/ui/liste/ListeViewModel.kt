package com.example.jet_bring.ui.liste

import android.content.res.Resources
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import com.example.jet_bring.R
import com.example.jet_bring.model.*
import java.lang.Math.ceil
import java.lang.Math.floor

class ListeViewModel : ViewModel() {


    val selectedProducts: MutableState<List<Product>> = mutableStateOf(ArrayList())
    val selectedRicetta: MutableState<Ricetta> = mutableStateOf(Ricetta())

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

    fun getProduct(productId: Long): Product? {
        for (product in products) {
            if(product.id == productId)
                return product
        }
        return null
    }

    fun setDescription(productId: Long, description: String?) {
        val product = this.getProduct(productId)
        product?.description = description
    }

    fun getDescription(productId: Long): String? {
        val product = this.getProduct(productId)
        return product?.description
    }


    /**
     *
     * Selected products methods
     *
     */

    fun addSelectedProduct(product: Product) {
        val current = ArrayList(this.selectedProducts.value)
        if(!this.containsSelectedProduct(product.id))
            current.add(product)
        this.selectedProducts.value = current
    }

    fun removeSelectedProduct(productId: Long) {
        val current = ArrayList(this.selectedProducts.value)
        for(product in current) {
            if(product.id == productId) {
                current.remove(product)
                break
            }
        }
        this.selectedProducts.value = current
    }

    fun containsSelectedProduct(productId: Long): Boolean {
        val current = ArrayList(this.selectedProducts.value)
        for(product in current) {
            if(product.id == productId)
                return true
        }
        return false
    }

    fun getSelectedProducts(): List<Product> {
        return ArrayList(this.selectedProducts.value)
    }

    fun isSelectedProductsEmpty(): Boolean {
        return ArrayList(this.selectedProducts.value).size == 0
    }

    /**
     * Ricette Logic
     */
    fun getRicette(): Array<Ricetta> {
        return ricette
    }

    fun getRicetta(ricettaId:Long): Ricetta {
        for (ricetta in ricette) {
            if(ricetta.id==ricettaId ) {
                return ricetta
            }
        }
        throw Resources.NotFoundException("ricetta non trovata")
    }
    fun setSelectedRicetta(ricettaId:Long): Ricetta {
        if(ricettaId != selectedRicetta.value.id) {
            selectedRicetta.value = getRicetta(ricettaId).copy()
        }
        return selectedRicetta.value
    }

    fun getSelectedRicetta(): Ricetta {
        return selectedRicetta.value
    }
    fun isInSelectedRicettaList(productId: Long): Boolean {
        for(product in selectedRicetta.value.ingredienti) {
            if(product.id == productId)
                return true
        }
        return false
    }
    fun getSelectedRicettaProducts(): List<Product> {
        return ArrayList(selectedRicetta.value.ingredienti)
    }

    fun addToSelectedRicetta(productId:Long) {
        val current = ArrayList(selectedRicetta.value.ingredienti)
        for(ricetta in getRicette()) {
            if(ricetta.id == selectedRicetta.value.id) {
                for(product in ricetta.ingredienti) {
                    if(product.id == productId) {
                        current.add(product)
                    }
                }
            }
        }
        selectedRicetta.value.ingredienti = current
    }
    fun removeFromSelectedRicetta(productId:Long) {
        val current = ArrayList(selectedRicetta.value.ingredienti)
        for(product in current) {
            if(product.id == productId) {
                current.remove(product)
                break
            }
        }
        selectedRicetta.value.ingredienti = current
    }

    fun addselectedRicettaListToSelectedProducts() {
        for(product in selectedRicetta.value.ingredienti) {
            addSelectedProduct(product)
        }
    }


}