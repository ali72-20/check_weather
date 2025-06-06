package com.example.checkweather.Screen.Home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.checkweather.R

@Composable
fun HomeLoadingView() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.bg), contentScale = ContentScale.FillBounds)
            .height(200.dp)
    ) {
        CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
    }
}