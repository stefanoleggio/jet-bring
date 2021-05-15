package com.example.jet_bring.ui.profilo

import android.graphics.drawable.Drawable
import android.media.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Person
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jet_bring.R



data class UserData(
    var name: String = "John Doe",
    var email: String = "john_doe@gmail.com",
    var profileIcon: Int = R.drawable.jet_bringicon
)

data class TitleIcon(
    val title: String,
    val iconTitle: ImageVector
)

/*TODO correggere le icone per farle corrispondere a quelle originali*/
val tableDataList = listOf<TitleIcon>(
    TitleIcon("Impostazioni", Icons.Default.Settings),
    TitleIcon("Guida e Consigli",Icons.Default.Info),
    TitleIcon("Consigliala",Icons.Default.Lock),
    TitleIcon("Altre Opzioni di Jet-Bring",Icons.Default.ArrowForward),
    TitleIcon("Altro",Icons.Default.ThumbUp)
)

