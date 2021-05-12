package com.example.jet_bring.ui.profilo

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

data class TableData(
    val title: String,
    val iconTitle: ImageVector
)
/*TODO correggere le icone per farle corrispondere a quelle originali*/
val tableDataList = listOf<TableData>(
    TableData("Impostazioni", Icons.Default.Settings),
    TableData("Guida e Consigli",Icons.Default.Info),
    TableData("Consigliala",Icons.Default.Lock),
    TableData("Altre Opzioni di Jet-Bring",Icons.Default.ArrowForward),
    TableData("Altro",Icons.Default.ThumbUp)
)