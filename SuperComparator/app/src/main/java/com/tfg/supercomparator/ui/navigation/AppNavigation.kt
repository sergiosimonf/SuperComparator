package com.tfg.supercomparator.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tfg.supercomparator.domain.modules.analytics.AnalyticsManager
import com.tfg.supercomparator.domain.modules.auth.AuthManager
import com.tfg.supercomparator.domain.modules.data.DatabaseProvider
import com.tfg.supercomparator.ui.view.DashboardScreen
import com.tfg.supercomparator.ui.view.ForgotPasswordScreen
import com.tfg.supercomparator.ui.view.ImageScren
import com.tfg.supercomparator.ui.view.LoginScreen
import com.tfg.supercomparator.ui.view.RegisterScreen
import com.tfg.supercomparator.ui.view.SplashScreen
import com.tfg.supercomparator.ui.view.test.MovableContentScreen

@Composable
fun AppNavigation(context: Context, navController: NavHostController = rememberNavController()) {
    val database = DatabaseProvider.getAppDatabase(context)
    val analytics: AnalyticsManager = AnalyticsManager(context)
    val authManager: AuthManager = AuthManager(context)

    NavHost(
        navController = navController,
        startDestination = AppScreens.SPLASHSCREEN.ruta
    ) {
        composable(AppScreens.LOGIN.ruta) {
            LoginScreen(
                auth = authManager,
                analytics = analytics,
                navController = navController
            )
        }
        composable(AppScreens.REGISTER.ruta) {
            RegisterScreen(
                auth = authManager,
                analytics = analytics,
                navController = navController
            )
        }
        composable(AppScreens.SPLASHSCREEN.ruta) {
            SplashScreen(
                authManager = authManager,
                navController = navController
            )
        }
        composable(AppScreens.DASHBOARD.ruta) {
            DashboardScreen(
                auth = authManager,
                analytics = analytics,
                navController = navController,
                database = database

            )
        }
        composable(AppScreens.FORGOTPASSWORD.ruta) {
            ForgotPasswordScreen(
                auth = authManager,
                analytics = analytics,
                navController = navController
            )
        }
        composable(AppScreens.MOVABLE.ruta) {
            MovableContentScreen()
        }
        composable(AppScreens.IMAGE.ruta) {
            ImageScren()
        }
    }
}