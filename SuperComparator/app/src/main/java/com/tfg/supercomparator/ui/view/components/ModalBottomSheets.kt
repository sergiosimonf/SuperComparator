package com.tfg.supercomparator.ui.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tfg.supercomparator.R
import kotlinx.coroutines.launch

@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalBottomSheets() {
    val sheetState = rememberModalBottomSheetState()
    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }
    val scaffoldState = rememberBottomSheetScaffoldState()

    var sheetPeekHeight by remember { mutableStateOf(340.dp) }
    var heigthMax by remember { mutableStateOf(290.dp) }
    var heigthMin by remember { mutableStateOf(0.dp) }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .heightIn(max = heigthMax, min = heigthMin)
                    .verticalScroll(rememberScrollState())
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_v1),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.logo_v1),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.logo_v1),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.logo_v1),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.logo_v1),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.logo_v1),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.logo_v1),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.logo_v1),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.logo_v1),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.logo_v1),
                    contentDescription = null
                )
                Image(
                    painter = painterResource(id = R.drawable.logo_v1),
                    contentDescription = null
                )
            }
        },
        sheetPeekHeight = sheetPeekHeight,
        containerColor  = Color.Transparent
    ) {

    }
}