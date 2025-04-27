package com.example.checkweather.Screen.forcast

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.checkweather.LocalWeatherData
import com.example.checkweather.R

import com.example.checkweather.core.ConstKey
import com.example.checkweather.core.Dimens
import com.example.checkweather.managers.forcast.ForcastScreenActions
import com.example.checkweather.managers.forcast.ForcastScreenUIState
import com.example.checkweather.managers.forcast.FortCastViewModel
import com.example.domain.entities.DataItemEntity


@Composable
fun DaysForcastFragment(
    viewModel: FortCastViewModel = hiltViewModel<FortCastViewModel>(),
    navController: NavController
) {
    val uiState by viewModel.forcastUiState.collectAsState()
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val cityName = savedStateHandle?.get<String>(ConstKey.cityName)
    LaunchedEffect(Unit) {
        viewModel.doAction(ForcastScreenActions.GetForcastDataAction(cityName = cityName))
    }
    DaysForcastScreenContent(
        uiState, viewModel, navController, cityName = cityName ?: stringResource(
            R.string.cairo
        )
    )
}


@Composable
fun DaysForcastScreenContent(
    uiState: ForcastScreenUIState,
    viewModel: FortCastViewModel,
    navController: NavController,
    cityName: String
) {


    when (uiState) {
        is ForcastScreenUIState.ForcastScreenLoadingState -> {
            ForeCastLoadingView()
        }

        is ForcastScreenUIState.ForcastScreenSuccessState -> {
            ForecastFragmentBody(
                navController = navController,
                uiState.weatherData,
                cityName = cityName
            )
        }

        is ForcastScreenUIState.ForcastScreenErrorState -> {
            ForecastErrorView(uiState.errorMessage, viewModel)
        }
    }
}

@Composable
fun DaysListView(days: List<DataItemEntity>) {
    LazyColumn(
        modifier = Modifier
            .padding(Dimens.PaddingSmall)
    ) {
        items(days) { item ->
            DaysForcastListItem(item)
        }
    }
}

@Composable
fun WeatherIcon(iconCode: String) {
    Image(
        painter = rememberAsyncImagePainter(ConstKey.getIconUrl(iconCode)),
        contentDescription = stringResource(R.string.weather_icon),
        modifier = Modifier
            .padding(bottom = Dimens.PaddingMedium)
            .size(80.dp)
    )
}

