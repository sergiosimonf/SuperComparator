package com.tfg.supercomparator.ui.view

import android.content.Context
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tfg.supercomparator.R
import com.tfg.supercomparator.domain.modules.analytics.AnalyticsManager
import com.tfg.supercomparator.domain.modules.auth.AuthManager
import com.tfg.supercomparator.ui.navigation.AppScreens
import com.tfg.supercomparator.ui.theme.DarkGreen
import com.tfg.supercomparator.ui.theme.GrayBanished
import com.tfg.supercomparator.ui.theme.Green
import com.tfg.supercomparator.ui.view.components.LoginTextField
import com.tfg.supercomparator.viewModel.ForgotPasswordViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ForgotPasswordScreen(
    auth: AuthManager,
    analytics: AnalyticsManager,
    viewModel: ForgotPasswordViewModel = ForgotPasswordViewModel(),
    navController: NavController,
) {
    analytics.LogScreenView(screenName = AppScreens.FORGOTPASSWORD.ruta)

//            val email: String by viewModel.email.observeAsState(initial = "")
    val uiColor = if (isSystemInDarkTheme()) DarkGreen else Green
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Surface(color = uiColor) {
        Box(
            contentAlignment = Alignment.TopCenter
        ) {
            TopSection()
            Card(
                shape = RoundedCornerShape(topStart = 45.dp, topEnd = 45.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 240.dp)
            ) {
                Spacer(modifier = Modifier.height(70.dp))
                Column(
                    modifier = Modifier
                        .padding(horizontal = 25.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ForgotPasswordSection(
                        navController,
                        uiColor,
                        viewModel,
                        auth,
                        analytics,
                        scope,
                        context
                    )
                }
            }
        }
    }
}

@Composable
private fun ForgotPasswordSection(
    navController: NavController,
    uiColor: Color,
    viewModel: ForgotPasswordViewModel,
    auth: AuthManager,
    analytics: AnalyticsManager,
    scope: CoroutineScope,
    context: Context,
) {
    val email by viewModel.email.observeAsState(initial = "")

    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Forgot password",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start
        )
    }
    Spacer(modifier = Modifier.size(15.dp))
    Text(
        text = "Enter yout email for the verification proccess, we will send a email",
        color = GrayBanished,
    )
    Spacer(modifier = Modifier.size(30.dp))

    LoginTextField(
        label = "Email",
        value = email,
        onValueChange = { viewModel.onEmailChanged(it) },
        passwordMode = false,
        trailing = "",
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.size(40.dp))
    Button(
        onClick = {
            scope.launch {
                viewModel.forgotPassword(analytics, auth, context, navController)
            }
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(size = 4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = uiColor,
            contentColor = Color.White
        ),
    ) {
        Text(text = "Continue")
    }
    Spacer(modifier = Modifier.size(15.dp))
    Button(
        onClick = { navController.navigate(AppScreens.LOGIN.ruta) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(size = 4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = uiColor
        ),
    ) {
        Text(text = "Back to the Login Screen")
    }
}


@Composable
private fun TopSection() {
    val uiColor = if (isSystemInDarkTheme()) Color(0xFFFFFBFC) else Color(0xFFFFFBFC)

    Row(
        modifier = Modifier
            .padding(top = 35.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {
        Column(
            modifier = Modifier
                .weight(0.30f),
        ) {

            Icon(
                painter = painterResource(id = R.drawable.logo_v1),
                contentDescription = null,
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.CenterHorizontally),
                tint = uiColor
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                text = stringResource(id = R.string.compareitor),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineMedium,
                color = uiColor
            )
        }
//        Image(
//            painter = painterResource(id = R.drawable.supermarket_shop),
//            modifier = Modifier
//                .size(150.dp)
//                .weight(0.25f),
//            contentDescription = null,
//        )
    }
}