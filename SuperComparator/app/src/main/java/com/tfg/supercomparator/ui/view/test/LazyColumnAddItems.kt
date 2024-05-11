package com.tfg.supercomparator.ui.view.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Preview
@Composable
fun LazyVerticalGridWithAddButton() {
    val items = remember { mutableStateListOf("Elemento 1", "Elemento 2", "", "", "", "") }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.padding(16.dp)) {
        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            items(items) { item ->
                ListItem(text = item) {
                    items.remove(item)
                }
            }
        }

        Button(onClick = {
            val newElement = "Nuevo elemento"
            items.add(newElement) // Add element outside ListItem
            scope.launch {
                listState.animateScrollToItem(items.lastIndex)
            }
        }) {
            Text("Añadir elemento")
        }
    }
}

@Preview
@Composable
fun LazyColumnWithAddButton() {
    val items = remember { mutableStateListOf("Elemento 1", "Elemento 2") }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Column {
        LazyColumn(state = listState) {
            items(items) { item ->
                ListItem(text = item) {
                    items.remove(item)
                }
            }
        }

        Button(onClick = {
            val newElement = "Nuevo elemento"
            items.add(newElement) // Add element outside ListItem
            scope.launch {
                listState.animateScrollToItem(items.lastIndex)
            }
        }) {
            Text("Añadir elemento")
        }
    }
}


@Composable
fun ListItem(text: String, onClick: () -> Unit) {
    Card(onClick = onClick) {
        Text(text)
    }
}


@Composable
fun ThreeItemsPerRowList(items: List<String>) {
    LazyColumn {
        items.chunked(3) { chunk ->
            item {
                Row(Modifier.fillMaxWidth()) {
                    chunk.forEach { item ->
                        Item1(item)
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun Item1(item: String) {
    Text(text = item)
}


@Preview
@Composable
fun ejemplo() {
    val items =
        listOf("Elemento 1", "Elemento 2", "Elemento 3", "Elemento 4", "Elemento 5", "Elemento 6")
    ThreeItemsPerRowList(items = items)
}


@Preview
@Composable
fun PreviewLazyGrid() {
    LazyGridWithThreeColumns()
}

@Composable
fun LazyGridWithThreeColumns() {
    // Declares the list of items as a mutable state
    var items by remember { mutableStateOf(generateItems()) }

    Column(modifier = Modifier.padding(16.dp)) {
        Button(
            onClick = {
                // Add a new item to the list when the button is clicked
                items = items.toMutableList().apply { add("New Item ${items.size + 1}") }
            },
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text("Add Item")
        }

        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            items(items.size) { index ->
                // Here you can add any element you want to display in the grid
                Text(text = items[index])
            }
        }
    }
}

// Utility function to generate a list of example items
private fun generateItems(): List<String> {
    return List(15) { index -> "Item ${index + 1}" }
}

