package com.example.checkweather.Screen.Home

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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.checkweather.R
import com.example.checkweather.managers.home.HomeScreenViewModel
import com.example.checkweather.managers.home.WeatherUiState
import com.example.checkweather.ui.theme.CheckWeatherTheme
import com.example.checkweather.ui.theme.White
import com.example.domain.entities.WeatherDataEntity
import dagger.hilt.android.AndroidEntryPoint

val LocalWeatherData = staticCompositionLocalOf<WeatherDataEntity> {
    error("No weather data found! Did you forget to provide it?")
}

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
                val weatherData = (weatherState as WeatherUiState.SuccessState).weatherData
                CompositionLocalProvider(LocalWeatherData provides weatherData) {
                    HomeScreenBody(
                        modifier = Modifier.padding(paddingValues),
                    )
                }
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

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun HomeScreenContentPreview() {
//    HomeScreenContent()
//}