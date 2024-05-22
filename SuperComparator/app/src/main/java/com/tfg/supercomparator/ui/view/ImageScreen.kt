package com.tfg.supercomparator.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.imageLoader

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageScren(
    navController: NavHostController = rememberNavController(),
) {
    val context = LocalContext.current
    val imageUrl = "https://supermercado.eroski.es/images/22445217.jpg"
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1280f / 847f)
            )
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(1280f / 847f),
                loading = {
                    CircularProgressIndicator()
                }

            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            context.imageLoader.diskCache?.clear()
//            context.imageLoader.diskCache?.remove(imageUrl)
            context.imageLoader.memoryCache?.clear()
//            context.imageLoader.memoryCache?.remove(MemoryCache.Key(imageUrl))
        }) {
            Text("Clear cache")
        }
    }
}