package com.example.checkweather.managers.home


import androidx.lifecycle.ViewModel

import com.example.domain.core.api_result.Failure
import com.example.domain.core.api_result.Success
import com.example.domain.entities.WeatherDataEntity

import com.example.domain.entities.WeatherRequestEntity
import com.example.domain.repository.WeatherRepository

import dagger.hilt.android.lifecycle.HiltViewModel

import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {
    lateinit var weatherDataEntity: WeatherDataEntity
    suspend fun getWeatherData(cityName: String?): WeatherUiState {
        return when (val response = weatherRepository.getWeatherData(
            WeatherRequestEntity(
                city = cityName?:"Cairo",
                days = 8
            )
        )) {
            is Success<WeatherDataEntity> -> {
                WeatherUiState.SuccessState(weatherData = response.data)
            }

            is Failure<WeatherDataEntity> -> {
                WeatherUiState.ErrorState(response.exceptionMessage)
            }
        }
    }

}



