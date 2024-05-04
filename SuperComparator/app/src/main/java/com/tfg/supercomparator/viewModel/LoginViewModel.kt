package com.tfg.supercomparator.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tfg.supercomparator.domain.modules.network.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginViewModel: ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password
    private val _passwordMode = MutableLiveData<Boolean>()
    val passwordMode: LiveData<Boolean> = _passwordMode

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
    }
    fun changePasswordMode(passwordMode: Boolean) {
        _passwordMode.value = !passwordMode
    }

    private val diaQuoteService = QuoteRepository()

    fun getQuote() = viewModelScope.launch {
        val quote = withContext(Dispatchers.IO) { diaQuoteService.getDiaProduct("coca") } // Operación bloqueante
        Log.e("Response", quote.toString())
    }

}