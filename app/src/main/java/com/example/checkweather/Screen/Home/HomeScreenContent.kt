package com.example.checkweather.Screen.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.checkweather.LocalWeatherData
import com.example.checkweather.R
import com.example.checkweather.core.AppRoutesManger
import com.example.checkweather.core.ConstKey
import com.example.checkweather.core.Dimens
import com.example.checkweather.managers.home.HomeScreenViewModel
import com.example.checkweather.managers.home.WeatherUiState
import com.example.checkweather.ui.theme.Blue

@Composable
fun HomeScreenContent(
    viewModel: HomeScreenViewModel = hiltViewModel<HomeScreenViewModel>(),
    navController: NavController
) {
    var weatherState by remember {
        mutableStateOf<WeatherUiState>(WeatherUiState.LoadingState)
    }
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val cityName = savedStateHandle?.get<String>(ConstKey.cityName)
    LaunchedEffect(Unit) {
        val result = viewModel.getWeatherData(cityName = cityName)
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
                        modifier = Modifier.padding(paddingValues,),
                        navController = navController
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
fun HomeScreenBody(modifier: Modifier = Modifier, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize().background(MaterialTheme.colorScheme.primary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp, start = 16.dp)

        ) {
            LocationRowView(navController = navController)
            CurrentWeatherView(modifier)
            WeatherDetailsRow()
            Button(
                modifier = Modifier.padding(Dimens.PaddingLarge),
                onClick = {
                navController.navigate(AppRoutesManger.DaysForcastScreen.route)
            }) {
                Text(text = "Forcast screen")
            }
        }
    }
}
