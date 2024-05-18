package com.tfg.supercomparator.ui.view.components

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tfg.supercomparator.R
import com.tfg.supercomparator.ui.theme.DarkGreen
import com.tfg.supercomparator.ui.theme.Green
import com.tfg.supercomparator.viewModel.SearchScreemViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarM3() {

    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    val searchHistory = listOf("Android", "Kotlin", "Compose", "Material Design", "GPT-4")

    Column {
        SearchBar(
            query = query,
            onQueryChange = { query = it },
            onSearch = { newQuery ->
                println("Performing search on query: $newQuery")
            },
            active = active,
            onActiveChange = { active = it },
            placeholder = {
                Text(text = "Search")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            },
            trailingIcon = {
                Row {
//                    IconButton(onClick = { /* open mic dialog */ }) {
//                        Icon(
//                            painter = painterResource(R.drawable.microphone),
//                            contentDescription = "Mic",
//                            modifier = Modifier.size(24.dp)
//                        )
//                    }
                    if (active) {
                        IconButton(
                            onClick = { if (query.isNotEmpty()) query = "" else active = false }
                        ) {
                            Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
                        }
                    }
                }
            }
        ) {
            searchHistory.takeLast(3).forEach { item ->
                ListItem(
                    modifier = Modifier.clickable { query = item },
                    headlineContent = { Text(text = item) },
                    leadingContent = {
                        Icon(
                            painter = painterResource(R.drawable.history),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                )
            }
        }
        Text(text = "Search History")
    }
}

@SuppressLint("MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarSuperProducts(viewModel: SearchScreemViewModel) {
    val query: String by viewModel.query.observeAsState(initial = "")
    val seachBarActiveMode: Boolean by viewModel.seachBarActiveMode.observeAsState(initial = false)

    val scope = rememberCoroutineScope()

    val searchHistory by remember { mutableStateOf(mutableListOf<String>()) }

    val uiColor = if (isSystemInDarkTheme()) DarkGreen else Green

    DockedSearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
        query = query,
        onQueryChange = { viewModel.onQueryChanged(it) },
        onSearch = { newQuery ->
            if (newQuery.isNotEmpty()) {
                viewModel.unSeachBarActiveMode()
                viewModel.executeQuery()
                scope.launch {
                    withContext(Dispatchers.IO) {
                        Thread.sleep(100) // Pequeño delay para que no apareca en la animación
                        searchHistory.add(newQuery)
                    }
                }
            }
        },
        active = seachBarActiveMode,
        onActiveChange = { viewModel.onChangeSeachBarActiveMode(it) },
        placeholder = {
            Text(text = "Search")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
        },
        trailingIcon = {
            Row {
//                IconButton(onClick = { /* open mic dialog */ }) {
//                    Icon(painter = painterResource(R.drawable.microphone), contentDescription = "Mic", modifier = Modifier.size(24.dp))
//                }
                if (seachBarActiveMode) {
                    IconButton(
                        onClick = { if (query.isNotEmpty()) viewModel.queryClear() else viewModel.unSeachBarActiveMode() }
                    ) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
                    }
                }
            }
        }
    ) {
        searchHistory.takeLast(4).forEach { item ->
            ListItem(
                modifier = Modifier.clickable { viewModel.onQueryChanged(item) },
                headlineContent = { Text(text = item) },
                leadingContent = {
                    Icon(
                        painter = painterResource(R.drawable.history),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
        }
    }
}