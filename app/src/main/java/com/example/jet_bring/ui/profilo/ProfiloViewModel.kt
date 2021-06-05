package com.example.jet_bring.ui.profilo

import android.content.res.Resources
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import com.example.jet_bring.ui.theme.Theme
import com.example.jet_bring.ui.theme.themes


class ProfiloViewModel : ViewModel() {

    var user by mutableStateOf(UserData())
    var temp by mutableStateOf(UserData())
    private var columnMode: MutableState<Boolean> = mutableStateOf(false)
    private var theme: MutableState<Theme> = mutableStateOf(themes.get(0))
    private var themeSet: MutableState<Boolean> = mutableStateOf(false)

    fun editProfileName(userName:String) {
        temp = UserData(userName,temp.email,temp.profileIcon)
    }
    fun editProfileEmail(userEmail:String) {
        temp = UserData(temp.name,userEmail,temp.profileIcon)
    }
    fun editProfileIcon(userIcon:Int) {
        temp = UserData(temp.name,temp.email,userIcon)
    }

    fun onSaveDone() {
        user = temp
    }

    fun notSaved() {
        temp = user
    }

    /**
     * columnMode handling
     */
    fun isColumnMode(): Boolean {
        return columnMode.value
    }
    fun setMode(state: Int) {
        if(state == 1) columnMode()
        else if(state ==0) gridMode()
        else throw ArrayIndexOutOfBoundsException( "Il valore $state non è 0 o 1 come richiesto da setMode")
    }
    fun columnMode() {
        columnMode.value = true
    }

    fun gridMode() {
        columnMode.value = false
    }

    /**
     * theme choice
     */
    fun setTheme(index:Int) {
        theme.value= themes.get(index)
        themeSet.value = true
    }
    fun getSelectedTheme():Theme {
        return theme.value
    }
    fun isSet():Boolean {
        return themeSet.value
    }
    fun getThemeList(): List<String> {
        val toRet = ArrayList<String>()
        themes.forEach {
            toRet.add(it.description)
        }
        return toRet
    }



    fun calculateColumnsNumber(): Int {
        val width = getDp(Resources.getSystem().displayMetrics.widthPixels)
        val minSize = 120
        val paddingSide = 32
        return (width - paddingSide) / minSize
    }

    private fun getDp(valInPx: Int):Int {
        return (valInPx * 160/ (Resources.getSystem().displayMetrics.densityDpi))
    }
}
