package com.tfg.supercomparator.ui.navigation

import android.content.Context
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tfg.supercomparator.domain.modules.analytics.AnalyticsManager
import com.tfg.supercomparator.domain.modules.auth.AuthManager
import com.tfg.supercomparator.domain.modules.data.DatabaseProvider
import com.tfg.supercomparator.domain.modules.model.product.Product
import com.tfg.supercomparator.ui.view.DashboardScreen
import com.tfg.supercomparator.ui.view.ForgotPasswordScreen
import com.tfg.supercomparator.ui.view.ImageScren
import com.tfg.supercomparator.ui.view.LoginScreen
import com.tfg.supercomparator.ui.view.ProductScreen
import com.tfg.supercomparator.ui.view.RegisterScreen
import com.tfg.supercomparator.ui.view.SplashScreen
import com.tfg.supercomparator.ui.view.test.MovableContentScreen
import kotlinx.serialization.json.Json

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
        composable(
            route = AppScreens.PRODUCT.ruta,
            arguments = listOf(navArgument(NavArgs.Product.key) { type = ProductNavType })
//            arguments = listOf(navArgument(NavArgs.Product.key) { type = parcelableOf<Product>() })
        ) {
            ProductScreen(
                navController = navController,
                database = database
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

inline fun <reified T : Parcelable> parcelableOf() = object : NavType<T>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): T? {
        return bundle.getParcelable(key) as T?
    }

    override fun parseValue(value: String): T {
        return Json.decodeFromString(Uri.decode(value))
    }

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putParcelable(key, value)
    }

}

val ProductNavType = object : NavType<Product>(isNullableAllowed = false) {
    override fun put(bundle: Bundle, key: String, value: Product) {
        bundle.putParcelable(key, value)
    }

    override fun get(bundle: Bundle, key: String): Product? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(key, Product::class.java)
        } else {
            bundle.getParcelable(key) as Product?
        }
    }

    override fun parseValue(value: String): Product {
        return Json.decodeFromString(Uri.decode(value))
    }
}