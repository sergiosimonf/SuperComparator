package com.tfg.supercomparator.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tfg.supercomparator.R
import com.tfg.supercomparator.domain.modules.model.product.Product
import com.tfg.supercomparator.domain.modules.model.product.ProductHistory.Companion.mapToLocalDateString
import com.tfg.supercomparator.domain.modules.network.QuoteRepository
import com.tfg.supercomparator.service.AhorramasService
import com.tfg.supercomparator.service.AlcampoService
import com.tfg.supercomparator.service.CarrefourSercice
import com.tfg.supercomparator.service.DiaService
import com.tfg.supercomparator.service.EroskiService
import com.tfg.supercomparator.service.HipercorService
import com.tfg.supercomparator.service.MercadonaService
import com.tfg.supercomparator.ui.navigation.NavArgs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.ConnectException
import java.time.LocalDate

class ProductViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {
    private val _data = MutableLiveData<Map<LocalDate, Float>>().apply { emptyMap<LocalDate, Float>() }
    val data: LiveData<Map<LocalDate, Float>> = _data

    private val product: Product = savedStateHandle[NavArgs.Product.key]!!

    var state by mutableStateOf<Product?>(null)
        private set

    init {
        state = product
        viewModelScope.launch(Dispatchers.IO) {
            if (QuoteRepository.apiConexion) {
                try {
                    when (product.tiendaIcon) {
                        R.drawable.ahorramas -> _data.postValue(
                            AhorramasService().getProductHistory(
                                product.name
                            ).mapToLocalDateString()
                        )

                        R.drawable.alcampo -> _data.postValue(
                            AlcampoService().getProductHistory(
                                product.name
                            ).mapToLocalDateString()
                        )

                        R.drawable.carrefour -> _data.postValue(
                            CarrefourSercice().getProductHistory(
                                product.name
                            ).mapToLocalDateString()
                        )

                        R.drawable.dia -> _data.postValue(
                            DiaService().getProductHistory(product.name).mapToLocalDateString()
                        )

                        R.drawable.eroski -> _data.postValue(
                            EroskiService().getProductHistory(
                                product.name
                            ).mapToLocalDateString()
                        )

                        R.drawable.hipercor -> _data.postValue(
                            HipercorService().getProductHistory(
                                product.name
                            ).mapToLocalDateString()
                        )

                        R.drawable.mercadona -> _data.postValue(
                            MercadonaService().getProductHistory(
                                product.name
                            ).mapToLocalDateString()
                        )
                    }
                } catch (e: ConnectException) {
                    // Handle ConnectException specifically
                } catch (e: Exception) {
                    // Handle any other exceptions
                }
            }
        }
    }
}