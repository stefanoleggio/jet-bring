package com.example.jet_bring

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.ExperimentalComposeUiApi
import com.example.jet_bring.ui.profilo.ProfiloViewModel
import com.example.jet_bring.ui.theme.JetbringTheme

class MainActivity : AppCompatActivity() {

    @ExperimentalFoundationApi
    @ExperimentalComposeUiApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetbringTheme  {
                JetbringApp()
            }
        }
    }
}