package com.tfg.supercomparator.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

class RegisterViewModel(private val navController: NavHostController) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _repeatPassword = MutableLiveData<String>()
    val repeatPassword: LiveData<String> = _repeatPassword

    private val _passwordMode = MutableLiveData<Boolean>().apply { value = true }
    val passwordMode = _passwordMode

    private val _passwordRecoverMode = MutableLiveData<Boolean>().apply { value = false }
    val passwordRecoverMode: LiveData<Boolean> = _passwordRecoverMode

    fun onRegisterChanged(email: String, password: String, repeatPassword: String) {
        _email.value = email
        _password.value = password
        _repeatPassword.value = repeatPassword
    }

    fun changePasswordMode() {
        _passwordMode.value = _passwordMode.value?.not()
    }

    fun changePasswordRecoverMode() {
        _passwordRecoverMode.value = _passwordRecoverMode.value?.not()
    }
}