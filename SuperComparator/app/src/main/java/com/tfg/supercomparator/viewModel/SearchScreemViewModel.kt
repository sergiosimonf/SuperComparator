package com.tfg.supercomparator.viewModel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tfg.supercomparator.domain.modules.data.AppDatabase
import com.tfg.supercomparator.domain.modules.model.ahorramas.mapToProductList
import com.tfg.supercomparator.domain.modules.model.alcampo.product.mapToProductList
import com.tfg.supercomparator.domain.modules.model.carrefour.product.mapToProductList
import com.tfg.supercomparator.domain.modules.model.dia.product.mapToProductList
import com.tfg.supercomparator.domain.modules.model.eroski.mapToProductList
import com.tfg.supercomparator.domain.modules.model.hipercor.mapToProductList
import com.tfg.supercomparator.domain.modules.model.mercadona.mapToProductList
import com.tfg.supercomparator.domain.modules.model.product.Product
import com.tfg.supercomparator.domain.modules.network.QuoteRepository
import com.tfg.supercomparator.service.AhorramasService
import com.tfg.supercomparator.service.AlcampoService
import com.tfg.supercomparator.service.CarrefourSercice
import com.tfg.supercomparator.service.DiaService
import com.tfg.supercomparator.service.EroskiService
import com.tfg.supercomparator.service.HipercorService
import com.tfg.supercomparator.service.MercadonaService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SearchScreemViewModel : ViewModel() {

    private val _queryExecuted = MutableLiveData<Boolean>().apply { value = false }
    val queryExecuted: LiveData<Boolean> = _queryExecuted

    private val _intennetCoexion = MutableLiveData<Boolean>().apply { value = false }
    val internetCoexion: LiveData<Boolean> = _intennetCoexion

    private val _isNotificado = MutableLiveData<Boolean>().apply { value = false }
    val isNotificado: LiveData<Boolean> = _isNotificado

    private val _expanded = MutableLiveData<Boolean>().apply { value = false }
    val expanded: LiveData<Boolean> = _expanded

    private val _suggestions =
        MutableLiveData<List<String>>().apply { value = listOf("Price", "Price per unit") }
    val suggestions: LiveData<List<String>> = _suggestions

    private val _selectedSortType = MutableLiveData<String>().apply { value = "Price" }
    val selectedSortType: LiveData<String> = _selectedSortType

    private val _textfieldSize =
        MutableLiveData<androidx.compose.ui.geometry.Size>().apply { androidx.compose.ui.geometry.Size.Zero }
    val textfieldSize: LiveData<androidx.compose.ui.geometry.Size> = _textfieldSize

    private val _productSelected = MutableLiveData<Product>()
    val productSelected: LiveData<Product> = _productSelected

    private val _ahorramasIconSearch = MutableLiveData<Boolean>().apply { value = true }
    val ahorramasIconSearch: LiveData<Boolean> = _ahorramasIconSearch

    private val _alcampoIconSearch = MutableLiveData<Boolean>().apply { value = true }
    val alcampoIconSearch: LiveData<Boolean> = _alcampoIconSearch

    private val _carrefourIconSearch = MutableLiveData<Boolean>().apply { value = true }
    val carrefourIconSearch: LiveData<Boolean> = _carrefourIconSearch

    private val _diaIconSearch = MutableLiveData<Boolean>().apply { value = true }
    val diaIconSearch: LiveData<Boolean> = _diaIconSearch

    private val _eroskiIconSearch = MutableLiveData<Boolean>().apply { value = true }
    val eroskiIconSearch: LiveData<Boolean> = _eroskiIconSearch

    private val _hipercorIconSearch =
        MutableLiveData<Boolean>().apply { value = QuoteRepository.apiConexion }
    val hipercorIconSearch: LiveData<Boolean> = _hipercorIconSearch

    private val _mercadornaIconSearch =
        MutableLiveData<Boolean>().apply { value = QuoteRepository.apiConexion }
    val mercadonaSearch: LiveData<Boolean> = _mercadornaIconSearch

    private val _seachBarActiveMode = MutableLiveData<Boolean>().apply { value = false }
    val seachBarActiveMode: LiveData<Boolean> = _seachBarActiveMode

    private val _query = MutableLiveData<String>().apply { value = "" }
    val query: LiveData<String> = _query

    private val _searchProuducts = MutableLiveData<MutableList<Product>>()
    val searchProuducts: LiveData<MutableList<Product>> = _searchProuducts

    private var products = mutableListOf<Product>()

    fun executeQuery(database: AppDatabase) {
        _searchProuducts.postValue(mutableListOf())
        products = mutableListOf()

        _queryExecuted.value = true
        _expanded.value = false

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
            query.value?.let { searchEroski(it) }?.let { totalQueries.add(it) }

        if (hipercorIconSearch.value == true)
            query.value?.let { searchHipercor(it) }?.let { totalQueries.add(it) }

        if (mercadonaSearch.value == true)
            query.value?.let { searchMercadona(it) }?.let { totalQueries.add(it) }

        viewModelScope.launch(Dispatchers.IO) {
            totalQueries.awaitAll() // Espera a que todas las consultas terminen
            sortProducts()
            checkFavoriteProuducts(database)
            _searchProuducts.postValue(products)
            Log.d("Search", "Todas las consultas han finalizado")
        }
    }

    private fun checkFavoriteProuducts(database: AppDatabase) {
        val productsDatabase = database.productDAO.getFavProducts()
        val productsNameDatabase = productsDatabase.associateBy { it.name }

        products.forEach { product ->
            product.isFavorite = productsNameDatabase[product.name]?.isFavorite ?: false
        }
    }

    private fun sortProducts() {
        Log.d("SELECTED SORT TYPE", _selectedSortType.value!!)
        when (_selectedSortType.value) {
            "Price" -> {
                Log.d("SORTING BY", _selectedSortType.value!!)
                products.sortBy { it.price }
            }

            "Price per unit" -> {
                Log.d("SORTING BY", _selectedSortType.value!!)
                products.sortBy { it.pricePerUnit }
            }
        }
    }

    private fun searchAhorramas(query: String): Deferred<Unit> =
        viewModelScope.async(Dispatchers.IO) {
            try {
                val quote =
                    withContext(Dispatchers.IO) { AhorramasService().findProducts(query) }
                Log.e("Response", quote.toString())
                products.addAll(quote.mapToProductList())
                Log.e("Ahorrams", quote.toString())
                if (QuoteRepository.apiConexion) {
                    withContext(Dispatchers.IO) {
                        val response = AhorramasService().saveProductHistory(quote)
                        Log.d("Ahorramas response", response.errorBody().toString())
                    }
                }
            } catch (ex: Exception) {
                Log.d("Ahorramas Error", ex.toString())
            }
        }

    private fun searchAlcampo(query: String): Deferred<Unit> =
        viewModelScope.async(Dispatchers.IO) {
            try {
                val quote =
                    withContext(Dispatchers.IO) {
                        AlcampoService().findProducts(query)
                    }
                products.addAll(quote.mapToProductList())
                Log.e("Alcampo", quote.toString())
                if (QuoteRepository.apiConexion) {
                    withContext(Dispatchers.IO) {
                        AlcampoService().saveProductHistory(quote)
                    }
                }
            } catch (ex: Exception) {
                Log.d("Alcampo Error", ex.toString())
            }
        }


    private fun searchCarrefour(query: String): Deferred<Unit> =
        viewModelScope.async(Dispatchers.IO) {
            try {
                val quote =
                    withContext(Dispatchers.IO) {
                        CarrefourSercice().findProducts(query)
                    }
                Log.e("Response", quote.toString())
                products.addAll(quote.mapToProductList())
                Log.e("Carrefour", quote.toString())
                if (QuoteRepository.apiConexion) {
                    withContext(Dispatchers.IO) {
                        CarrefourSercice().saveProductHistory(quote)
                    }
                }
            } catch (ex: Exception) {
                Log.d("Carrefour Error", ex.toString())
            }
        }


    private fun searchDia(query: String): Deferred<Unit> = viewModelScope.async(Dispatchers.IO) {
        try {
            val quote =
                withContext(Dispatchers.IO) {
                    DiaService().findProducts(query)
                }
            products.addAll(quote.mapToProductList())
            Log.e("Dia", quote.toString())
            if (QuoteRepository.apiConexion) {
                withContext(Dispatchers.IO) {
                    DiaService().saveProductHistory(quote)
                }
            }
        } catch (ex: Exception) {
            Log.d("Dia Error", ex.toString())
        }
    }


    private fun searchEroski(query: String): Deferred<Unit> = viewModelScope.async(Dispatchers.IO) {
        try {
            val quote =
                withContext(Dispatchers.IO) {
                    EroskiService().findProducts(query)
                }
            products.addAll(quote.mapToProductList())
            Log.e("Eroski", quote.toString())
            if (QuoteRepository.apiConexion) {
                withContext(Dispatchers.IO) {
                    EroskiService().saveProductHistory(quote)
                }
            }
        } catch (ex: Exception) {
            Log.d("Eroski Error", ex.toString())
        }
    }

    private fun searchHipercor(query: String): Deferred<Unit> =
        viewModelScope.async(Dispatchers.IO) {
            Log.e("Hipercor", "Iniciando busqueda")
            try {
                val quote =
                    withContext(Dispatchers.IO) {
                        HipercorService().findProducts(query)
                    }
                products.addAll(quote.mapToProductList())
                Log.e("Hipercor", quote.toString())
                withContext(Dispatchers.IO) {
                    HipercorService().saveProductHistory(quote)
                }
            } catch (ex: Exception) {
                Log.d("Hipercor Error", ex.toString())
            }
        }

    private fun searchMercadona(query: String): Deferred<Unit> =
        viewModelScope.async(Dispatchers.IO) {
            try {
                val quote =
                    withContext(Dispatchers.IO) {
                        MercadonaService().findProducts(query)
                    }
                products.addAll(quote.mapToProductList())
                Log.e("Mercadona", quote.toString())
                withContext(Dispatchers.IO) {
                    MercadonaService().saveProductHistory(quote)
                }
            } catch (ex: Exception) {
                Log.d("Mercadona Error", ex.toString())
            }
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
        if (QuoteRepository.apiConexion) {
            _hipercorIconSearch.value = _hipercorIconSearch.value?.not()
        }
    }

    fun onTouchMercadonaIconSearch() {
        if (QuoteRepository.apiConexion) {
            _mercadornaIconSearch.value = _mercadornaIconSearch.value?.not()
        }
    }

    fun onExpandedEvent() {
        _expanded.value = _expanded.value?.not()
    }

    fun dropExpanded() {
        _expanded.value = false
    }

    fun changeTextfieldSize(size: androidx.compose.ui.geometry.Size) {
        _textfieldSize.value = size
    }

    fun selectText(sortType: String) {
        _selectedSortType.value = sortType
    }

    fun changeNotificationStatus(notification: Boolean) {
        _isNotificado.value = notification
    }

    fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities =
            connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        _intennetCoexion.value =
            networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}
