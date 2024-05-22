package com.tfg.supercomparator.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.tfg.supercomparator.R
import com.tfg.supercomparator.domain.modules.data.AppDatabase
import com.tfg.supercomparator.ui.theme.DarkGreen
import com.tfg.supercomparator.ui.theme.Green
import com.tfg.supercomparator.ui.view.components.ProductContentSearch
import com.tfg.supercomparator.ui.view.components.SearchBarSuperProducts
import com.tfg.supercomparator.viewModel.SearchScreemViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    database: AppDatabase,
    viewModel: SearchScreemViewModel = SearchScreemViewModel(),
) {
    val queryExecuted: Boolean by viewModel.filters.observeAsState(initial = false)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        SearchBarSuperProducts(viewModel, database)
        Spacer(modifier = Modifier.height(25.dp))
        if (!queryExecuted) {
            SupercMarketsIcons(viewModel)
        }
        Spacer(modifier = Modifier.height(25.dp))
        Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier.fillMaxHeight()) {
            ProductContentSearch(viewModel, database)
        }
    }
}

@Composable
fun SupercMarketsIcons(viewModel: SearchScreemViewModel) {

    val ahorramasIconSearch: Boolean by viewModel.ahorramasIconSearch.observeAsState(initial = true)
    val alcampoIconSearch: Boolean by viewModel.alcampoIconSearch.observeAsState(initial = true)
    val carrefourIconSearch: Boolean by viewModel.carrefourIconSearch.observeAsState(initial = true)
    val diaIconSearch: Boolean by viewModel.diaIconSearch.observeAsState(initial = true)
    val eroskiIconSearch: Boolean by viewModel.eroskiIconSearch.observeAsState(initial = true)
    val hipercorIconSearch: Boolean by viewModel.hipercorIconSearch.observeAsState(initial = true)
    val mercadonaSearch: Boolean by viewModel.mercadonaSearch.observeAsState(initial = true)

    val ahorraMasIcon =
        if (ahorramasIconSearch) R.drawable.ahorramas else R.drawable.ahorramas_unactive
    val alcampoIcon = if (alcampoIconSearch) R.drawable.alcampo else R.drawable.alcampo_unactive
    val carrefourIcon =
        if (carrefourIconSearch) R.drawable.carrefour else R.drawable.carrefour_unactive
    val diaIcon = if (diaIconSearch) R.drawable.dia else R.drawable.dia_unactive
    val eroskiIcon = if (eroskiIconSearch) R.drawable.eroski else R.drawable.eroski_unactive
    val hipercorIcon = if (hipercorIconSearch) R.drawable.hipercor else R.drawable.hipercor_unative
    val mercadonaIcon = if (mercadonaSearch) R.drawable.mercadona else R.drawable.mercadona_unactive

    val padding = 22.dp
    val imageSize = 50.dp

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            IconButton(
                onClick = { viewModel.onTouchAhorramasIconSearch() },
                modifier = Modifier.padding(end = padding)
            ) {
                Card(modifier = Modifier.clip(RoundedCornerShape(11.dp))) {
                    Image(
                        painter = painterResource(id = ahorraMasIcon),
                        contentDescription = null,
                        modifier = Modifier
                            .height(imageSize)
                    )
                }
            }
            IconButton(
                onClick = { viewModel.onTouchAlcampoIconSearch() },
                modifier = Modifier.padding(end = padding)
            ) {
                Card(modifier = Modifier.clip(RoundedCornerShape(11.dp))) {
                    Image(
                        painter = painterResource(id = alcampoIcon),
                        contentDescription = null,
                        modifier = Modifier
                            .height(imageSize)
                    )
                }
            }
            IconButton(
                onClick = { viewModel.onTouchCarrefourIconSearch() },
                modifier = Modifier.padding(end = padding)
            ) {
                Image(
                    painter = painterResource(id = carrefourIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .height(imageSize)
                )
            }
            IconButton(onClick = { viewModel.onTouchDiaIconSearch() }) {
                Image(
                    painter = painterResource(id = diaIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .height(imageSize)
                        .background(Color.Transparent)
                )
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        Row(horizontalArrangement = Arrangement.Center) {
            IconButton(
                onClick = { viewModel.onTouchEroskiIconSearch() },
                modifier = Modifier.padding(end = padding)
            ) {
                Card(modifier = Modifier.clip(RoundedCornerShape(11.dp))) {
                    Image(
                        painter = painterResource(id = eroskiIcon),
                        contentDescription = null,
                        modifier = Modifier
                            .height(imageSize)
                    )
                }
            }
            IconButton(
                modifier = Modifier.padding(end = padding),
                onClick = { viewModel.onTouchHipercorIconSearch() }
            ) {
                Image(
                    painter = painterResource(id = hipercorIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .height(imageSize)
                )
            }
            IconButton(onClick = { viewModel.onTouchMercadonaIconSearch() }) {
                Image(
                    painter = painterResource(id = mercadonaIcon),
                    contentDescription = null,
                    modifier = Modifier
                        .height(imageSize)
                )
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        DropDownMenu(viewModel)
    }
}


@Composable
fun DropDownMenu(viewModel: SearchScreemViewModel) {
    val expanded: Boolean by viewModel.expanded.observeAsState(initial = false)
    val suggestions: List<String> by viewModel.suggestions.observeAsState(
        initial = listOf(
            "Price",
            "Price per unit"
        )
    )
    val selectedSortType: String by viewModel.selectedSortType.observeAsState(initial = "Price")
    val textfieldSize: Size by viewModel.textfieldSize.observeAsState(initial = Size.Zero)

    val uiColor = if (isSystemInDarkTheme()) DarkGreen else Green


    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(20.dp)) {
        OutlinedTextField(
            value = selectedSortType,
            onValueChange = { /* No-op */ },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    viewModel.changeTextfieldSize(coordinates.size.toSize())
                },
            label = { Text("Order by:") },
            trailingIcon = {
                Icon(icon, contentDescription = "contentDescription",
                    Modifier.clickable { viewModel.onExpandedEvent() })
            },
            colors = OutlinedTextFieldDefaults.colors(uiColor)
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { viewModel.dropExpanded() },
            modifier = Modifier
                .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    viewModel.selectText(label)
                    viewModel.dropExpanded()
                }) {
                    Text(text = label)
                }
            }
        }
    }
}