package com.tfg.supercomparator.ui.view

import android.content.ContentValues
import android.graphics.Typeface
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.patrykandpatrick.vico.compose.cartesian.CartesianChartHost
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberBottomAxis
import com.patrykandpatrick.vico.compose.cartesian.axis.rememberStartAxis
import com.patrykandpatrick.vico.compose.cartesian.decoration.rememberHorizontalLine
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineCartesianLayer
import com.patrykandpatrick.vico.compose.cartesian.layer.rememberLineSpec
import com.patrykandpatrick.vico.compose.cartesian.rememberCartesianChart
import com.patrykandpatrick.vico.compose.cartesian.rememberVicoZoomState
import com.patrykandpatrick.vico.compose.common.component.rememberLineComponent
import com.patrykandpatrick.vico.compose.common.component.rememberShapeComponent
import com.patrykandpatrick.vico.compose.common.component.rememberTextComponent
import com.patrykandpatrick.vico.compose.common.of
import com.patrykandpatrick.vico.compose.common.shader.color
import com.patrykandpatrick.vico.core.cartesian.data.CartesianChartModelProducer
import com.patrykandpatrick.vico.core.cartesian.data.CartesianValueFormatter
import com.patrykandpatrick.vico.core.cartesian.data.lineSeries
import com.patrykandpatrick.vico.core.cartesian.decoration.HorizontalLine
import com.patrykandpatrick.vico.core.common.Dimensions
import com.patrykandpatrick.vico.core.common.data.ExtraStore
import com.patrykandpatrick.vico.core.common.shader.DynamicShader
import com.patrykandpatrick.vico.core.common.shape.Shape
import com.tfg.supercomparator.R
import com.tfg.supercomparator.domain.modules.data.AppDatabase
import com.tfg.supercomparator.ui.theme.DarkGreen
import com.tfg.supercomparator.ui.theme.Green
import com.tfg.supercomparator.ui.view.components.FavoriteButton
import com.tfg.supercomparator.ui.view.components.rememberMarker
import com.tfg.supercomparator.viewModel.ProductViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ProductScreen(
    viewModel: ProductViewModel = viewModel(),
    navController: NavHostController,
    database: AppDatabase,
) {
    val scope = rememberCoroutineScope()
    val uiColor = if (isSystemInDarkTheme()) DarkGreen else Green
    val product = viewModel.state!!
    val modelProducer = remember { CartesianChartModelProducer.build() }
    val data by viewModel.data.observeAsState(initial = emptyMap())

    if (data.isNotEmpty()){
        LaunchedEffect(Unit) {
            withContext(Dispatchers.Default) {
                val xToDates = data.keys.associateBy { it.toEpochDay().toFloat() }
                modelProducer.tryRunTransaction {
                    lineSeries { series(xToDates.keys, data.values) }
                    updateExtras { it[xToDateMapKey] = xToDates }
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = uiColor,
                    titleContentColor = Color.White,
                ),
                title = {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = { navController.popBackStack() },
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Add")
                        }
                        Spacer(modifier = Modifier.size(12.dp))
                        Text("Top app bar")
                    }
                }
            )
        },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = {  },
//                containerColor = uiColor,
//                contentColor = Color.White,
//            ) {
//                Icon(Icons.Default.Add, contentDescription = "Add")
//            }
//        }
    ) { innerPadding ->
        Card(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(325.dp)
                        .padding(top = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = product.imageUrl,
                        placeholder = painterResource(id = R.drawable.image_placeholder),
                        error = painterResource(R.drawable.image_placeholder),
                        contentDescription = null,
                        modifier = Modifier
                            .aspectRatio(1280f / 847f)
                            .fillMaxWidth(),
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 20.dp),
                                horizontalArrangement = Arrangement.End
                            ) {
                                if (product.hasOfertaExtra) {
                                    Card(
                                        modifier = Modifier.padding(top = 13.dp),
                                        colors = CardDefaults.cardColors(Color.Red),
                                    ) {
                                        product.ofertExtra?.let {
                                            Text(
                                                text = it,
                                                color = Color.White,
                                                style = MaterialTheme.typography.bodyLarge,
                                                fontSize = 15.sp,
                                                modifier = Modifier.padding(
                                                    start = 15.dp,
                                                    end = 15.dp
                                                )
                                            )
                                        }
                                    }
                                }
                                FavoriteButton(
                                    onCheckedChange = {
                                        product.isFavorite = it
                                        scope.launch(Dispatchers.IO) {
                                            database.productDAO.upsertProduct(product)
                                        }
                                    },
                                    isFavorite = product.isFavorite
                                )
                            }
                            Row(
                                Modifier.padding(start = 20.dp)
                            ) {
                                Image(
                                    painter = painterResource(id = product.tiendaIcon),
                                    contentDescription = null,
                                    modifier = Modifier.size(50.dp)
                                )
                            }
                        }
                    }
                }
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = product.name,
                        color = Color.Gray,
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    if (product.hasOferta) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "${product.priceNoOfert}",
                                color = Color.Gray,
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.LineThrough,
                                modifier = Modifier.padding()
                            )
                            OfferBadge(product.priceNoOfert, product.priceOfert)
                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp, end = 50.dp),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Text(
                            text = if (product.hasOferta) "${product.priceOfert}€" else "${product.price}€",
                            color = Color.Gray,
                            style = MaterialTheme.typography.displaySmall,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(end = 6.dp)
                        )
                        Spacer(modifier = Modifier.size(10.dp))
                        Text(
                            text = product.pricePerUnitText,
                            color = Color.Gray,
                            style = MaterialTheme.typography.headlineSmall,
                        )
                    }
                    Spacer(modifier = Modifier.size(20.dp))
                    Box(modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize()){
                        ChartHistoryPrice(modelProducer = modelProducer, modifier = Modifier, data)
                    }
                }
            }
        }
    }
}

