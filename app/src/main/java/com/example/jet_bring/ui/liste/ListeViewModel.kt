package com.example.jet_bring.ui.liste

import android.content.res.Resources
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.ViewModel
import com.example.jet_bring.R
import com.example.jet_bring.currentRoute
import com.example.jet_bring.model.*
import java.lang.Math.ceil
import java.lang.Math.floor

class ListeViewModel : ViewModel() {


    val selectedProducts: MutableState<List<Product>> = mutableStateOf(ArrayList())

    val selectedRicette: MutableState<List<Ricetta>> = mutableStateOf(ArrayList(setSelectedRicette()))

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

    fun getProduct(productId: Long): Product {
        for (product in products) {
            if(product.id == productId)
                return product
        }
        throw Resources.NotFoundException("prodotto non trovato")
    }
    fun getSelectedProduct(productId: Long): Product {
        for (product in selectedProducts.value) {
            if(product.id == productId)
                return product
        }
        throw Resources.NotFoundException("Prodotto non presente nella lista di prodotti selezionati")
    }
    fun setDescription(productId: Long, description: String?) {
        if(isInSelectedProduct(productId)) {
            val product = getSelectedProduct(productId)
            product?.description = description
        }
            val product = getProduct(productId)
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
        if(!this.isInSelectedProduct(product.id))
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

    fun isInSelectedProduct(productId: Long): Boolean {
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

    fun setSelectedRicette(): ArrayList<Ricetta> {
        val toRet = ArrayList<Ricetta>()
        for (ricetta in ricette) {
            toRet.add(ricetta.copy())
        }
        return toRet
    }

    fun getRicette(): ArrayList<Ricetta> {
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
    fun getSelectedRicetta(ricettaId: Long): Ricetta {
        for(ricetta in selectedRicette.value) {
            if(ricetta.id == ricettaId)
               return ricetta
        }
        throw Resources.NotFoundException("Ricetta non trovata")
    }

    fun resetRicetta(ricettaId: Long) {
        val ricettaToAdd = getRicetta(ricettaId)
        val tempSels = ArrayList(selectedRicette.value)
        val toRemove = getSelectedRicetta(ricettaId)
        tempSels.remove(toRemove)
        tempSels.add(ricettaToAdd)
        selectedRicette.value = tempSels
    }
    fun isInSelectedRicettaList(productId: Long,ricettaId: Long): Boolean {
        for(product in getSelectedRicetta(ricettaId).ingredienti) {
            if(product.id == productId)
                return true
        }
        return false
    }
    fun getSelectedRicettaProducts(ricettaId:Long): List<Product> {
        return ArrayList(getSelectedRicetta(ricettaId).ingredienti)
    }

    fun addToSelectedRicetta(productId:Long,ricettaId: Long) {
        val ricettaToAdd = getSelectedRicetta(ricettaId).copy()
        for(ricetta in getRicette()) {
            if(ricetta.id == getSelectedRicetta(ricettaId).id) {
                for(product in ricetta.ingredienti) {
                    if(product.id == productId) {
                        val tempList = ArrayList(ricettaToAdd.ingredienti)
                        tempList.add(product)
                        ricettaToAdd.ingredienti = tempList
                        break
                    }
                }
            }
        }
        val tempSels = ArrayList(selectedRicette.value)
        val toRemove = getSelectedRicetta(ricettaId)
        tempSels.remove(toRemove)
        tempSels.add(ricettaToAdd)
        selectedRicette.value = tempSels
    }
    fun removeFromSelectedRicetta(productId:Long,ricettaId: Long) {
        val ricettaToAdd = getSelectedRicetta(ricettaId).copy()
        for(product in ricettaToAdd.ingredienti) {
            if(product.id == productId) {
                val tempList = ArrayList(ricettaToAdd.ingredienti)
                tempList.remove(product)
                ricettaToAdd.ingredienti = tempList
                break
            }
        }
        val tempSels = ArrayList(selectedRicette.value)
        val toRemove = getSelectedRicetta(ricettaId)
        tempSels.remove(toRemove)
        tempSels.add(ricettaToAdd)
        selectedRicette.value = tempSels
    }

    fun addselectedRicettaListToSelectedProducts(ricettaId: Long) {
        for(product in getSelectedRicetta(ricettaId).ingredienti) {
            var prodDesc = product.description
            if(isInSelectedProduct(product.id)) {
                val currentDesc = getSelectedProduct(product.id).description
                prodDesc = "${if(currentDesc == null) "1" else currentDesc} + $prodDesc"
            }
            addSelectedProduct(product)
            setDescription(product.id,prodDesc)
        }
    }

    fun setRicettaProductDescription(productId: Long,ricettaId: Long,description: String?) {
        for (product in getSelectedRicettaProducts(ricettaId)) {
            if(product.id == productId) {
                product.description = description
            }
        }

    }

    fun getRicettaProductDescription(productId: Long,ricettaId: Long):String? {
        for (product in getSelectedRicettaProducts(ricettaId)) {
            if(product.id == productId) {
                return product.description
            }
        }
        return null
    }
}