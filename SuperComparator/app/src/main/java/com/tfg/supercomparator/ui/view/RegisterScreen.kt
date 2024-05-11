package com.tfg.supercomparator.ui.view

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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tfg.supercomparator.R
import com.tfg.supercomparator.ui.navigation.AppScreens
import com.tfg.supercomparator.ui.theme.DarkGreen
import com.tfg.supercomparator.ui.theme.GrayBanished
import com.tfg.supercomparator.ui.theme.Green
import com.tfg.supercomparator.ui.theme.Roboto
import com.tfg.supercomparator.ui.view.components.RegisterTextField
import com.tfg.supercomparator.viewModel.RegisterViewModel

@Preview
@Composable
fun RegisterScreen(
    navController: NavHostController = rememberNavController(),
    viewModel: RegisterViewModel = RegisterViewModel(navController)
) {
    val uiColor = if (isSystemInDarkTheme()) DarkGreen else Green

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
                    RegisterSection(viewModel)
                    Spacer(modifier = Modifier.height(10.dp))
                    RegisterSectionBotom(navController, viewModel)
                }
            }

        }
    }
}


@Composable
private fun RegisterSectionBotom(navController: NavHostController, viewModel: RegisterViewModel) {
    val uiColor = if (isSystemInDarkTheme()) DarkGreen else Green
    val passwordRecoverMode by viewModel.passwordRecoverMode.observeAsState(initial = false)

    Column {
        Row(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                ClickableText(
                    text = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                color = GrayBanished,
                                fontSize = 14.sp,
                                fontFamily = Roboto,
                                fontWeight = FontWeight.Medium,
                            )
                        ) {
                            append("¿Quieres añadir un método\npara recuperar la contraseña?")
                        }
                    },
                    onClick = {
                        viewModel.changePasswordRecoverMode()
                    },
                )
                Checkbox(
                    checked = passwordRecoverMode,
                    onCheckedChange = { viewModel.changePasswordRecoverMode() },
                    colors = CheckboxDefaults.colors(uiColor)
                )
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color(0xFFAAAAAA),
                        fontSize = 14.sp,
                        fontFamily = Roboto,
                        fontWeight = FontWeight.Normal
                    )
                ) {
                    append("Already have an account?")
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
                    append("Sign in")
                }
            }, onClick = {
                navController.navigate(AppScreens.LOGIN.ruta)
            })
        }
    }
    Spacer(modifier = Modifier.height(50.dp))
}

@Composable
private fun RegisterSection(viewModel: RegisterViewModel) {
    val uiColor = if (isSystemInDarkTheme()) DarkGreen else Green

    val email by viewModel.email.observeAsState(initial = "")
    val password by viewModel.password.observeAsState(initial = "")
    val repeatPassword by viewModel.repeatPassword.observeAsState(initial = "")
    val passwordMode by viewModel.passwordMode.observeAsState(initial = true)

    val loginText = "Register a new account."
    val loginWord = "Register"

    val loginAnnotatedString = buildAnnotatedString {
        append(loginText)
        addStyle(
            style = SpanStyle(
                color = Color.Gray, fontFamily = FontFamily(Font(R.font.inter_medium))
            ), start = 0, end = loginText.length
        )
        addStyle(
            style = SpanStyle(
                color = uiColor, fontFamily = FontFamily(Font(R.font.inter_medium))
            ), start = 0, end = loginWord.length
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
    Spacer(modifier = Modifier.height(25.dp))
    RegisterTextField(
        label = "Email",
        value = email,
        onValueChange = { viewModel.onRegisterChanged(it, password, repeatPassword) },
        onIconButtonClick = {},
        passwordTextField = false,
        textPassWordMode = false,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(15.dp))
    RegisterTextField(
        label = "Password",
        value = password,
        onValueChange = { viewModel.onRegisterChanged(email, it, repeatPassword) },
        onIconButtonClick = { viewModel.changePasswordMode() },
        passwordTextField = true,
        textPassWordMode = passwordMode,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(15.dp))
    RegisterTextField(
        label = "Repeat password",
        value = repeatPassword,
        onValueChange = { viewModel.onRegisterChanged(email, password, it) },
        onIconButtonClick = { viewModel.changePasswordMode() },
        passwordTextField = true,
        textPassWordMode = passwordMode,
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(20.dp))
    Button(
        modifier = Modifier.fillMaxWidth(), onClick = {}, colors = ButtonDefaults.buttonColors(
            containerColor = uiColor, contentColor = Color.White
        ), shape = RoundedCornerShape(size = 4.dp)
    ) {
        Text(
            text = "Register now",
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
            modifier = Modifier.weight(0.30f),
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