package com.tfg.supercomparator.ui.navigation

import androidx.navigation.NavHostController
import com.tfg.supercomparator.ui.navigation.Destination.LOGIN
import com.tfg.supercomparator.ui.navigation.Destination.REGISTER

object Destination {
    const val LOGIN = "Login"
    const val REGISTER = "Register"
}

// No funciona
class Actions(navController: NavHostController) {

    val goRegisterScreen: () -> Unit = {
        navController.navigate(REGISTER)
    }

    val goLoginScreen: () -> Unit = {
        navController.navigate(LOGIN)
    }
}

sealed class AppScreens(val ruta: String) {
    data object LOGIN: AppScreens(ruta = "login")
    data object REGISTER: AppScreens("register")
    data object DASHBOARD: AppScreens("dashboard")
    data object IMAGE: AppScreens("image")
    data object MOVABLE: AppScreens("movable")
    data object SPLASHSCREEN: AppScreens("splashscreen")
}