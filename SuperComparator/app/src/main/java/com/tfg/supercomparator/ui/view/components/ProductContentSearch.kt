package com.tfg.supercomparator.ui.view.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tfg.supercomparator.R
import com.tfg.supercomparator.domain.modules.data.AppDatabase
import com.tfg.supercomparator.domain.modules.model.product.Product
import com.tfg.supercomparator.ui.theme.DarkGreen
import com.tfg.supercomparator.ui.theme.Green
import com.tfg.supercomparator.viewModel.SearchScreemViewModel

@Composable
fun ProductContentSearch(
    viewModel: SearchScreemViewModel = SearchScreemViewModel(),
    database: AppDatabase
) {
    val scope = rememberCoroutineScope()
    val searchItems: MutableList<Product>? by viewModel.searchProuducts.observeAsState()
    val uiColor = if (isSystemInDarkTheme()) DarkGreen else Green

    Card(
        colors = CardDefaults.cardColors(uiColor),
        shape = RoundedCornerShape(35.dp, 35.dp, 0.dp, 0.dp)
    ) {
        Text(
            text = "Products",
            color = Color.White,
            style = TextStyle(
                fontFamily = FontFamily(Font((R.font.roboto_bold))),
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
            ),
            modifier = Modifier
                .padding(top = 12.dp)
                .fillMaxWidth()
        )
        Spacer(
            modifier = Modifier.height(16.dp)
        )
        if (searchItems == null) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 20.dp, start = 30.dp),
                text = "Do a search to start comparing...",
                color = Color.White
            )
        } else {
            if (searchItems!!.isEmpty()) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp, bottom = 20.dp),
                        text = "No products found yet",
                        textAlign = TextAlign.Center,
                        fontSize = 22.sp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CircularProgressIndicator(color = Color.White)
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.Start
                ) {
                    items(searchItems!!) { item ->
                        Column(
                            modifier = Modifier.padding(8.dp)
                        ) {
                            ProductCard(item, database)
                        }
                    }
                }
            }
        }
    }
}