package com.example.jet_bring.ui.profilo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProfiloViewModel : ViewModel() {

    var user by mutableStateOf(UserData())
    var temp by mutableStateOf(UserData())


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

}