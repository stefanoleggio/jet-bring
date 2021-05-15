package com.example.jet_bring.ui.ispirazione

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.jet_bring.ui.ispirazione.components.Ricetta


class AddRicettaViewModel : ViewModel() {

    var ricetta by mutableStateOf(Ricetta())

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
        ricetta = ricetta
    }


}