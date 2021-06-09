package com.example.jet_bring

import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.jet_bring.ui.liste.ListeViewModel
import com.example.jet_bring.ui.profilo.ProfiloViewModel
import com.example.jet_bring.ui.theme.JetbringTheme

class MainActivity : AppCompatActivity() {

    /**
     * Creazione ViewModel
     */
    val profiloViewModel  = ProfiloViewModel()
    val listeViewModel = ListeViewModel()

    @RequiresApi(Build.VERSION_CODES.R)
    @ExperimentalAnimationApi
    @ExperimentalFoundationApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetbringTheme(profiloViewModel.getSelectedTheme(),profiloViewModel.isSet()) {
                JetbringApp(profiloViewModel, listeViewModel)
            }
        }
    }
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            profiloViewModel.state = "Landscape" // this will automatically change the text to landscape
            profiloViewModel.setColumnNumber()
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            profiloViewModel.state = "Potrait"   // this will automatically change the text to potrait
            profiloViewModel.setColumnNumber()
        }
    }
}
