package com.tfg.supercomparator.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.google.firebase.analytics.FirebaseAnalytics
import com.tfg.supercomparator.domain.modules.analytics.AnalyticsManager
import com.tfg.supercomparator.domain.modules.auth.AuthManager
import com.tfg.supercomparator.domain.modules.auth.AuthRes

class RegisterViewModel(private val navController: NavHostController) : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _repeatPassword = MutableLiveData<String>()
    val repeatPassword: LiveData<String> = _repeatPassword

    private val _passwordMode = MutableLiveData<Boolean>().apply { value = true }
    val passwordMode = _passwordMode

    fun onRegisterChanged(email: String, password: String, repeatPassword: String) {
        _email.value = email
        _password.value = password
        _repeatPassword.value = repeatPassword
    }

    fun changePasswordMode() {
        _passwordMode.value = _passwordMode.value?.not()
    }

    suspend fun signUp(
        auth: AuthManager,
        analytics: AnalyticsManager,
        navController: NavHostController,
        context: Context,
    ) {
        val email = _email.value
        val password = _password.value
        val repeatPassword = _repeatPassword.value

        if (!email.isNullOrEmpty() && !password.isNullOrEmpty() && !repeatPassword.isNullOrEmpty()) {
            if (password == repeatPassword) {
                if (password.length >= 6) {
                    when (val result = auth.createUserWithEmailAndPassword(email, password)) {
                        is AuthRes.Success -> {
                            analytics.logButtonClicked(FirebaseAnalytics.Event.SIGN_UP)
                            Toast.makeText(context, "Registro exitoso", Toast.LENGTH_LONG).show()
                            navController.popBackStack()
                        }

                        is AuthRes.Error -> {
                            analytics.logButtonClicked("Error SignUp ${result.errorMessage}")
                            Toast.makeText(
                                context,
                                "Error SignUp ${result.errorMessage}",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                } else {
                    Toast.makeText(context, "Tamaño inválido de la contraseña", Toast.LENGTH_LONG)
                        .show()
                }
            } else {
                Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(context, "Existen campos vacíos", Toast.LENGTH_LONG).show()
        }
    }

}