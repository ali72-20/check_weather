package com.example.checkweather.managers.home

import com.example.domain.entities.WeatherDataEntity

sealed class WeatherUiState {
     object LoadingState : WeatherUiState()
    data class SuccessState(val weatherData: WeatherDataEntity) : WeatherUiState()
    data class ErrorState(val errorMessage: String) : WeatherUiState()
}
