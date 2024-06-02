package com.tfg.supercomparator.ui.view

import android.app.AlertDialog
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseUser
import com.tfg.supercomparator.R
import com.tfg.supercomparator.domain.modules.auth.AuthManager
import com.tfg.supercomparator.domain.modules.model.api.Message
import com.tfg.supercomparator.domain.modules.network.QuoteRepository
import com.tfg.supercomparator.domain.modules.network.SuperComparatorQuoteService
import com.tfg.supercomparator.ui.navigation.AppScreens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

@Composable
fun SplashScreen(navController: NavController, authManager: AuthManager) {
    val context = LocalContext.current
    val user: FirebaseUser? = authManager.getCurrentUser()

    LaunchedEffect(key1 = true) {
        testApiConexion(context)
        navController.popBackStack()
        if (user == null) navController.navigate(AppScreens.LOGIN.ruta) else navController.navigate(
            AppScreens.DASHBOARD.ruta
        )
    }
    Splash()
}

@Preview
@Composable
private fun Splash() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "SuperComparator",
            Modifier.size(100.dp, 150.dp),
        )
        Text(text = "SuperComparator", fontSize = 30.sp, fontWeight = FontWeight.Bold)
    }
}

private suspend fun testApiConexion(context: Context) {
    try {
        val response = withContext(Dispatchers.IO) { callApiConexion() }
        showDialog("Conexión exitosa", response.message, context)
        QuoteRepository.apiConexion = true
    } catch (e: HttpException) {
        showDialog("Error en la conexión", e.message ?: "Error desconocido", context)
    } catch (e: Throwable) {
        showDialog("No se pudo conectar a la API", e.message ?: "Error desconocido", context)
    }
}

private suspend fun callApiConexion(): Message {
    return withContext(Dispatchers.IO) {
        QuoteRepository
            .getSupercomparatorAPIClient()
            .create(SuperComparatorQuoteService::class.java)
            .testConexion()
    }
}

private fun showDialog(title: String, message: String, context: Context) {
    val builder = AlertDialog.Builder(context)
    builder.setTitle(title)
    builder.setMessage(message)
    builder.setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
    val dialog = builder.create()
    dialog.show()
}
