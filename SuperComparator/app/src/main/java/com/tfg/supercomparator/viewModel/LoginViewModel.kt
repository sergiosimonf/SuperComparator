package com.tfg.supercomparator.viewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.tfg.supercomparator.domain.modules.analytics.AnalyticsManager
import com.tfg.supercomparator.domain.modules.auth.AuthManager
import com.tfg.supercomparator.domain.modules.auth.AuthRes
import com.tfg.supercomparator.service.AhorramasServices
import com.tfg.supercomparator.ui.navigation.AppScreens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class LoginViewModel : ViewModel() {
    private val _email = MutableLiveData<String>().apply { value = "" }
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>().apply { value = "" }
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

    fun googleLogIn() = viewModelScope.launch {
    }

    suspend fun signInAnonymously(
        auth: AuthManager,
        analytics: AnalyticsManager,
        context: Context,
        navController: NavHostController,
    ) {
        when (val result = auth.signInAnonymously()) {
            is AuthRes.Success -> {
                analytics.logButtonClicked("Click: Continuar como invitado")
                navController.navigate(AppScreens.DASHBOARD.ruta) {
                    popUpTo(AppScreens.LOGIN.ruta) {
                        inclusive = true
                    }
                }
            }

            is AuthRes.Error -> {
                analytics.logError("Error SignIn Incognito: ${result.errorMessage}")
            }
        }
    }

    suspend fun signIn(
        auth: AuthManager,
        analytics: AnalyticsManager,
        context: Context,
        navController: NavHostController,
    ) {
        val email = _email.value
        val password = _password.value

        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            when (val result = auth.signInWithEmailAndPassword(email, password)) {
                is AuthRes.Success -> {
                    analytics.logButtonClicked("Click: Iniciar sesión correo & contraseña")
                    navController.navigate(AppScreens.DASHBOARD.ruta) {
                        popUpTo(AppScreens.LOGIN.ruta) {
                            inclusive = true
                        }
                    }
                }

                is AuthRes.Error -> {
                    analytics.logButtonClicked("Error SignIn: ${result.errorMessage}")
                    Toast.makeText(
                        context,
                        "Error SignIn: ${result.errorMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        } else {
            Toast.makeText(context, "Existen campos vacíos", Toast.LENGTH_LONG).show()
        }
    }


}