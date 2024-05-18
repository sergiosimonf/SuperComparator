package com.tfg.supercomparator.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.tfg.supercomparator.R
import com.tfg.supercomparator.domain.modules.analytics.AnalyticsManager
import com.tfg.supercomparator.domain.modules.auth.AuthManager

@Composable
fun UserScreen(analytics: AnalyticsManager, auth: AuthManager, navigation: NavController) {

    val user = auth.getCurrentUser()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (user?.photoUrl != null) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user?.photoUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Imagen",
                placeholder = painterResource(id = R.drawable.profile),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(40.dp)
            )
        } else {
            Image(
                painter = painterResource(R.drawable.profile),
                contentDescription = "Foto de perfil por defecto",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .size(40.dp)
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = if (!user?.displayName.isNullOrEmpty()) "Hola ${user?.displayName}" else "Bienvenido/a",
                fontSize = 20.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = if (!user?.email.isNullOrEmpty()) "${user?.email}" else "Anónimo",
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}