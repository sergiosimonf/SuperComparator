package com.tfg.supercomparator.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.tfg.supercomparator.domain.modules.analytics.AnalyticsManager
import com.tfg.supercomparator.domain.modules.auth.AuthManager
import com.tfg.supercomparator.domain.modules.auth.AuthRes
import com.tfg.supercomparator.ui.navigation.AppScreens


class ForgotPasswordViewModel : ViewModel() {

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email
    suspend fun forgotPassword(
        analytics: AnalyticsManager,
        auth: AuthManager,
        context: Context,
        navController: NavController,
    ) {
        if (!email.value.isNullOrEmpty()) {
            when (val res = auth.resetPassword(email.value!!)) {
                is AuthRes.Success -> {
                    analytics.logButtonClicked(buttonName = "Reset password $email")
                    Toast.makeText(context, "Correo enviado", Toast.LENGTH_SHORT).show()
                    navController.navigate(AppScreens.LOGIN.ruta)
                }

                is AuthRes.Error -> {
                    analytics.logError(error = "Reset password error $email : ${res.errorMessage}")
                    Toast.makeText(context, "Error al enviar el correo", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(context, "Introduce un correo", Toast.LENGTH_SHORT).show()
        }
    }

    fun onEmailChanged(email: String) {
        _email.value = email
    }
}