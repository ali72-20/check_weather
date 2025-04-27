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
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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

        WeatherIcon(iconCode = LocalWeatherData.current.data[0].weather?.icon ?: "")
        Box(
            modifier = Modifier
                .padding(Dimens.PaddingSmall)
                .background(
                    color = Color.White.copy(alpha = 0.3f),
                    shape = RoundedCornerShape(12.dp)
                )
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Box(
                    Modifier.padding(bottom = Dimens.PaddingXSmall)
                )
                LocalWeatherData.current.data[0].dataTime?.let {
                    Text(
                        text = "ToDay $it",
                        fontSize = 24.sp,
                        color = White,
                        fontWeight = FontWeight.Bold
                    )
                }
                Box(
                    Modifier.padding(bottom = Dimens.PaddingXSmall)
                )
                Text(
                    text = "${LocalWeatherData.current.data[0].temp}°C",
                    color = White,
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Bold
                )
                LocalWeatherData.current.data[0].weather?.description?.let {
                    Text(
                        text = it,
                        color = White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Box(
                    Modifier.padding(bottom = Dimens.PaddingXSmall)
                )
                MinMaxTempRow()
                Box(
                    Modifier.padding(bottom = Dimens.PaddingXSmall)
                )
                WeatherDetailsRow()
            }
        }
    }
}

@Composable
fun MinMaxTempRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimens.PaddingMedium, bottom = Dimens.PaddingMedium),
        horizontalArrangement = Arrangement.SpaceEvenly
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
        contentDescription = stringResource(R.string.weather_icon),
        modifier = Modifier
            .padding(bottom = Dimens.PaddingMedium)
            .size(200.dp)
    )
}