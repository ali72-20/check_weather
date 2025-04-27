package com.example.checkweather.Screen.Home

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.checkweather.LocalWeatherData
import com.example.checkweather.R
import com.example.checkweather.core.ConstKey
import com.example.checkweather.core.Dimens
import com.example.checkweather.ui.theme.White
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

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
            Modifier.padding(bottom = Dimens.PaddingXSmall)
        )
        Text(
            text = "${stringResource(R.string.updated_at)} ${LocalWeatherData.current.data[0].dataTime}",
            color = Color.White,
            fontSize = 16.sp
        )
        WeatherIcon(iconCode = LocalWeatherData.current.data[0].weather?.icon ?: "")
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
        MinMaxTempRow()
    }
}

@Composable
fun MinMaxTempRow() {
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = Dimens.PaddingMedium, bottom = Dimens.PaddingMedium),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "${stringResource(R.string.min_temp)} ${LocalWeatherData.current.data[0].appMinTemp}°C",
            color = White,
            fontSize = 16.sp
        )
        Text(
            text = "${stringResource(R.string.max_temp)} ${LocalWeatherData.current.data[0].appMaxTemp}°C",
            color = White,
            fontSize = 16.sp
        )

    }
}

@Composable
fun WeatherIcon(iconCode: String) {
    Image(
        painter = rememberAsyncImagePainter(ConstKey.getIconUrl(iconCode)),
        contentDescription = "Weather Icon",
        modifier = Modifier
            .padding(bottom = Dimens.PaddingMedium)
            .size(100.dp)
    )
}