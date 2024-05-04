//package com.tfg.supercomparator.ui.view.test
//
//import androidx.annotation.DrawableRes
//import androidx.compose.animation.ExperimentalAnimationApi
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.layout.wrapContentHeight
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.BackdropScaffold
//import androidx.compose.material.BackdropScaffoldDefaults
//import androidx.compose.material.BackdropValue
//import androidx.compose.material.Card
//import androidx.compose.material.ContentAlpha
//import androidx.compose.material.Divider
//import androidx.compose.material.ExperimentalMaterialApi
//import androidx.compose.material.Icon
//import androidx.compose.material.IconButton
//import androidx.compose.material.IconToggleButton
//import androidx.compose.material.LocalContentAlpha
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Surface
//import androidx.compose.material.Text
//import androidx.compose.material.TextField
//import androidx.compose.material.TextFieldDefaults
//import androidx.compose.material.TopAppBar
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.DateRange
//import androidx.compose.material.icons.filled.Favorite
//import androidx.compose.material.icons.filled.FavoriteBorder
//import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material.icons.filled.Place
//import androidx.compose.material.icons.filled.Search
//import androidx.compose.material.rememberBackdropScaffoldState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.CompositionLocalProvider
//import androidx.compose.runtime.Immutable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.graphicsLayer
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.TextStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.tooling.preview.PreviewParameter
//import androidx.compose.ui.tooling.preview.PreviewParameterProvider
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.tfg.supercomparator.R
//import kotlinx.coroutines.launch
//import java.text.DecimalFormat
//import kotlin.random.Random
//
///*
//    Material Design backdrop. This component provides an API to put together
//    several material components to construct your screen.
//
//    For a similar component which
//    implements the basic material design layout strategy with app bars,
//    floating action buttons and navigation drawers, use the standard Scaffold.
//
//    For similar component that uses a bottom sheet as the centerpiece of the screen,
//    use BottomSheetScaffold.
// */
//
///**
// * [Backdrop](https://material.io/components/backdrop#behavior)
// *
// *
// * ```backdropScaffoldState.conceal()``` is used to hide, and
// * ```backdropScaffoldState.reveal()``` to reveal bottom content which is **frontLayerContent**.
// *
// * ```headerHeight``` can be used to set **front layer content height** while it's concealed
// * ```peekHeight``` sets **total height for back layer starting from bottom of appBar**.
// *
// */
//@ExperimentalAnimationApi
//@ExperimentalMaterialApi
//@Preview
//@Composable
//fun Tutorial2_10Screen5() {
//    TutorialContent()
//}
//
//@OptIn(ExperimentalAnimationApi::class)
//@ExperimentalMaterialApi
//@Preview
//@Composable
//private fun TutorialContentPreview(
//    @PreviewParameter(BackdropValueProvider::class)
//    initialBackdropValue: BackdropValue
//) {
//    TutorialContent(initialBackdropValue)
//}
//
//@OptIn(ExperimentalMaterialApi::class)
//private class BackdropValueProvider : PreviewParameterProvider<BackdropValue> {
//    override val values: Sequence<BackdropValue>
//        get() = sequenceOf(
//            BackdropValue.Concealed,
//            BackdropValue.Revealed
//        )
//}
//
//@ExperimentalAnimationApi
//@ExperimentalMaterialApi
//@Composable
//private fun TutorialContent(initialBackdropValue: BackdropValue = BackdropValue.Revealed) {
//
//    val backdropScaffoldState =
//        rememberBackdropScaffoldState(initialValue = initialBackdropValue)
//    val coroutineScope = rememberCoroutineScope()
//
//    BackdropScaffold(
//        appBar = {
//            TopAppBar(
//                elevation = 8.dp,
//                title = {
//                    Text("BackdropScaffold")
//                },
//                navigationIcon = {
//                    IconButton(onClick = {
//                        if (backdropScaffoldState.isRevealed) {
//                            coroutineScope.launch { backdropScaffoldState.conceal() }
//                        } else if (backdropScaffoldState.isConcealed) {
//                            coroutineScope.launch { backdropScaffoldState.reveal() }
//                        }
//                    }) {
//                        Icon(Icons.Default.Menu, null)
//                    }
//                },
//            )
//        },
//        scaffoldState = backdropScaffoldState,
//        // Back layer properties
//        peekHeight = BackdropScaffoldDefaults.PeekHeight,
//        persistentAppBar = true,
////        backLayerBackgroundColor = MaterialTheme.colors.primary,
//        backLayerContent = {
//            BackLayerContent()
//        },
//        // Front layer properties
//        stickyFrontLayer = true,
//        headerHeight = BackdropScaffoldDefaults.HeaderHeight,
//        frontLayerShape = BackdropScaffoldDefaults.frontLayerShape,
//        frontLayerElevation = BackdropScaffoldDefaults.FrontLayerElevation,
//        // 🔥 Removes transparent white color when backdropScaffoldState in concealed
//        frontLayerScrimColor = Color.Unspecified,
//        frontLayerContent = {
//            FrontLayerContent()
//        }
//    ) {
//
//    }
//}
//
//@Composable
//private fun BackLayerContent() {
//    Column(
//        modifier = Modifier
//            .wrapContentHeight()
//            .fillMaxWidth()
//    ) {
//        Spacer(Modifier.height(16.dp))
//        BackLayerTextField("Search", "Search dummy...", Icons.Default.Search)
//        Spacer(Modifier.height(16.dp))
//        BackLayerTextField("Date", "Date dummy...", Icons.Default.DateRange)
//        Spacer(Modifier.height(16.dp))
//        BackLayerTextField("Place", "Place dummy...", Icons.Default.Place)
//        Spacer(Modifier.height(8.dp))
//    }
//}
//
//@Composable
//private fun FrontLayerContent() {
//    Column {
//        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
//            Text(
//                text = "SubHeader",
//                modifier = Modifier.padding(top = 16.dp, start = 8.dp, end = 8.dp, bottom = 8.dp)
//            )
//        }
//        Divider(
//            modifier = Modifier
//                .padding(horizontal = 8.dp)
//                .background(Color.LightGray)
//                .height(1.dp)
//        )
//        Spacer(modifier = Modifier.height(8.dp))
//        LazyColumn(
//            contentPadding = PaddingValues(8.dp),
//            verticalArrangement = Arrangement.spacedBy(8.dp)
//        ) {
//            items(placesses) { place ->
//                PlacesToBookVerticalComponent(placess = place)
//            }
//        }
//    }
//}
//
//@Composable
//private fun BackLayerTextField(
//    label: String,
//    placeHolder: String,
//    imageVector: ImageVector
//) {
//    var textFieldValue by remember { mutableStateOf("") }
//
//    TextField(
//        modifier = Modifier
//            .padding(8.dp)
//            .clip(RoundedCornerShape(8.dp))
//            .fillMaxWidth(),
//        value = textFieldValue,
//        label = { Text(label) },
//        placeholder = { Text(placeHolder) },
//        onValueChange = { newValue ->
//            textFieldValue = newValue
//        },
//        colors = TextFieldDefaults.textFieldColors(
//            backgroundColor = Color(0xffD1C4E9),
//        ),
//        textStyle = TextStyle(
//            color = MaterialTheme.colors.primary
//        ),
//        leadingIcon = {
//            Icon(imageVector = imageVector, contentDescription = null)
//        }
//    )
//}
//
//@Immutable
//data class Place(
//    val id: Long,
//    val description: String,
//    @DrawableRes val imgRes: Int,
//    val rating: Double = Random.nextDouble(0.0, 10.0),
//    val price: Int = Random.nextInt(500)
//)
//
//@Composable
//fun PlacesToBookVerticalComponent(placess: Placess) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth(),
//        elevation = 2.dp,
//        shape = RoundedCornerShape(8.dp)
//    ) {
//        Row(modifier = Modifier.clickable { }) {
//            PlaceContent(placess, Modifier.weight(1f))
//            ImageContent(placess)
//        }
//    }
//}
//
//@Composable
//private fun PlaceContent(placess: Placess, modifier: Modifier = Modifier) {
//
//    val rating = placess.rating
//
//    val triple = getPlaceProperties(rating)
//
//    val color = triple.first
//    val text = triple.second
//    val startCount = triple.third
//
//    Column(
//        modifier = modifier
//            .height(140.dp)
//            .padding(8.dp)
//    ) {
//
//        // 🔥 This weight pushes price text to bottom
//        Column(modifier = Modifier.weight(1f)) {
//            Text(
//                text = placess.description,
//                fontWeight = FontWeight.Bold,
//                fontSize = 18.sp,
//                overflow = TextOverflow.Ellipsis
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))
//
//            Row {
//                repeat(startCount) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.ic_baseline_star_12),
//                        contentDescription = null,
//                    )
//                }
//            }
//
//            Spacer(modifier = Modifier.height(8.dp))
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Surface(
//                    shape = RoundedCornerShape(4.dp),
//                    color = color
//
//                ) {
//                    Text(
//                        modifier = Modifier.padding(2.dp),
//                        text = decimalFormat.format(rating),
//                        fontSize = 12.sp,
//                        color = Color.White
//                    )
//                }
//                Spacer(modifier = Modifier.width(4.dp))
//                Text(text = text, color = color, fontSize = 12.sp)
//            }
//        }
//
//        Row {
//            // 🔥 This Spacer with weight pushes price tag to most right of this context
//            Spacer(modifier = Modifier.weight(1f))
//            Text(
//                text = "$${placess.price}",
//                color = Color(0xff4CAF50)
//            )
//        }
//    }
//}
//
//val decimalFormat = DecimalFormat("0.0")
//
//
//@Composable
//private fun getPlaceProperties(rating: Double): Triple<Color, String, Int> {
//    val triple = if (rating > 8) {
//        Triple(Color(0xff4CAF50), "Very good", 5)
//    } else if (rating <= 8 && rating > 6) {
//        Triple(Color(0xff2196F3), "Good", 4)
//    } else if (rating > 4 && rating <= 6) {
//        Triple(Color(0xffFFEB3B), "Mediocre", 3)
//
//    } else if (rating > 2 && rating <= 4) {
//        Triple(Color(0xffFF9800), "Bad", 2)
//    } else {
//        Triple(Color(0xffF44336), "Very bad", 1)
//    }
//    return triple
//}
//
//@Composable
//private fun ImageContent(placess: Placess) {
//    Box(contentAlignment = Alignment.TopEnd, modifier = Modifier.size(140.dp)) {
//
//        val painter = painterResource(id = placess.imgRes)
//
//        Image(
//            modifier = Modifier.fillMaxSize(),
//            contentScale = ContentScale.FillBounds,
//            painter = painter,
//            contentDescription = null
//        )
//
//        Surface(
//            shape = CircleShape,
//            modifier = Modifier
//                .padding(6.dp)
//                .size(32.dp),
//            color = Color(0x77000000)
//        ) {
//            FavoriteButtons(modifier = Modifier.padding(8.dp))
//        }
//    }
//}
//@Composable
//fun FavoriteButtons(
//    modifier: Modifier = Modifier,
//    color: Color = Color(0xffE91E63)
//) {
//
//    var isFavorite by remember { mutableStateOf(false) }
//
//    IconToggleButton(
//        checked = isFavorite,
//        onCheckedChange = {
//            isFavorite = !isFavorite
//        }
//    ) {
//        Icon(
//            tint = color,
//            modifier = modifier.graphicsLayer {
//                scaleX = 1.3f
//                scaleY = 1.3f
//            },
//            imageVector = if (isFavorite) {
//                Icons.Filled.Favorite
//            } else {
//                Icons.Default.FavoriteBorder
//            },
//            contentDescription = null
//        )
//    }
//
//}