package com.example.jet_bring

import android.annotation.SuppressLint
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.jet_bring.ui.ispirazione.AddRicettaViewModel
import com.example.jet_bring.ui.liste.ListeViewModel
import com.example.jet_bring.ui.profilo.ProfiloViewModel
import com.example.jet_bring.ui.theme.JetbringTheme
import com.example.jet_bring.ui.theme.Theme

class MainActivity : AppCompatActivity() {
    val profiloViewModel  = ProfiloViewModel()
    @RequiresApi(Build.VERSION_CODES.R)
    @ExperimentalAnimationApi
    @ExperimentalFoundationApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetbringTheme(profiloViewModel.getSelectedTheme(),profiloViewModel.isSet()) {
                JetbringApp(profiloViewModel)
            }
        }
    }
}
