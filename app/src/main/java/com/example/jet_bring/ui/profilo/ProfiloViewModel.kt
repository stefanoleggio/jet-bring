package com.example.jet_bring.ui.profilo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ProfiloViewModel : ViewModel() {

    var user by mutableStateOf(UserData())

    fun editProfileName(userName:String) {
        user.name = userName
    }
    fun editProfileEmail(userEmail:String) {
        user.email = userEmail
    }
    fun editProfileIcon(userMod: UserData) {
        user.profileIcon = userMod.profileIcon
    }

    fun onEditDone() {}

}