package com.example.jet_bring.ui.ispirazione

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration




@Composable
fun paddingBasedOnOrientation() : Int{
    if(LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE){
        return 12
    }else{
        return 6
    }

}