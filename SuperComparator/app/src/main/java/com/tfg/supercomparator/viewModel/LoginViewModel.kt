package com.tfg.supercomparator.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tfg.supercomparator.service.AhorramasServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginViewModel : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password
    private val _passwordMode = MutableLiveData<Boolean>().apply { value = true }
    val passwordMode: LiveData<Boolean> = _passwordMode

    fun onLoginChanged(email: String, password: String) {
        _email.value = email
        _password.value = password
    }

    fun changePasswordMode() {
        _passwordMode.value = _passwordMode.value?.not()
    }

    private val ahorramas = AhorramasServices()

    fun getQuote() = viewModelScope.launch {
        val quote =
            withContext(Dispatchers.IO) { ahorramas.findProducts("coca") } // Operación bloqueante
        Log.e("Response", quote.toString())
    }

}