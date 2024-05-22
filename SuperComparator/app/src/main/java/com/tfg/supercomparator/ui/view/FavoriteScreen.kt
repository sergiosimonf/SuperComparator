package com.tfg.supercomparator.ui.view

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tfg.supercomparator.domain.modules.data.AppDatabase
import com.tfg.supercomparator.domain.modules.model.product.Product
import com.tfg.supercomparator.ui.theme.DarkGreen
import com.tfg.supercomparator.ui.theme.Green
import com.tfg.supercomparator.ui.view.components.ProductCard
import com.tfg.supercomparator.viewModel.FavoriteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun FavoriteScreen(
    database: AppDatabase,
    viewModel: FavoriteViewModel = FavoriteViewModel(),
) {
//    analytics.LogScreenView(screenName = AppScreens.LOGIN.ruta)
    val uiColor = if (isSystemInDarkTheme()) DarkGreen else Green


    val scope = rememberCoroutineScope()
    val refreshing by viewModel.refreshing.observeAsState(initial = false)
    val favProducts: List<Product> by viewModel.favProducts.observeAsState(initial = listOf())
    viewModel.searchFavProducts(database)

    fun refresh() = scope.launch(Dispatchers.IO) {
        viewModel.startRefreshing()
        delay(1500)
        viewModel.searchFavProducts(database)
        viewModel.finishRefresing()
    }

    val state = rememberPullRefreshState(refreshing, ::refresh)

    Box(Modifier.pullRefresh(state)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxSize()
        ) {
            if (!refreshing) {
                items(favProducts) { item ->
                    ProductCard(item, database)
                }
            }
        }

        PullRefreshIndicator(
            refreshing,
            state,
            Modifier.align(Alignment.TopCenter),
            contentColor = uiColor
        )
    }
}