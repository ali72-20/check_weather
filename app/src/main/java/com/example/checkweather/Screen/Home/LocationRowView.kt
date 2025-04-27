package com.example.checkweather.Screen.Home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.checkweather.R
import com.example.checkweather.ui.theme.White

@Composable
fun LocationRowView() {
    Row(modifier = Modifier.wrapContentSize()) {
        Icon(
            painterResource(R.drawable.location_icon),
            contentDescription = stringResource(R.string.location_icon),
            tint = Color.White
        )
        Text(
            text = LocalWeatherData.current.cityName,
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 4.dp, top = 4.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            contentDescription = stringResource(R.string.search_icon),
            tint = White,
            painter = painterResource(R.drawable.search_icon),
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}


