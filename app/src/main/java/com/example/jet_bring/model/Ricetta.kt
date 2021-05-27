package com.example.jet_bring.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.jet_bring.R

/**
 * Data class usata per memorizzare le informazioni delle ricette
 */
data class Ricetta(
    var id: Long? = -1,
    var titolo: String? = "Nome Modello",
    var pubblicatore: String? = "Io",
    var immagine: Int? = null,
    var sourceUrl: String? = "http://",
    var voti: Int? = 0,
    var persone: Int? = 0,
    var descrizione: String? = "descrizione",
    var ingredienti: List<Product> = listOf(),
    var dateAdded: String? = null,
)


val ricette = arrayListOf(

    Ricetta(
        0,
        "Brioche con Fragole",
        "Il buongiorno si vede dal mattino",
        R.drawable.brioche_con_fragole_e_crema_5759_720x480,
        "https://blog.giallozafferano.it/maniamore/brioche-crema-fragole/",
        44,
        3,
        "Sano e delizioso",

        listOf(
            getProduct("Cornetti", "2"),
            getProduct("Fragole", "2"),
            getProduct("Zenzero", "un po'"),
        ),


        "24/4/2021"),



    Ricetta(
        1,
        "Crostini alla Montanara",
        "Giallo Zafferano",
        R.drawable.crostoni_pancetta_funghi,
        "https://www.tavolartegusto.it/ricetta/crostini-di-pane/",
        502,
        2,
        "Buoni e gustosi",
        listOf(
            getProduct("Pane", "200 g"),
            getProduct("Pancetta", "100 g"),
            getProduct("Funghi", "20 pezzi"),
        ),
        "20/4/2021"),

    Ricetta(
        2,
        "Toast alla Zucca e Pollo",
        "RicetteMagazine",
        R.drawable.petto_di_pollo_alla_zucca,
        "https://blog.giallozafferano.it/allacciateilgrembiule/petto-di-pollo-alla-zucca/",
        5,
        1,
        "Toast buonissimo",
        listOf(
            getProduct("Toast", "2 fette"),
            getProduct("Pollo", "1 fetta"),
            getProduct("Zucca", "50 g"),

        ),
        "21/4/2021"),

    Ricetta(
        3,
        "Insalata Vegana",
        "MondoVegano",
        R.drawable.insalata_vegana,
        "https://www.fonzietheburgershouse.com/insalata-vegana/",
        70,
        2,
        "Salutare e dietetico",
        listOf(
            getProduct("Carote", "2"),
            getProduct("Insalata", "2 foglie"),
            getProduct("Pomodori", "1"),
            getProduct("Cipolla", "1"),
            getProduct("Limone", "q.b."),

    ), "22/6/2020"),

    Ricetta(
        4,
        "Brioche con Mela",
        "Il buongiorno si vede dal mattino",
        R.drawable.brioche_alle_mele_e_cannella_ricetta_dolce,
        "https://blog.giallozafferano.it/studentiaifornelli/brioche-alle-mele/",
        24,
        3,
        "Sano e delizioso",

        listOf(
            getProduct("Cornetti", "2"),
            getProduct("Mela", "2"),
            getProduct("Zenzero", "un po'"),

            ),

        "24/4/2021"),
)