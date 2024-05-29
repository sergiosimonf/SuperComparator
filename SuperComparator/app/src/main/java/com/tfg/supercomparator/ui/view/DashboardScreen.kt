package com.tfg.supercomparator.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TopAppBar
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tfg.supercomparator.R
import com.tfg.supercomparator.domain.modules.analytics.AnalyticsManager
import com.tfg.supercomparator.domain.modules.auth.AuthManager
import com.tfg.supercomparator.domain.modules.data.AppDatabase
import com.tfg.supercomparator.ui.navigation.AppScreens
import com.tfg.supercomparator.ui.theme.DarkGreen
import com.tfg.supercomparator.ui.theme.Green
import com.tfg.supercomparator.ui.theme.blackGray
import com.tfg.supercomparator.viewModel.DashboardViewModel
import com.tfg.supercomparator.viewModel.FavoriteViewModel
import com.tfg.supercomparator.viewModel.SearchScreemViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = DashboardViewModel(),
    navController: NavHostController,
    analytics: AnalyticsManager,
    auth: AuthManager,
    database: AppDatabase,
) {
    analytics.LogScreenView(screenName = AppScreens.DASHBOARD.ruta)
    val selectedIndex = remember { mutableIntStateOf(1) }
    val showDialog by viewModel.showDialog.observeAsState(initial = false)

    val uiColor = if (isSystemInDarkTheme()) DarkGreen else Green

    val onLogoutConfirmed: () -> Unit = {
        auth.signOut()
        navController.navigate(AppScreens.LOGIN.ruta) {
            popUpTo(AppScreens.DASHBOARD.ruta) {
                inclusive = true
            }
        }
    }

    Surface(color = uiColor) {
        CustomTopAppBar(viewModel)
        Box(
            contentAlignment = Alignment.TopCenter
        ) {
            Card(
                shape = RoundedCornerShape(topStart = 50.dp, topEnd = 40.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 55.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(0.85f),
                ) {
                    when (selectedIndex.intValue) {
                        0 -> {
                            FavoriteScreen(database, FavoriteViewModel(),  navController)
                        }

                        1 -> {
                            SearchScreen(database,navController, SearchScreemViewModel())
                        }

                        2 -> {
                            UserScreen(
                                analytics = analytics,
                                auth = auth,
                                navigation = navController
                            )
                        }
                    }
                }
                if (showDialog) {
                    LogoutDialog(onConfirmLogout = {
                        onLogoutConfirmed()
                        viewModel.hiadeDialog()
                    }, onDismiss = { viewModel.hiadeDialog() })
                }
                CustomBottomBar(selectedIndex = selectedIndex)
            }
        }
    }
}

@Composable
fun CustomTopAppBar(viewModel: DashboardViewModel) {
    val uiColor = if (isSystemInDarkTheme()) DarkGreen else Green

    TopAppBar(
        elevation = 0.dp,
        modifier = Modifier.fillMaxWidth(),
        title = {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "SuperComparator",
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White,
                    style = TextStyle(
                        fontStyle = FontStyle.Italic,
                        fontFamily = FontFamily(Font((R.font.roboto_bold))),
                        fontSize = 26.sp
                    )
                )
                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = { viewModel.showDialog() }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.logout),
                        contentDescription = "settings",
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        },
        backgroundColor = uiColor,
    )
}

@Composable
fun CustomBottomBar(selectedIndex: MutableState<Int>) {
    val uiColor = if (isSystemInDarkTheme()) DarkGreen else Green
    val listItems = listOf("Search", "Favorite", "Profile")
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 46.dp, end = 46.dp, top = 22.dp)
            .height(64.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(uiColor),
        contentAlignment = Alignment.Center
    ) {
        BottomNavigation(elevation = 0.dp, backgroundColor = uiColor) {
            listItems.forEachIndexed { index, label ->
                val isSelected = selectedIndex.value == index
                BottomNavigationItem(
                    icon = {
                        when (index) {
                            0 -> {
                                TabIcons(
                                    painterResource(id = R.drawable.heart),
                                    isSelected
                                )
                            }

                            1 -> {
                                TabIcons(
                                    painterResource(id = R.drawable.search),
                                    isSelected
                                )
                            }

                            2 -> {
                                TabIcons(
                                    painterResource(id = R.drawable.profile),
                                    isSelected
                                )
                            }
                        }
                    },
                    selected = isSelected,
                    onClick = { selectedIndex.value = index },
                    alwaysShowLabel = false,
                )
            }
        }
    }
}

@Composable
fun TabIcons(icon: Painter, isTintColor: Boolean) {
    val uiColor = if (isSystemInDarkTheme()) Green else blackGray
    if (isTintColor) {
        Icon(
            modifier = Modifier
                .wrapContentSize()
                .size(20.dp),
            painter = icon,
            tint = Color.White,
            contentDescription = "tb_icon_if"
        )
    } else {
        Icon(
            modifier = Modifier
                .wrapContentSize()
                .size(20.dp),
            painter = icon,
            tint = uiColor,
            contentDescription = "tb_icon_else"
        )
    }
}

@Composable
fun LogoutDialog(onConfirmLogout: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Cerrar sesión") },
        text = { Text("¿Estás seguro que deseas cerrar sesión?") },
        confirmButton = {
            Button(
                onClick = onConfirmLogout
            ) {
                Text("Aceptar")
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss
            ) {
                Text("Cancelar")
            }
        }
    )
}