package com.tfg.supercomparator.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tfg.supercomparator.R
import com.tfg.supercomparator.ui.view.components.DockedSearchBarM3
import com.tfg.supercomparator.ui.view.components.ModalBottomSheets

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun SearchScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        DockedSearchBarM3()
        Spacer(modifier = Modifier.height(25.dp))
        SupercMarketsIcons()
        ModalBottomSheets()
    }
}

@Composable
fun SupercMarketsIcons() {

    val padding = 22.dp
    val imageSize = 50.dp

    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(end = padding)) {
                Card(modifier = Modifier.clip(RoundedCornerShape(11.dp))) {
                    Image(
                        painter = painterResource(id = R.drawable.ahorramas),
                        contentDescription = null,
                        modifier = Modifier
                            .height(imageSize)
                    )
                }
            }
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(end = padding)) {
                Card(modifier = Modifier.clip(RoundedCornerShape(11.dp))) {
                    Image(
                        painter = painterResource(id = R.drawable.alcampo),
                        contentDescription = null,
                        modifier = Modifier
                            .height(imageSize)
                    )
                }
            }
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(end = padding)) {
                Image(
                    painter = painterResource(id = R.drawable.carrefour),
                    contentDescription = null,
                    modifier = Modifier
                        .height(imageSize)
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.dia),
                    contentDescription = null,
                    modifier = Modifier
                        .height(imageSize)
                        .background(Color.Transparent)
                )
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        Row(horizontalArrangement = Arrangement.Center) {
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(end = padding)) {
                Card(modifier = Modifier.clip(RoundedCornerShape(11.dp))) {
                    Image(
                        painter = painterResource(id = R.drawable.eroski),
                        contentDescription = null,
                        modifier = Modifier
                            .height(imageSize)
                    )
                }
            }
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(end = padding)) {
                Image(
                    painter = painterResource(id = R.drawable.hipercor),
                    contentDescription = null,
                    modifier = Modifier
                        .height(imageSize)
                )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.mercadona),
                    contentDescription = null,
                    modifier = Modifier
                        .height(imageSize)
                )
            }
        }
    }
}
