package com.example.jet_bring.ui.ispirazione

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.jet_bring.R
import com.example.jet_bring.model.*

/**
 * ViewModel usato per l'AddRicetta
 * */

class AddRicettaViewModel : ViewModel() {

    var ricetta by mutableStateOf(Ricetta())
    val prodottiAddRicetta = getProdutsWithOutComments()

    /**
     * Funzione che salva la ricetta
     * */

    fun addRicetta() {
        this.onSaveDone()
        ricette.add(this.ricetta)
    }



    /**
     * Funzioni che modificano la ricetta
     * */
    fun onTitoloChange(newName: String) {
        ricetta = Ricetta(id = ricetta.id, titolo = newName, descrizione = ricetta.descrizione, pubblicatore = ricetta.pubblicatore, immagine = ricetta.immagine, sourceUrl = ricetta.sourceUrl, voti = ricetta.voti, ingredienti = ricetta.ingredienti, persone = ricetta.persone )

    }

    fun onDescrizioneChange(newName: String) {
        ricetta = Ricetta(id = ricetta.id, titolo = ricetta.titolo, descrizione = newName, pubblicatore = ricetta.pubblicatore, immagine = ricetta.immagine, sourceUrl = ricetta.sourceUrl, voti = ricetta.voti, ingredienti = ricetta.ingredienti, persone = ricetta.persone )

    }

    fun onLinkChange(newName: String) {
        ricetta = Ricetta(id = ricetta.id, titolo = ricetta.titolo, descrizione = ricetta.descrizione, pubblicatore = ricetta.pubblicatore, immagine = ricetta.immagine, sourceUrl = newName, voti = ricetta.voti, ingredienti = ricetta.ingredienti, persone = ricetta.persone )

    }

    fun onPersonChange(function: String) {
        if(function == "plus")
            ricetta = Ricetta(id = ricetta.id, titolo = ricetta.titolo, descrizione = ricetta.descrizione, pubblicatore = ricetta.pubblicatore, immagine = ricetta.immagine, sourceUrl = ricetta.sourceUrl, voti = ricetta.voti, ingredienti = ricetta.ingredienti, persone = ricetta.persone?.plus(1))
        else if(function == "minus" && ricetta.persone!! > 1)
            ricetta = Ricetta(id = ricetta.id, titolo = ricetta.titolo, descrizione = ricetta.descrizione, pubblicatore = ricetta.pubblicatore, immagine = ricetta.immagine, sourceUrl = ricetta.sourceUrl, voti = ricetta.voti, ingredienti = ricetta.ingredienti, persone = ricetta.persone?.minus(1))
        else if(function == "minus" && ricetta.persone!! == 1)
            ricetta = Ricetta(id = ricetta.id, titolo = ricetta.titolo, descrizione = ricetta.descrizione, pubblicatore = ricetta.pubblicatore, immagine = ricetta.immagine, sourceUrl = ricetta.sourceUrl, voti = ricetta.voti, ingredienti = ricetta.ingredienti, persone = ricetta.persone)
        else throw ArrayIndexOutOfBoundsException( "Il valore $function non è un operatore per la funzione PersonChange")
    }


    fun isInSelectedProductsRicetta(productId: Long): Boolean {
        val current = ArrayList(ricetta.ingredienti)
        for(product in current) {
            if(product.id == productId)
                return true
        }
        return false
    }

    fun removeSelectedProductsRicetta(productId: Long) {
        val current = ArrayList(ricetta.ingredienti)

        for(product in current) {
            if(product.id == productId) {
                current.remove(product)
                break
            }
        }
        ricetta = Ricetta(id = ricetta.id, titolo = ricetta.titolo, descrizione = ricetta.descrizione, pubblicatore = ricetta.pubblicatore, immagine = R.drawable.empty_plate, sourceUrl = ricetta.sourceUrl, voti = ricetta.voti, ingredienti = current, persone = ricetta.persone)

    }

    fun addSelectedProductsRicetta(product: Product) {
        val current = ArrayList(ricetta.ingredienti)
        if(!this.isInSelectedProductsRicetta(product.id))
            current.add(product)
        ricetta = Ricetta(id = ricetta.id, titolo = ricetta.titolo, descrizione = ricetta.descrizione, pubblicatore = ricetta.pubblicatore, immagine = R.drawable.empty_plate, sourceUrl = ricetta.sourceUrl, voti = ricetta.voti, ingredienti = current, persone = ricetta.persone)

    }


    fun setNamePerson(name : String){
        ricetta = Ricetta(id = ricetta.id, titolo = ricetta.titolo, descrizione = ricetta.descrizione, pubblicatore = name, immagine = R.drawable.empty_plate, sourceUrl = ricetta.sourceUrl, voti = ricetta.voti, ingredienti = ricetta.ingredienti, persone = ricetta.persone)
    }

    fun onSaveDone() {

        val current = mutableListOf<Product>()

        for (prodotto in ricetta.ingredienti)
            prodotto.description?.let { getProduct(prodotto.name, it) }?.let { current.add(it) }

        ricetta = Ricetta(id = (ricette.lastIndex.toLong()+1), titolo = ricetta.titolo+" ", descrizione = ricetta.descrizione, pubblicatore = ricetta.pubblicatore, immagine = R.drawable.empty_plate, sourceUrl = ricetta.sourceUrl, voti = ricetta.voti, ingredienti = current, persone = ricetta.persone)
    }

    fun setDescriptionVirginProducts(productId: Long, description: String?){

        for(prodotto in prodottiAddRicetta){
            if(productId == prodotto.id)
                prodotto.description = description
        }
    }

    fun getProdutsWithOutComments():List<Product> {
        val current = mutableListOf<Product>()
        for (prodotto in products)
            current.add(getProduct(prodotto.name, "1"))

        return current
    }



}