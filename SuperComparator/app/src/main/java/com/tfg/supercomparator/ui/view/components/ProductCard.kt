package com.tfg.supercomparator.ui.view.components

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tfg.supercomparator.R
import com.tfg.supercomparator.domain.modules.model.product.Product
import com.tfg.supercomparator.ui.theme.DarkGreen
import com.tfg.supercomparator.ui.theme.Green
import com.tfg.supercomparator.viewModel.SearchScreemViewModel

@Preview
@Composable
fun ProductContentSearch(viewModel: SearchScreemViewModel = SearchScreemViewModel()) {

    val searchItems: MutableList<Product>? by viewModel.searchProuducts.observeAsState()
    val history: String by viewModel.query.observeAsState(initial = "")

    val uiColor = if (isSystemInDarkTheme()) DarkGreen else Green

    Card(
        colors = CardDefaults.cardColors(uiColor),
        shape = RoundedCornerShape(35.dp, 35.dp, 0.dp, 0.dp)
    ) {
        Text(
            text = "Productos",
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
                    .padding(top = 10.dp, bottom = 20.dp),
                text = "No products found yet do a search to start comparing...",
                textAlign = TextAlign.Center,
                fontSize = 22.sp,
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
                            ProductCard(item)
                        }
                    }
                }

            }
        }
    }
}

@Composable
private fun ProductCard(item: Product) {
    val uiColor = if (isSystemInDarkTheme()) DarkGreen else Green
    Card(
        onClick = {},
        modifier = Modifier
            .fillMaxHeight(),
        shape = RoundedCornerShape(40.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = item.imageUrl,
                    placeholder = painterResource(id = R.drawable.image_placeholder),
                    error = painterResource(R.drawable.image_placeholder),
                    contentDescription = null,
                    modifier = Modifier
                        .aspectRatio(1280f / 847f)
                        .fillMaxWidth(),
                )
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    FavoriteButton(
                        onCheckedChange = { /*TODO*/ }
                    )
                    if (item.hasOfertaExtra) {
                        Card(
                            colors = CardDefaults.cardColors(Color.Red),
                        ) {
                            item.ofertExtra?.let {
                                Text(
                                    text = it,
                                    color = Color.White,
                                    style = MaterialTheme.typography.bodySmall,
                                    fontSize = 9.sp,
                                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                                )
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Image(
                        painter = painterResource(id = item.tiendaIcon),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                    )
                }
            }
            Text(
                text = item.name,
                color = Color.Gray,
                style = MaterialTheme.typography.labelLarge,
//                fontFamily = R.font.roboto_bold,
                modifier = Modifier.padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.size(4.dp))
            if (item.hasOferta) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "${item.priceNoOfert}",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.LineThrough,
                        modifier = Modifier.padding()
                    )
                    OfferBadge(item.priceNoOfert, item.priceOfert)
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.height(40.dp)
            ) {
                Text(
                    text = if (item.hasOferta) "${item.priceOfert}" else "${item.price}",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(end = 6.dp)
                )
                Text(
                    text = item.pricePerUnitText,
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@Composable
private fun OfferBadge(priceNoOfert: Double?, priceOfert: Double?) {
    Card(
        colors = CardDefaults.cardColors(Color.Red),
        modifier = Modifier.padding(start = 8.dp)
    ) {
        if (priceOfert == null) {
            Log.e(TAG, "Wtf ")
        }
        Text(
            text = "${calcularPorcentajeDeOferta(priceNoOfert!!, priceOfert!!)}% dto",
            color = Color.White,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 9.sp
        )
    }
}


fun calcularPorcentajeDeOferta(precioOriginal: Double, precioOferta: Double): Int {
    val diferencia = precioOriginal - precioOferta

    return (diferencia / precioOriginal * 100).toInt()
}