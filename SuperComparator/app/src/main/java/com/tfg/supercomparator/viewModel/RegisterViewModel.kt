package com.tfg.supercomparator.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.tfg.supercomparator.ui.navigation.Actions

class RegisterViewModel(private val navController: NavHostController) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _repeatPassword = MutableLiveData<String>()
    val repeatPassword: LiveData<String> = _repeatPassword

    private val _passwordMode = MutableLiveData<Boolean>()
    val passwordMode = _passwordMode

    private val _passwordRecoverMode = MutableLiveData<Boolean>()
    val passwordRecoverMode: LiveData<Boolean> = _passwordRecoverMode

    fun onRegisterChanged(email: String, password: String, repeatPassword: String) {
        _email.value = email
        _password.value = password
        _repeatPassword.value = repeatPassword
    }

    fun changePasswordMode(passwordMode: Boolean) {
        _passwordMode.value = !passwordMode
    }

    fun changePasswordRecoverMode(passwordRecoverMode: Boolean) {
        _passwordRecoverMode.value = !passwordRecoverMode
    }
}