package com.example.jet_bring.ui.liste

import android.content.res.Resources
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.jet_bring.model.*

class ListeViewModel : ViewModel() {


    /**
     * Variabile contenente i prodotti selezionati
     */
    val selectedProducts: MutableState<List<Product>> = mutableStateOf(ArrayList())

    /**
     * Variabile contenente le ricette selezionate
     */
    var selectedRicette: MutableState<List<Ricetta>> = mutableStateOf(ArrayList(setSelectedRicette()))


    /**
     *
     * Metodi per le categorie
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
     * Metodi per i prodotti
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
     * Metodi per la modifica dei prodotti selezionati
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
     * Metodi per le ricette
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
        for(ingrediente in getSelectedRicetta(ricettaId).ingredienti) {
            val product = getProduct(ingrediente.id)
            val ingDesc = ingrediente.description
            if(product.description == null || product.description == "") {
                if(isInSelectedProduct(product.id))
                    setDescription(product.id,"1 + $ingDesc")
                else
                    setDescription(product.id, ingDesc)
            } else {
                setDescription(product.id, product.description + " + " + ingDesc)
            }
            addSelectedProduct(product)
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