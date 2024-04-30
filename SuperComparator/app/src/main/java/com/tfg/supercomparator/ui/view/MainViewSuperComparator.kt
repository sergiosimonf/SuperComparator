package com.tfg.supercomparator.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.tfg.supercomparator.domain.modules.model.carrefour.product.CarrefourProduct
import com.tfg.supercomparator.ui.navigation.AppNavigation
import com.tfg.supercomparator.ui.theme.ComposeLoginScreenInitTheme
import com.tfg.supercomparator.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainViewSuperComparator : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLoginScreenInitTheme {
                AppNavigation()
                val carrefourProduct: CarrefourProduct
            }
        }
    }
}
