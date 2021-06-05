package com.example.jet_bring.ui.ispirazione

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.jet_bring.R
import com.example.jet_bring.model.Product
import com.example.jet_bring.model.Ricetta
import com.example.jet_bring.model.ricette
import com.example.jet_bring.ui.liste.ListeViewModel


class AddRicettaViewModel : ViewModel() {

    var ricetta by mutableStateOf(Ricetta())
    var listeViewModel = ListeViewModel()





    fun addRicetta() {
        this.onSaveDone()
        ricette.add(this.ricetta)
    }


    /*
    * funzioni per cambiare la ricetta corrente
     */
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


    fun onSaveDone() {
        ricetta = Ricetta(id = (ricette.lastIndex.toLong()+1), titolo = ricetta.titolo, descrizione = ricetta.descrizione, pubblicatore = ricetta.pubblicatore, immagine = R.drawable.empty_plate, sourceUrl = ricetta.sourceUrl, voti = ricetta.voti, ingredienti = listeViewModel.selectedProducts.component1(), persone = ricetta.persone)
        ricette.plus(ricetta)

    }

    fun resetRicetta(){
        ricetta = Ricetta()
        listeViewModel = ListeViewModel()
    }


}