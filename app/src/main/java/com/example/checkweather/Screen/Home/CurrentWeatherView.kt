package com.example.checkweather.Screen.Home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.checkweather.R
import com.example.checkweather.core.Dimens
import com.example.checkweather.ui.theme.White

@Composable
fun CurrentWeatherView(modifier: Modifier) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = Dimens.PaddingLarge, bottom = Dimens.PaddingMedium),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LocalWeatherData.current.data[0].dataTime?.let {
            Text(
                text = it,
                fontSize = 40.sp,
                color = White,
                fontWeight = FontWeight.Bold
            )
        }
        Box(
            Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Updated at ${LocalWeatherData.current.data[0].dataTime}",
            color = Color.White,
            fontSize = 16.sp
        )
        Icon(
            painterResource(R.drawable.icon),
            contentDescription = stringResource(R.string.temp_icon),
            tint = White
        )
        LocalWeatherData.current.data[0].weather?.description?.let {
            Text(
                text = it,
                color = White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Text(
            text = "${LocalWeatherData.current.data[0].temp}°C",
            color = White,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )

    }
}