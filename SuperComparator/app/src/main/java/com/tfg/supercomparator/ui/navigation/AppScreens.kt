package com.tfg.supercomparator.ui.navigation

import android.net.Uri
import com.tfg.supercomparator.domain.modules.model.product.Product
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

sealed class AppScreens(val ruta: String) {
    data object LOGIN : AppScreens(ruta = "login")
    data object REGISTER : AppScreens("register")
    data object DASHBOARD : AppScreens("dashboard")
    data object FORGOTPASSWORD : AppScreens("forgotPassword")
    data object SPLASHSCREEN : AppScreens("splashscreen")
    data object PRODUCT : AppScreens("product/{${NavArgs.Product.key}}") {
        fun createRoute(product: Product) = "product/${Uri.encode(Json.encodeToJsonElement(product).toString())}"
    }
    data object IMAGE : AppScreens("image")
    data object MOVABLE : AppScreens("movable")
}

enum class NavArgs(val key: String) {
    Product("product")
}