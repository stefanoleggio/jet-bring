package com.example.jet_bring.ui.ispirazione.components

/**
 * Data class usata per memorizzare le informazioni delle ricette
 */
data class Ricetta (
    val id: Int? = null,
    val titolo: String? = null,
    val pubblicatore: String? = null,
    val immagine: String? = null,
    val sourceUrl: String? = null,
    val voti: Int? = 0,
    val descrizione: String? = null,
    val ingredienti: List<String> = listOf(),
    val dateAdded: String? = null,
)