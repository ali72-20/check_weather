package com.example.checkweather.Screen.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
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
                HomeLoadingView()
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
                ErrorViewHome(viewModel = viewModel, errorMessage = (weatherState as WeatherUiState.ErrorState).errorMessage)
            }
        }
    }
}

@Composable
fun HomeScreenBody(modifier: Modifier = Modifier, navController: NavController) {
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
            Button(
                modifier = Modifier.padding(Dimens.PaddingLarge),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary),
                onClick = {
                navController.navigate(AppRoutesManger.DaysForcastScreen.route)
            }) {
                Text(text = stringResource(R.string.forecast_screen))
            }
        }
    }
}
@Composable
fun ErrorViewHome(errorMessage: String, viewModel: HomeScreenViewModel) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
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

            },
        )
    }
}