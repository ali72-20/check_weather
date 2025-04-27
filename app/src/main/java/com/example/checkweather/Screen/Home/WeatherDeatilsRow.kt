package com.example.checkweather.Screen.Home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.checkweather.LocalWeatherData
import com.example.checkweather.R
import com.example.checkweather.ui.theme.White

@Composable
fun WeatherDetailsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        WeatherDetailsRowItem(
            R.drawable.group,
            stringResource(R.string.humidity),
            "${LocalWeatherData.current.data[0].rh}%"
        )
        WeatherDetailsRowItem(
            R.drawable.icon_wind,
            stringResource(R.string.wind),
            "${LocalWeatherData.current.data[0].windSpd}km/h"
        )
        WeatherDetailsRowItem(
            R.drawable.icon_feels_like,
            stringResource(R.string.feels_like),
            "22°"
        )
    }
}

@Composable
fun WeatherDetailsRowItem(icon: Int, text: String, value: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(painterResource(icon), contentDescription = "de", tint = White)
        Text(
            text = text,
            style = TextStyle(
                color = White,
                fontSize = 14.sp,
            )
        )
        Text(
            text = value,
            color = White,
            fontSize = 14.sp,
        )
    }
}