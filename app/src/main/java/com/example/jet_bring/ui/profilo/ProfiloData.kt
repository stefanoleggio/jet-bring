package com.example.jet_bring.ui.profilo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.jet_bring.R


/**
 * data class utilizzata per il salvataggio dei dati utente
 */
data class UserData(
    var name: String = "John Doe",
    var email: String = "john_doe@gmail.com",
    var profileIcon: Int = R.drawable.ic_profile
)
/**
 * data class utilizzata per i dati utilizzati per generare le tables in profilo
 */
data class TableData(
    val title: String,
    val iconTitle: ImageVector,
    val list: List<CBoxArg> = listOf()
)
/**
 * data class utilizzata per gli elementi delle table
 */
data class CBoxArg(
    val title: String,
    val link: String
)
/**
 * lista di dati utilizzata per generare le table in profilo
 */
/*TODO correggere le icone per farle corrispondere a quelle originali*/
val tableDataList = listOf(
    TableData("Impostazioni", Icons.Default.Settings)
)

