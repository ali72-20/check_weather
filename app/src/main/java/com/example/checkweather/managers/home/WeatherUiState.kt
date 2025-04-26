package com.example.checkweather.managers.home

import com.example.domain.entities.WeatherDataEntity

abstract class WeatherUiState {}
class LoadingState : WeatherUiState()
class SuccessState(val weatherData: WeatherDataEntity) : WeatherUiState()
class ErrorState(val errorMessage: String) : WeatherUiState()