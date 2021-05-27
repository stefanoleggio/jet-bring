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
    val listeViewModel = ListeViewModel()


    val ricetteList: MutableState<List<Ricetta>> = mutableStateOf(ArrayList())


    fun inizialize(){
        for(ricetta in ricette){
            val current = ArrayList(this.ricetteList.value)
            current.add(ricetta)
            this.ricetteList.value = current
        }
    }

    fun getricetteList(): List<Ricetta> {
        return ArrayList(this.ricetteList.value)
    }



    fun addRicetta() {
        this.onSaveDone()
        val current = ArrayList(this.ricetteList.value)
        current.add(ricetta)
        this.ricetteList.value = current
    }


    /*
    * funzioni per cambiare la ricetta corrente
     */
    fun onTitoloChange(newName: String) {
        ricetta = Ricetta(id = ricetta.id, titolo = newName, descrizione = ricetta.descrizione, pubblicatore = ricetta.pubblicatore, immagine = ricetta.immagine, sourceUrl = ricetta.sourceUrl, voti = ricetta.voti, ingredienti = ricetta.ingredienti )

    }

    fun onDescrizioneChange(newName: String) {
        ricetta = Ricetta(id = ricetta.id, titolo = ricetta.titolo, descrizione = newName, pubblicatore = ricetta.pubblicatore, immagine = ricetta.immagine, sourceUrl = ricetta.sourceUrl, voti = ricetta.voti, ingredienti = ricetta.ingredienti )

    }

    fun onLinkChange(newName: String) {
        ricetta = Ricetta(id = ricetta.id, titolo = ricetta.titolo, descrizione = ricetta.descrizione, pubblicatore = ricetta.pubblicatore, immagine = ricetta.immagine, sourceUrl = newName, voti = ricetta.voti, ingredienti = ricetta.ingredienti )

    }

    fun onSaveDone() {

        ricetta = Ricetta(id = (ricette.lastIndex.toLong()+1), titolo = ricetta.titolo, descrizione = ricetta.descrizione, pubblicatore = ricetta.pubblicatore, immagine = R.drawable.empty_plate, sourceUrl = ricetta.sourceUrl, voti = ricetta.voti, ingredienti = listeViewModel.selectedProducts.component1()

        )

        ricette.plus(ricetta)
    }


}