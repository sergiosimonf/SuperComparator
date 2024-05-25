package com.tfg.supercomparator.ui.view

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.GoogleAuthProvider
import com.tfg.supercomparator.R
import com.tfg.supercomparator.domain.modules.analytics.AnalyticsManager
import com.tfg.supercomparator.domain.modules.auth.AuthManager
import com.tfg.supercomparator.domain.modules.auth.AuthRes
import com.tfg.supercomparator.ui.navigation.AppScreens
import com.tfg.supercomparator.ui.theme.DarkGreen
import com.tfg.supercomparator.ui.theme.GrayBanished
import com.tfg.supercomparator.ui.theme.Green
import com.tfg.supercomparator.ui.theme.Roboto
import com.tfg.supercomparator.ui.view.components.LoginTextField
import com.tfg.supercomparator.ui.view.components.RegisterTextField
import com.tfg.supercomparator.ui.view.components.SocialMediaLognIn
import com.tfg.supercomparator.ui.view.components.SocialMediaLognInIcon
import com.tfg.supercomparator.viewModel.LoginViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: LoginViewModel = LoginViewModel(),
    analytics: AnalyticsManager,
    auth: AuthManager,
) {
    analytics.LogScreenView(screenName = AppScreens.LOGIN.ruta)
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val uiColor = if (isSystemInDarkTheme()) DarkGreen else Green

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        when (val account =
            auth.handleSignInResult(GoogleSignIn.getSignedInAccountFromIntent(result.data))) {
            is AuthRes.Success -> {
                val credential = GoogleAuthProvider.getCredential(account?.data?.idToken, null)
                scope.launch {
                    val fireUser = auth.signInWithGoogleCredential(credential)
                    if (fireUser != null) {
                        Toast.makeText(context, "Bienvenidx", Toast.LENGTH_SHORT).show()
                        navController.navigate(AppScreens.DASHBOARD.ruta) {
                            popUpTo(AppScreens.LOGIN.ruta) {
                                inclusive = true
                            }
                        }
                    }
                }
            }

            is AuthRes.Error -> {
                analytics.logError("Error SignIn: ${account.errorMessage}")
                Toast.makeText(context, "Error: ${account.errorMessage}", Toast.LENGTH_SHORT).show()
            }

            else -> {
                Toast.makeText(context, "Error desconocido", Toast.LENGTH_SHORT).show()
            }
        }
    }


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
                Spacer(modifier = Modifier.height(40.dp))
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 25.dp)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LoginSection(viewModel, auth, analytics, scope, context, navController)
                    Spacer(modifier = Modifier.height(30.dp))
                    SocialMediaSection(
                        viewModel,
                        auth,
                        analytics,
                        scope,
                        context,
                        navController,
                        googleSignInLauncher
                    )
                    DontHaveAccountSecction(navController)
                }
            }
        }
    }
}

@Composable
private fun DontHaveAccountSecction(navController: NavHostController) {
    val uiColor = if (isSystemInDarkTheme()) DarkGreen else Green

    Row(
        modifier = Modifier
            .padding(top = 50.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = GrayBanished,
                    fontSize = 14.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Normal
                )
            ) {
                append("Don´t have a account?")
            }
        })
        Spacer(modifier = Modifier.width(5.dp))
        ClickableText(text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = uiColor,
                    fontSize = 14.sp,
                    fontFamily = Roboto,
                    fontWeight = FontWeight.Medium,
                    textDecoration = TextDecoration.Underline
                )
            ) {
                append("Create now")
            }
        }) {
            navController.navigate(AppScreens.REGISTER.ruta)
        }
    }
    Spacer(modifier = Modifier.height(50.dp))
}

@Composable
private fun SocialMediaSection(
    viewModel: LoginViewModel,
    auth: AuthManager,
    analytics: AnalyticsManager,
    scope: CoroutineScope,
    context: Context,
    navController: NavHostController,
    googleSignInLauncher: ManagedActivityResultLauncher<Intent, ActivityResult>,
) {
    val uiColor = if (isSystemInDarkTheme()) DarkGreen else Green

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Or continue with",
            style = MaterialTheme.typography.labelMedium.copy(color = uiColor)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SocialMediaLognInIcon(
                icon = R.drawable.incognito,
                text = "No Account",
                modifier = Modifier.weight(1f),
            ) {
                scope.launch {
                    viewModel.signInAnonymously(auth, analytics, context, navController)
                }
            }
            Spacer(modifier = Modifier.width(20.dp))
            SocialMediaLognIn(
                icon = R.drawable.google,
                text = "Google",
                modifier = Modifier.weight(1f),
                onClick = { auth.signInWithGoogle(googleSignInLauncher) }
            )
        }
    }
}

@Composable
private fun LoginSection(
    viewModel: LoginViewModel,
    authManager: AuthManager,
    analytics: AnalyticsManager,
    scope: CoroutineScope,
    context: Context,
    navController: NavHostController,
) {
    val email: String by viewModel.email.observeAsState(initial = "")
    val password by viewModel.password.observeAsState(initial = "")
    val passwordMode by viewModel.passwordMode.observeAsState(initial = true)

    val uiColor = if (isSystemInDarkTheme()) DarkGreen else Green

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val loginText = "Log in to your account."
        val loginWord = "Log in"
        val loginAnnotatedString = buildAnnotatedString {
            append(loginText)
            addStyle(
                style = SpanStyle(
                    color = Color.Gray,
                    fontFamily = FontFamily(Font(R.font.inter_medium))
                ),
                start = 0,
                end = loginText.length
            )
            addStyle(
                style = SpanStyle(
                    color = uiColor,
                    fontFamily = FontFamily(Font(R.font.inter_medium))
                ),
                start = 0,
                end = loginWord.length
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 20.dp),
            text = loginAnnotatedString,
            textAlign = TextAlign.Center,
            fontSize = 22.sp,
        )
    }
    Spacer(modifier = Modifier.height(25.dp))
    LoginTextField(
        label = "Email",
        value = email,
        onValueChange = { viewModel.onLoginChanged(it, password) },
        passwordMode = false,
        trailing = "",
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(15.dp))
    RegisterTextField(
        label = "Password",
        value = password,
        onValueChange = { viewModel.onLoginChanged(email, it) },
        onIconButtonClick = { viewModel.changePasswordMode() },
        passwordTextField = true,
        textPassWordMode = passwordMode,
        modifier = Modifier.fillMaxWidth()
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
        horizontalArrangement = Arrangement.End,
    ) {
        ClickableText(text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = uiColor,
                    fontSize = 12.sp,
                    fontFamily = Roboto,
                )
            ) {
                append("Forgot Password")
            }
        }) {
            navController.navigate(AppScreens.FORGOTPASSWORD.ruta)
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            scope.launch {
                viewModel.signIn(authManager, analytics, context, navController)
            }
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = uiColor,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(size = 4.dp)
    ) {
        Text(
            text = "Log in",
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Medium)
        )
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