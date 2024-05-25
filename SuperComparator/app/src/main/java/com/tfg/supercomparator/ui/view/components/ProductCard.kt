package com.tfg.supercomparator.ui.view.components

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.tfg.supercomparator.R
import com.tfg.supercomparator.domain.modules.data.AppDatabase
import com.tfg.supercomparator.domain.modules.model.product.Product
import com.tfg.supercomparator.ui.navigation.AppScreens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ProductCard(
    item: Product,
    database: AppDatabase,
    navController: NavController,
) {
    val scope = rememberCoroutineScope()
    val isFavorite by remember { mutableStateOf(item.isFavorite) }

    Card(
        onClick = {
            navController.navigate(AppScreens.PRODUCT.createRoute(item))
        },
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
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween,
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        if (item.hasOfertaExtra) {
                            Card(
                                modifier = Modifier.padding(top = 19.dp),
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
                        FavoriteButton(
                            onCheckedChange = {
                                item.isFavorite = it
                                scope.launch(Dispatchers.IO) {
                                    database.productDAO.upsertProduct(item)
                                }
                            },
                            isFavorite = item.isFavorite
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Image(
                        painter = painterResource(id = item.tiendaIcon),
                        contentDescription = null,
                        modifier = Modifier.size(50.dp)
                    )
                }
            }
            Text(
                text = item.name,
                color = Color.Gray,
                style = MaterialTheme.typography.labelLarge,
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
                    text = if (item.hasOferta) "${item.priceOfert}€" else "${item.price}€",
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