package com.tfg.supercomparator.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tfg.supercomparator.domain.modules.model.ahorramas.mapToProductList
import com.tfg.supercomparator.domain.modules.model.alcampo.product.mapToProductList
import com.tfg.supercomparator.domain.modules.model.carrefour.product.mapToProductList
import com.tfg.supercomparator.domain.modules.model.dia.product.mapToProductList
import com.tfg.supercomparator.domain.modules.model.eroski.mapToProductList
import com.tfg.supercomparator.domain.modules.model.product.Product
import com.tfg.supercomparator.service.AhorramasServices
import com.tfg.supercomparator.service.AlcampoService
import com.tfg.supercomparator.service.CarrefourSercice
import com.tfg.supercomparator.service.DiaService
import com.tfg.supercomparator.service.EroskiService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchScreemViewModel : ViewModel() {

    private val _ahorramasIconSearch = MutableLiveData<Boolean>().apply { value = false }
    val ahorramasIconSearch: LiveData<Boolean> = _ahorramasIconSearch

    private val _alcampoIconSearch = MutableLiveData<Boolean>().apply { value = false }
    val alcampoIconSearch: LiveData<Boolean> = _alcampoIconSearch

    private val _carrefourIconSearch = MutableLiveData<Boolean>().apply { value = false }
    val carrefourIconSearch: LiveData<Boolean> = _carrefourIconSearch

    private val _diaIconSearch = MutableLiveData<Boolean>().apply { value = false }
    val diaIconSearch: LiveData<Boolean> = _diaIconSearch

    private val _eroskiIconSearch = MutableLiveData<Boolean>().apply { value = false }
    val eroskiIconSearch: LiveData<Boolean> = _eroskiIconSearch

    private val _hipercorIconSearch = MutableLiveData<Boolean>().apply { value = false }
    val hipercorIconSearch: LiveData<Boolean> = _hipercorIconSearch

    private val _mercadornaIconSearch = MutableLiveData<Boolean>().apply { value = false }
    val mercadonaSearch: LiveData<Boolean> = _mercadornaIconSearch

    private val _seachBarActiveMode = MutableLiveData<Boolean>().apply { value = false }
    val seachBarActiveMode: LiveData<Boolean> = _seachBarActiveMode

    private val _query = MutableLiveData<String>().apply { value = "" }
    val query: LiveData<String> = _query

    private val _searchProuducts = MutableLiveData<MutableList<Product>>()
    val searchProuducts: LiveData<MutableList<Product>> = _searchProuducts

    private var products = mutableListOf<Product>()

    fun executeQuery() {
        _searchProuducts.postValue(mutableListOf())
        products = mutableListOf()
        val totalQueries = mutableListOf<Deferred<Unit>>()

        if (ahorramasIconSearch.value == true)
            query.value?.let { searchAhorramas(it) }?.let { totalQueries.add(it) }

        if (alcampoIconSearch.value == true)
            query.value?.let { searchAlcampo(it) }?.let { totalQueries.add(it) }

        if (carrefourIconSearch.value == true)
            query.value?.let { searchCarrefour(it) }?.let { totalQueries.add(it) }

        if (diaIconSearch.value == true)
            query.value?.let { searchDia(it) }?.let { totalQueries.add(it) }

        if (eroskiIconSearch.value == true)
            query.value?.let { searcheroski(it) }?.let { totalQueries.add(it) }

        if (hipercorIconSearch.value == true)
            Log.d("Search", "${hipercorIconSearch.value}")

        if (mercadonaSearch.value == true)
            Log.d("Search", "${mercadonaSearch.value}")

        viewModelScope.launch {
            totalQueries.awaitAll() // Espera a que todas las consultas terminen
            _searchProuducts.postValue(products)
            Log.d("Search", "Todas las consultas han finalizado")
        }
    }

    private fun searchAhorramas(query: String): Deferred<Unit> = viewModelScope.async {
        val quote =
            withContext(Dispatchers.IO) { AhorramasServices().findProducts(query) }
        Log.e("Response", quote.toString())
        products.addAll(quote.mapToProductList())
        Log.e("Response", _searchProuducts.value?.size.toString())
    }

    private fun searchAlcampo(query: String): Deferred<Unit> = viewModelScope.async {
        val quote =
            withContext(Dispatchers.IO) {
                AlcampoService().findProducts(query)
            }
        Log.e("Response", quote.toString())
        products.addAll(quote.mapToProductList())
        Log.e("Response", _searchProuducts.value?.size.toString())
    }


    private fun searchCarrefour(query: String): Deferred<Unit> = viewModelScope.async {
        val quote =
            withContext(Dispatchers.IO) {
                CarrefourSercice().findProducts(query)
            }
        Log.e("Response", quote.toString())
        products.addAll(quote.mapToProductList())
        Log.e("Response", _searchProuducts.value?.size.toString())
    }


    private fun searchDia(query: String): Deferred<Unit> = viewModelScope.async {
        val quote =
            withContext(Dispatchers.IO) {
                DiaService().findProducts(query)
            }
        products.addAll(quote.mapToProductList())
        Log.e("Quote Dia", quote.toString())
    }


    private fun searcheroski(query: String): Deferred<Unit> = viewModelScope.async {
        val quote =
            withContext(Dispatchers.IO) {
                EroskiService().findProducts(query)
            }
        products.addAll(quote.mapToProductList())
        Log.e("Response", quote.toString())
    }


    fun onQueryChanged(query: String) {
        _query.value = query
    }

    fun queryClear() {
        _query.value = ""
    }

    fun unSeachBarActiveMode() {
        _seachBarActiveMode.value = false
    }

    fun onChangeSeachBarActiveMode(value: Boolean) {
        _seachBarActiveMode.value = value
    }

    fun onTouchAhorramasIconSearch() {
        _ahorramasIconSearch.value = _ahorramasIconSearch.value?.not()
    }

    fun onTouchAlcampoIconSearch() {
        _alcampoIconSearch.value = _alcampoIconSearch.value?.not()
    }

    fun onTouchCarrefourIconSearch() {
        _carrefourIconSearch.value = _carrefourIconSearch.value?.not()
    }

    fun onTouchDiaIconSearch() {
        _diaIconSearch.value = _diaIconSearch.value?.not()
    }

    fun onTouchEroskiIconSearch() {
        _eroskiIconSearch.value = _eroskiIconSearch.value?.not()
    }

    fun onTouchHipercorIconSearch() {
        _hipercorIconSearch.value = _hipercorIconSearch.value?.not()
    }

    fun onTouchMercadonaIconSearch() {
        _mercadornaIconSearch.value = _mercadornaIconSearch.value?.not()
    }
}
