package com.tfg.supercomparator.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
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
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.tfg.supercomparator.R
import com.tfg.supercomparator.ui.theme.DarkGreen
import com.tfg.supercomparator.ui.theme.Green
import com.tfg.supercomparator.ui.theme.blackGray

@Preview
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DashboardScreen(navController: NavHostController = rememberNavController()) {
    val selectedIndex = remember { mutableIntStateOf(0) }
    val uiColor = if (isSystemInDarkTheme()) DarkGreen else Green

    Surface(color = uiColor) {
        CustomTopAppBar()
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
                        .fillMaxHeight(0.85f)
                ) {
                    when (selectedIndex.value) {
                        0 -> {
                            SearchScreen()
                        }

                        1 -> {
                        }

                        2 -> {
                        }

                        3 -> {
                        }
                    }
                }
                CustomBottomBar(selectedIndex = selectedIndex)
            }
        }
    }
}

@Composable
fun CustomTopAppBar() {
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
                    onClick = { }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.settings),
                        contentDescription = "settings",
                        modifier = Modifier.size(32.dp)
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
    val listItems = listOf("Search", "Lists", "Favorite", "Profile")
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 22.dp)
            .height(64.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(uiColor),
    ) {
        BottomNavigation(elevation = 0.dp, backgroundColor = uiColor) {
            listItems.forEachIndexed { index, label ->
                val isSelected = selectedIndex.value == index
                BottomNavigationItem(
                    icon = {
                        when (index) {
                            0 -> {
                                TabIcons(
                                    painterResource(id = R.drawable.search),
                                    isSelected
                                )
                            }

                            1 -> {
                                TabIcons(
                                    painterResource(id = R.drawable.list),
                                    isSelected
                                )
                            }

                            2 -> {
                                TabIcons(
                                    painterResource(id = R.drawable.heart),
                                    isSelected
                                )
                            }

                            3 -> {
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