package com.example.checkweather.Screen.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
import com.example.domain.entities.DataItemEntity
import kotlinx.coroutines.launch


@Composable
fun HomeScreenContent(
    viewModel: HomeScreenViewModel = hiltViewModel<HomeScreenViewModel>(),
    navController: NavController
) {
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val cityName = savedStateHandle?.get<String>(ConstKey.cityName)
    LaunchedEffect(Unit) {
        val result = viewModel.getWeatherData(cityName = cityName)
        viewModel.weatherState.value = result
    }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->
        when (viewModel.weatherState.value) {
            is WeatherUiState.LoadingState -> {
                HomeLoadingView()
            }

            is WeatherUiState.SuccessState -> {
                val weatherData =
                    (viewModel.weatherState.value as WeatherUiState.SuccessState).weatherData
                CompositionLocalProvider(LocalWeatherData provides weatherData) {
                    HomeScreenBody(
                        modifier = Modifier.padding(paddingValues),
                        navController = navController
                    )
                }
            }

            is WeatherUiState.ErrorState -> {
                ErrorViewHome(
                    viewModel = viewModel,
                    errorMessage = (viewModel.weatherState as WeatherUiState.ErrorState).errorMessage,
                    cityName = cityName ?: stringResource(R.string.cairo)
                )
            }
        }
    }
}

@Composable
fun HomeScreenBody(modifier: Modifier = Modifier, navController: NavController) {
    val cityNme = LocalWeatherData.current.cityName
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.bg), contentScale = ContentScale.Crop)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp, start = 16.dp)

        ) {
            LocationRowView(navController = navController)
            CurrentWeatherView(modifier)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimens.PaddingXXSmall),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    stringResource(R.string._7_days_forecast),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    stringResource(R.string.view_all),
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable {
                        navController.navigate(AppRoutesManger.DaysForcastScreen.passCity(cityName = cityNme))
                    }
                )
            }
            LazyRow(modifier = Modifier.wrapContentSize()) {
                items(3) { index ->
                    ForecastItem(LocalWeatherData.current.data[index])
                }
            }
        }
    }
}

@Composable
fun ForecastItem(dataItemEntity: DataItemEntity) {
    Box(
        modifier = Modifier
            .padding(Dimens.PaddingXXSmall)
            .background(
                color = Color.White.copy(alpha = 0.3f),
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Column(
            modifier = Modifier.padding(Dimens.PaddingMedium),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = dataItemEntity.dataTime ?: "",
                color = Color.White
            )
            WeatherIcon(iconCode = dataItemEntity.weather?.icon ?: "")
            Text(
                text = "${dataItemEntity.temp}°C",
                color = Color.White
            )
        }
    }
}

@Composable
fun ErrorViewHome(errorMessage: String, viewModel: HomeScreenViewModel, cityName: String) {
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(R.drawable.warning),
            contentDescription = stringResource(R.string.waring)
        )
        Spacer(modifier = Modifier.heightIn(Dimens.PaddingXXSmall))
        Text(
            textAlign = TextAlign.Center,
            text = errorMessage,
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            modifier = Modifier.padding(Dimens.PaddingMedium)
        )
        Spacer(modifier = Modifier.heightIn(Dimens.PaddingXXSmall))
        Image(
            painterResource(R.drawable.reload),
            contentDescription = stringResource(R.string.reload),
            modifier = Modifier.clickable {
                coroutineScope.launch {
                    val result = viewModel.getWeatherData(cityName = cityName)
                    viewModel.weatherState.value = result
                }
            },
        )
    }
}