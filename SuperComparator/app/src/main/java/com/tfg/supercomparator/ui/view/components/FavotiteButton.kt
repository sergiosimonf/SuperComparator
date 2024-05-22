package com.tfg.supercomparator.ui.view.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    color: Color = Color(0xFFD50000),
    onCheckedChange: (Boolean) -> Unit,
    isFavorite: Boolean
) {
    var isFavorite by remember { mutableStateOf(isFavorite) }

    IconToggleButton(
        checked = isFavorite,
        onCheckedChange = { isChecked ->
            isFavorite = isChecked
            onCheckedChange(isChecked)
        },
    ) {
        Icon(
            tint = color,
            modifier = modifier.graphicsLayer {
                scaleX = 1.3f
                scaleY = 1.3f
            },
            imageVector = if (isFavorite) {
                Icons.Filled.Favorite
            } else {
                Icons.Default.FavoriteBorder
            },
            contentDescription = null
        )
    }

}