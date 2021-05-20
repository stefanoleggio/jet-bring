package com.example.jet_bring.ui.profilo

import android.content.res.Resources
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProfiloViewModel : ViewModel() {

    var user by mutableStateOf(UserData())
    var temp by mutableStateOf(UserData())
    private var columnMode: MutableState<Boolean> = mutableStateOf(false)


    fun editProfileName(userName:String) {
        temp = UserData(userName,user.email,user.profileIcon)
    }
    fun editProfileEmail(userEmail:String) {
        temp = UserData(user.name,userEmail,user.profileIcon)
    }
    fun editProfileIcon(userMod: UserData) {
        temp = userMod
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
        return columnMode.component1()
    }

    fun columnMode() {
        columnMode = mutableStateOf<Boolean>(true)
    }

    fun gridMode() {
        columnMode = mutableStateOf(false)
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