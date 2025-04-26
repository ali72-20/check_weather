package com.example.checkweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.checkweather.core.Dimens
import com.example.checkweather.managers.home.HomeScreenViewModel
import com.example.checkweather.managers.home.WeatherUiState
import com.example.checkweather.ui.theme.CheckWeatherTheme
import com.example.checkweather.ui.theme.White
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CheckWeatherTheme {
                HomeScreenContent()
            }
        }
    }
}

@Composable
fun HomeScreenContent(
    viewModel: HomeScreenViewModel = hiltViewModel<HomeScreenViewModel>()
) {
    var weatherState by remember {
        mutableStateOf<WeatherUiState>(WeatherUiState.LoadingState)
    }
    LaunchedEffect(Unit) {
        val result = viewModel.getWeatherData()
        weatherState = result
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->
        when (weatherState) {
            is WeatherUiState.LoadingState -> {
                LoadingView()
            }

            is WeatherUiState.SuccessState -> {
                HomeScreenBody(modifier = Modifier.padding(paddingValues))
            }

            is WeatherUiState.ErrorState -> {
                Text(text = (weatherState as WeatherUiState.ErrorState).errorMessage)
            }
        }

    }
}


@Composable
fun HomeScreenBody(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.city_view), contentScale = ContentScale.Crop)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp, start = 16.dp)

        ) {
            LocationRowView()
            CurrentWeatherView(modifier)
            WeatherDetailsRow()
        }
    }
}

@Composable
fun LocationRowView() {
    Row(modifier = Modifier.wrapContentSize()) {
        Icon(
            painterResource(R.drawable.location_icon),
            contentDescription = stringResource(R.string.location_icon),
            tint = Color.White
        )
        Text(
            text = stringResource(R.string.paris),
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

@Composable
fun CurrentWeatherView(modifier: Modifier) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = Dimens.PaddingLarge, bottom = Dimens.PaddingMedium),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "June 07",
            fontSize = 40.sp,
            color = White,
            fontWeight = FontWeight.Bold
        )
        Box(
            Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Updated at 6/7/2025 10:00PM",
            color = Color.White,
            fontSize = 16.sp
        )
        Icon(
            painterResource(R.drawable.icon),
            contentDescription = stringResource(R.string.temp_icon),
            tint = White
        )
        Text(
            text = "Clear",
            color = White,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "25°C",
            color = White,
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold
        )

    }
}

@Composable
fun WeatherDetailsRow() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        WeatherDetailsRowItem(R.drawable.search_icon, "HUMIDITY", "56%")
        WeatherDetailsRowItem(R.drawable.group, "WIND", "4.63km/h")
        WeatherDetailsRowItem(R.drawable.icon_feels_like, "FEELS LIKE", "22°")
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

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Loading...", color = MaterialTheme.colorScheme.primary)
    }
}

@Composable
fun ForecastDaysView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) {
        LazyRow() {
        }
    }
}

@Composable
fun ForecastDayViewItem(day: String, temp: String, wind: String) {

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenContentPreview() {
    HomeScreenContent()
}