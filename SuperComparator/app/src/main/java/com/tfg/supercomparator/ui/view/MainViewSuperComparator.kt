package com.tfg.supercomparator.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.tfg.supercomparator.ui.navigation.AppNavigation
import com.tfg.supercomparator.ui.theme.ComposeLoginScreenInitTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainViewSuperComparator : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLoginScreenInitTheme {
                AppNavigation(this)
            }
        }
    }
}
