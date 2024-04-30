package com.tfg.supercomparator.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import com.tfg.supercomparator.ui.theme.Black
import com.tfg.supercomparator.ui.theme.GrayBanished
import com.tfg.supercomparator.ui.theme.Green
import com.tfg.supercomparator.ui.theme.Roboto
import com.tfg.supercomparator.ui.theme.bluishGray
import com.tfg.supercomparator.ui.view.components.LoginTextField
import com.tfg.supercomparator.ui.view.components.RegisterTextField
import com.tfg.supercomparator.ui.view.components.SocialMediaLognIn

@Preview
@Composable
fun LoginScreen(
    navController: NavHostController = rememberNavController(),
) {
    Surface {
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
                        .padding(horizontal = 25.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    RegisterSection()
                    Spacer(modifier = Modifier.height(30.dp))
                    SocialMediaSection()
                    DontHaveAccountSecction(navController)
                }
            }
        }
    }
}

@Composable
private fun DontHaveAccountSecction(navController: NavHostController) {
    val uiColor = if (isSystemInDarkTheme()) Color.White else Black
    Row(
        modifier = Modifier
            .fillMaxHeight(fraction = 0.8f)
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
                    color = if (isSystemInDarkTheme()) bluishGray else Green,
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
}

@Composable
private fun SocialMediaSection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Or continue with",
            style = MaterialTheme.typography.labelMedium.copy(color = GrayBanished)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            SocialMediaLognIn(
                icon = R.drawable.google,
                text = "Google",
                modifier = Modifier.weight(1f)
            ) {
            }
            Spacer(modifier = Modifier.width(20.dp))
            SocialMediaLognIn(
                icon = R.drawable.facebook,
                text = "Facebook",
                modifier = Modifier.weight(1f)
            ) {
            }
        }
    }
}

@Composable
private fun RegisterSection() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordMode by remember { mutableStateOf(true) }

    val uiColor = if (isSystemInDarkTheme()) Color(0xFFFFFBFC) else Color(0xFFEADEDA)
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
                    color = if (isSystemInDarkTheme()) bluishGray else Green,
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
        onValueChange = { email = it },
        passwordMode = false,
        trailing = "",
        modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(15.dp))
    RegisterTextField(
        label = "Password",
        value = password,
        onValueChange = { password = it },
        onIconButtonClick = { passwordMode = !passwordMode },
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
                    color = Green,
                    fontSize = 12.sp,
                    fontFamily = Roboto,
                )
            ) {
                append("Forgot Password")
            }
        }) {
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isSystemInDarkTheme()) bluishGray else Green,
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

    Image(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = 0.42f),
        painter = painterResource(id = R.drawable.green_square),
        contentDescription = null,
        contentScale = ContentScale.FillBounds
    )

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