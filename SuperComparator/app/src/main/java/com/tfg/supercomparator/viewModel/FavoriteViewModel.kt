package com.tfg.supercomparator.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tfg.supercomparator.domain.modules.data.AppDatabase
import com.tfg.supercomparator.domain.modules.model.product.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel: ViewModel() {
    private val _refreshing = MutableLiveData<Boolean>().apply { value = false }
    val refreshing: LiveData<Boolean> = _refreshing

    private val _favProducts = MutableLiveData<List<Product>>()
    val favProducts = _favProducts

    fun startRefreshing() {
        _refreshing.postValue(true)
    }

    fun finishRefresing() {
        _refreshing.postValue(false)
    }

    fun searchFavProducts(database: AppDatabase) {
        viewModelScope.launch(Dispatchers.IO) {
            _favProducts.postValue(database.productDAO.getFavProducts())
        }
    }
}