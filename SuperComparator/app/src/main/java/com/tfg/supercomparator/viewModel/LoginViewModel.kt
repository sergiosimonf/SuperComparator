package com.tfg.supercomparator.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tfg.supercomparator.domain.modules.network.QuoteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject


class LoginViewModel @Inject constructor(): ViewModel() {

    private val diaQuoteService = QuoteRepository()

    fun getQuote() = viewModelScope.launch {
        val quote = diaQuoteService.getDiaProduct("coca")
        Log.e("Response", quote.toString())
    }

}