package com.example.jet_bring.ui.profilo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun ProfiloScreen() {
    Text(text = "Profilo",
        style = TextStyle(color = Color.Black, fontSize = 36.sp),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray))
}