package com.tfg.supercomparator.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.tfg.supercomparator.domain.modules.model.product.Product
import com.tfg.supercomparator.ui.navigation.NavArgs

class ProductViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val product: Product = savedStateHandle[NavArgs.Product.key]!!

    var state by mutableStateOf<Product?>(null)
        private set

    init {
        state = product
    }
}