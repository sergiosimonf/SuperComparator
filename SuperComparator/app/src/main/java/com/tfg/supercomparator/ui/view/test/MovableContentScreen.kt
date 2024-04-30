package com.tfg.supercomparator.ui.view.test

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.movableContentOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Preview
@Composable
fun MovableContentScreen() {
    ListImageController()
}

@Composable
fun ListImageController() {
    Text(
        text = "Pulsa el boton para añadir una imagen a la lista"
    )

    var list: MutableList<Item> = mutableListOf()

    Row (
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(10.dp)
    ){
        list = remember {
            mutableStateListOf(
                Item(url = "https://supermercado.eroski.es/images/22445217.jpg"),
                Item(url = "https://supermercado.eroski.es/images/22445217.jpg"),
                Item(url = "https://supermercado.eroski.es/images/22445217.jpg")
            )
        }

        val movableItems =
            list.map { item ->
                movableContentOf {
                    AsyncImage(
                        model = item.url,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .aspectRatio(1280f / 847f)
                    )
                }
            }

        list.forEachIndexed { index, item ->
            movableItems[index]()
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            list.removeFirstOrNull()
        }) {
            Text(text = "Delete First Item")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            list.add(Item(url = "https://www.pngmart.com/files/11/Dank-Meme-Transparent-Background.png"))
        }) {
            Text(text = "Add First Item")
        }
    }



}

internal data class Item(val url: String)