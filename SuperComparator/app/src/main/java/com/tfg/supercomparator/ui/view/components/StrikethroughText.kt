package com.tfg.supercomparator.ui.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StrikethroughText(
    text: String,
    fontSize: TextUnit = 16.sp,
    colorLine: Color = Color.Red,
    textColor: Color = Color.Black,
) {

    val textStyle = TextStyle(fontSize = fontSize)
    val width = measureTextWidth(text, textStyle)
    val height = 16.dp

    Box(
        modifier = Modifier
            .size(width, height)
            .drawWithCache {
                onDrawWithContent {
                    drawContent()
                    drawLine(colorLine, Offset(0f, height.toPx()), Offset(width.toPx(), 0f), 4f)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, textAlign = TextAlign.Center, color = textColor)
    }
}

@Composable
fun measureTextWidth(text: String, style: TextStyle): Dp {
    val textMeasurer = rememberTextMeasurer()
    val widthInPixels = textMeasurer.measure(text, style).size.width
    return with(LocalDensity.current) { widthInPixels.toDp() }
}