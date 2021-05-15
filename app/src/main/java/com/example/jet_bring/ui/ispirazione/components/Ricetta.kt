package com.example.jet_bring.ui.ispirazione.components

/**
 * Data class usata per memorizzare le informazioni delle ricette
 */
data class Ricetta (
    var id: Int? = 1,
    var titolo: String? = "titolo",
    var pubblicatore: String? = "descrizione",
    var immagine: Int? = null,
    var sourceUrl: String? = "http://",
    var voti: Int? = 0,
    var descrizione: String? = "descrizione",
    var ingredienti: List<String> = listOf(),
    var dateAdded: String? = null,
)