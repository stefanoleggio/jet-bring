package com.example.jet_bring.ui.profilo

import android.graphics.drawable.AdaptiveIconDrawable
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsActions.OnClick
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jet_bring.ui.liste.ListeScreen
import com.example.jet_bring.ui.theme.JetbringTheme
val padding = 16.dp
@Composable
fun ProfiloScreen() {
    val fontSize = 24.sp
    val name = "Ciccio"

    JetbringTheme() {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(padding,padding)) {
            //item{Spacer(Modifier.size(padding))}
            item{Text(
                "Hi, $name",
                style = TextStyle(color = MaterialTheme.colors.onBackground, fontSize = fontSize )
            )}
            item{Spacer(Modifier.size(padding))}

            item {
                Column(modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,) {
                    Surface(
                        modifier = Modifier
                            .size(150.dp),
                        shape = CircleShape,
                        color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f),

                        ) {
                        /*TODO putting here an image*/
                    }
                }
            }
            item {Spacer(Modifier.size(padding))}
            items(items = tableDataList) {
                Table(it.title,it.iconTitle)
                Spacer(Modifier.padding(padding))
            }
        }
        /*
    Text(
        text = "Profilo",
        style = TextStyle(color = MaterialTheme.colors.onBackground, fontSize = 36.sp),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    )
     */
    }
}

@Composable
private fun Table(
    title: String,
    iconTitle:ImageVector
)   {
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        shape= RoundedCornerShape(10.dp),
        color = MaterialTheme.colors.surface

    ) {
        Column(
        ) {
            HeaderBox(title = title, icon = iconTitle)
            Spacer(modifier = Modifier.padding(padding/4))
            Surface(
                Modifier
                    .padding(PaddingValues(
                            start = padding,
                            end = padding/4
                        )),
                color = MaterialTheme.colors.background
            ){
                Spacer( modifier = Modifier
                    .padding(1.dp)
                    .fillMaxWidth()
                )
            }

            Text(text = "ciao")
            //ClickableBox(title = "My new Box", onItemClicked = ListeScreen())
        }
    }
}

@Composable
private fun HeaderBox(
    title: String,
    icon:ImageVector
) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.surface)
            .padding(PaddingValues(
                start = padding,
                top = padding,
                end = 5.dp
            )
        )
    )
    {
        Icon(
            icon,
            contentDescription = null)
        Spacer(modifier = Modifier.padding(padding/4))
        Text(text = title)
        //
    }
}

@Composable
private fun ClickableBox(
    title: String,

) {
    Surface(
        Modifier
            .padding(padding / 4)
            .fillMaxWidth()
    ) {
        Text(text = title)
    }
}


@Composable
@Preview
fun ScreenPreview() {
    ProfiloScreen()
}

@Composable
@Preview
fun TablePreview() {
    Table(
        "tablePreview",
        Icons.Default.Settings

    )
}

@Composable
@Preview
fun ClickableBoxPreview() {
    ClickableBox("Clickable Box Preview")
}