@Composable
private fun ChartHistoryPrice(
    modelProducer: CartesianChartModelProducer,
    modifier: Modifier,
    data: Map<LocalDate, Float>,
) {
    val marker = rememberMarker()
    val uiColor =if (isSystemInDarkTheme()) DarkGreen else Green
    CartesianChartHost(
        chart =
        rememberCartesianChart(
            rememberLineCartesianLayer(listOf(rememberLineSpec(DynamicShader.color(uiColor)))),
            startAxis = rememberStartAxis(),
            bottomAxis = rememberBottomAxis(
                valueFormatter = bottomAxisValueFormatter,
            ),
            decorations = listOf(rememberComposeHorizontalLine(data))
        ),
        modelProducer = modelProducer,
        modifier = modifier,
        marker = marker,
        zoomState = rememberVicoZoomState(zoomEnabled = true),
    )
}

@Composable
private fun OfferBadge(priceNoOfert: Double?, priceOfert: Double?) {
    Card(
        colors = CardDefaults.cardColors(Color.Red),
        modifier = Modifier.padding(start = 15.dp)
    ) {
        if (priceOfert == null) {
            Log.e(ContentValues.TAG, "Wtf ")
        }
        Text(
            text = "${calcularPorcentajeDeOferta(priceNoOfert!!, priceOfert!!)}% dto",
            color = Color.White,
            style = MaterialTheme.typography.bodySmall,
            fontSize = 15.sp,
            modifier = Modifier.padding(7.dp)
        )
    }
}

@Composable
private fun rememberComposeHorizontalLine(data: Map<LocalDate, Float>): HorizontalLine {

    val HORIZONTAL_LINE_THICKNESS_DP = 2f
    val HORIZONTAL_LINE_LABEL_HORIZONTAL_PADDING_DP = 8f
    val HORIZONTAL_LINE_LABEL_VERTICAL_PADDING_DP = 2f
    val HORIZONTAL_LINE_LABEL_MARGIN_DP = 4f

    val uiColor = if (isSystemInDarkTheme()) DarkGreen else Green
    val total = data.values.sum()  // Sumar todos los valores
    val average = total / data.size.toFloat()  // Calcular el promedio
    return rememberHorizontalLine(
        y = { average },
        line = rememberLineComponent(uiColor, HORIZONTAL_LINE_THICKNESS_DP.dp),
        labelComponent =
        rememberTextComponent(
            background = rememberShapeComponent(Shape.Pill, uiColor),
            padding =
            Dimensions.of(
                HORIZONTAL_LINE_LABEL_HORIZONTAL_PADDING_DP.dp,
                HORIZONTAL_LINE_LABEL_VERTICAL_PADDING_DP.dp,
            ),
            margins = Dimensions.of(HORIZONTAL_LINE_LABEL_MARGIN_DP.dp),
            typeface = Typeface.MONOSPACE,
        ),
    )
}



fun calcularPorcentajeDeOferta(precioOriginal: Double, precioOferta: Double): Int {
    val diferencia = precioOriginal - precioOferta

    return (diferencia / precioOriginal * 100).toInt()
}

private val xToDateMapKey = ExtraStore.Key<Map<Float, LocalDate>>()

private val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("d MMM uuuu")

private val bottomAxisValueFormatter =
    CartesianValueFormatter { x, chartValues, _ ->
        (chartValues.model.extraStore[xToDateMapKey][x] ?: LocalDate.ofEpochDay(x.toLong()))
            .format(dateTimeFormatter)
    }