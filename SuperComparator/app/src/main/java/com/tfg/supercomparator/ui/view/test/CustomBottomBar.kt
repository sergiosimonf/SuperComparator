//package com.tfg.supercomparator.ui.view.test
//
//import android.annotation.SuppressLint
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.wrapContentSize
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.BottomNavigation
//import androidx.compose.material.BottomNavigationItem
//import androidx.compose.material.Card
//import androidx.compose.material.IconButton
//import androidx.compose.material.Scaffold
//import androidx.compose.material.Surface
//import androidx.compose.material.TopAppBar
//import androidx.compose.material3.Icon
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.ColorFilter
//import androidx.compose.ui.graphics.ImageBitmap
//import androidx.compose.ui.graphics.painter.Painter
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.imageResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.Font
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.tfg.supercomparator.R
//import com.tfg.supercomparator.ui.theme.GrayBanished
//import com.tfg.supercomparator.ui.theme.Green
//import com.tfg.supercomparator.ui.theme.LightBlueWhite
//
//@Preview
//@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
//@Composable
//fun DashboardScreen() {
//    val selectedIndex = remember { mutableIntStateOf(0) }
//    Surface(modifier = Modifier.fillMaxSize()) {
//        Scaffold(
//            topBar = {
//                CustomTopAppBar()
//            },
//            content = {
//                Surface(modifier = Modifier.fillMaxSize(), color = Green) {
//                    Card(
//                        backgroundColor = Color.White,
//                        shape = RoundedCornerShape(topStart = 40.dp, topEnd = 40.dp),
//                        modifier = Modifier.fillMaxSize(),
//                    ) {
//                        Box(
//                            modifier = Modifier
//                                .padding(bottom = 96.dp)
//                        ) {
//                            when (selectedIndex.value) {
//                                0 -> {
//                                    ModalBottomSheetValue()
//                                }
//
//                                1 -> {
//                                    DockedSearchBarM3()
//                                }
//
//                                2 -> {
//                                }
//
//                                3 -> {
//                                }
//                            }
//                        } //bodyContent()
//
//                    }
//                }
//            },
//            bottomBar = {
//                CustomBottomBar(selectedIndex = selectedIndex)
//            },
//
//            )
//
//    }
//}
//
//@Composable
//fun CustomTopAppBar() {
//    TopAppBar(
//        elevation = 0.dp,
//        modifier = Modifier.fillMaxWidth(),
//        title = {
//            Box(modifier = Modifier.fillMaxWidth()) {
//                Text(
//                    text = "SuperComparator",
//                    modifier = Modifier.align(Alignment.Center),
//                    color = Color.White,
//                    style = TextStyle(
//                        fontStyle = FontStyle.Italic,
//                        fontFamily = FontFamily(Font((R.font.roboto_bold))),
//                        fontSize = 22.sp
//                    )
//                )
//                IconButton(
//                    modifier = Modifier.align(Alignment.CenterEnd),
//                    onClick = { }
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.settings),
//                        contentDescription = "settings",
//                        modifier = Modifier.size(32.dp)
//                    )
//                }
//            }
//        },
//        backgroundColor = Green,
//    )
//}
//
//@Composable
//fun CustomBottomBar(selectedIndex: MutableState<Int>) {
//
//    val listItems = listOf("Home", "Location", "Cart", "Profile")
//
//    Card(
//        elevation = 0.dp,
//        shape = RoundedCornerShape(20.dp),
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//            .height(64.dp)
//    ) {
//        BottomNavigation(backgroundColor = LightBlueWhite) {
//            listItems.forEachIndexed { index, label ->
//                val isSelected = selectedIndex.value == index
//                BottomNavigationItem(
//                    icon = {
//                        when (index) {
//                            0 -> {
//                                TabIcons(
//                                    painterResource(id = R.drawable.search),
//                                    isSelected
//                                )
//                            }
//
//                            1 -> {
//                                TabIcons(
//                                    painterResource(id = R.drawable.list),
//                                    isSelected
//                                )
//                            }
//
//                            2 -> {
//                                TabIcons(
//                                    painterResource(id = R.drawable.heart),
//                                    isSelected
//                                )
//                            }
//
//                            3 -> {
//                                TabIcons(
//                                    painterResource(id = R.drawable.profile),
//                                    isSelected
//                                )
//                            }
//                        }
//                    },
//                    selected = isSelected,
//                    onClick = { selectedIndex.value = index },
//                    alwaysShowLabel = false
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun TabIcons(icon: Painter, isTintColor: Boolean) {
//    if (isTintColor) {
//        Icon(
//            modifier = Modifier.wrapContentSize().size(20.dp),
//            painter = icon,
//            tint = Green,
//            contentDescription = "tb_icon_if"
//        )
//    } else {
//        Icon(
//            modifier = Modifier.wrapContentSize().size(20.dp),
//            painter = icon,
//            tint = GrayBanished,
//            contentDescription = "tb_icon_else"
//        )
//    }
//}
//
//
//
