package com.example.jet_bring.ui.profilo

import android.content.res.Resources
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.jet_bring.ui.theme.Theme
import com.example.jet_bring.ui.theme.themes


class ProfiloViewModel : ViewModel() {

    /**
     * mantiene i dati utente del profilo
     */
    var user by mutableStateOf(UserData())

    /**
     * mantiene le modifiche temporanee non salvate effettuate al profilo
     */
    var temp by mutableStateOf(UserData())

    /**
     * mantiene lo stato in cui vengono visualizzati i prodotti
     */
    private var columnMode: MutableState<Boolean> = mutableStateOf(false)

    /**
     * ritorna il tema tramite il quale è visualizzata l'applicazione
     */
    private var theme: MutableState<Theme> = mutableStateOf(themes.get(0))

    /**
     * salva se il tema è stato impostato o meno
     */
    private var themeSet: MutableState<Boolean> = mutableStateOf(false)

    /**
     * mantiene la condizione in cui è visualizzata l'applicazione: "Portrait" o "Landscape"
     */
    var state by mutableStateOf("Potrait")

    /**
     * mantiene il numero di colonne utilizzate per la visualizzazione in griglia dei prodotti
     */
    var columnNumber by mutableStateOf(calculateColumnsNumber())
    /**
     * effettua una modifica temporanea allo username del profilo
     */
    fun editProfileName(userName:String) {
        temp = UserData(userName,temp.email,temp.profileIcon)
    }

    /**
     * effettua una modifica temporanea alla mail del profilo
     */
    fun editProfileEmail(userEmail:String) {
        temp = UserData(temp.name,userEmail,temp.profileIcon)
    }

    /**
     * salva le modifiche al profilo
     */
    fun onSaveDone() {
        user = temp
    }

    /**
     * ripristina i valori nel caso in cui non si salvino le modifiche al profilo
     */
    fun notSaved() {
        temp = user
    }

    /**
     * ritorna true se l'applicazione rappresenta i prodotti in colonna
     */
    fun isColumnMode(): Boolean {
        return columnMode.value
    }
    /**
     * imposta la visualizzazione dei prodotti nell'applicazione in Colonna o griglia a seconda
     * del parametro passato
     */
    fun setMode(state: Int) {
        if(state == 1) columnMode()
        else if(state ==0) gridMode()
        else throw ArrayIndexOutOfBoundsException( "Il valore $state non è 0 o 1 come richiesto da setMode")
    }
    /**
     * imposta la visualizzazione dei prodotti nell'applicazione in Colonna
     */
    fun columnMode() {
        columnMode.value = true
    }

    /**
     * imposta la visualizzazione dei prodotti nell'applicazione in griglia
     */
    fun gridMode() {
        columnMode.value = false
    }

    /**
     * seleziona il tema
     */
    fun setTheme(index:Int) {
        theme.value= themes.get(index)
        themeSet.value = true
    }

    /**
     * ritorna il tema selezionato
     */
    fun getSelectedTheme():Theme {
        return theme.value
    }
    /**
     * ritorna true se il tema è impostato
     */
    fun isSet():Boolean {
        return themeSet.value
    }

    /**
     * ritorna la lista di temi tra cui è possibile scegliere
     */
    fun getThemeList(): List<String> {
        val toRet = ArrayList<String>()
        themes.forEach {
            toRet.add(it.description)
        }
        return toRet
    }


    /**
     * calcola il numero di colonne utilizzabili ricavata la larghezza dello schermo al momento
     * del calcolo
     */
    fun calculateColumnsNumber(): Int {
        val width = getDp(Resources.getSystem().displayMetrics.widthPixels)
        val minSize = 120
        val paddingSide = 32
        return (width - paddingSide) / minSize
    }

    /**
     * imposta il numero di colonne da utilizzare
     */
    fun setColumnNumber() {
        columnNumber = calculateColumnsNumber()
    }

    /**
     * funzione che permette di ricavare la dimensione dello schermo in dp
     * fornita la dimensione in pixel
     */
    private fun getDp(valInPx: Int):Int {
        return (valInPx * 160/ (Resources.getSystem().displayMetrics.densityDpi))
    }
}
