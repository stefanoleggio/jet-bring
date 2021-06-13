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
    var profileIcon: Int = R.drawable.ic_profile
)

data class TableData(
    val title: String,
    val iconTitle: ImageVector,
    val list: List<CBoxArg> = listOf()
)

data class CBoxArg(
    val title: String,
    val link: String
)

/*TODO correggere le icone per farle corrispondere a quelle originali*/
val tableDataList = listOf(
    TableData("Impostazioni", Icons.Default.Settings),
    /*
    TableData("Informazioni su JetBring",Icons.Default.ThumbUp)
    */
)

