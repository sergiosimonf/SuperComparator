package com.tfg.supercomparator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tfg.supercomparator.ui.view.ImageScren
import com.tfg.supercomparator.ui.view.LoginScreen
import com.tfg.supercomparator.ui.view.SplashScreen
import com.tfg.supercomparator.ui.view.test.MovableContentScreen
import com.tfg.supercomparator.ui.view.RegisterScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.SPLASHSCREEN.ruta) {
        composable(AppScreens.LOGIN.ruta) {
            LoginScreen(navController)
        }
        composable(AppScreens.REGISTER.ruta) {
            RegisterScreen(navController)
        }
        composable(AppScreens.IMAGE.ruta) {
            ImageScren()
        }
        composable(AppScreens.MOVABLE.ruta) {
            MovableContentScreen()
        }
        composable(AppScreens.SPLASHSCREEN.ruta) {
            SplashScreen(navController)
        }
    }
}