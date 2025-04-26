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
    suspend fun getWeatherData(): WeatherUiState {
        return when (val response = weatherRepository.getWeatherData(
            WeatherRequestEntity(
                city = "Cairo",
                country = "EG",
                days = 8
            )
        )) {
            is Success<WeatherDataEntity> -> {
                WeatherUiState.SuccessState(weatherData = response.data)
            }
            is Failure<WeatherDataEntity> -> {
                println("Fail")
                WeatherUiState.ErrorState(response.exceptionMessage)
            }
        }
    }
}