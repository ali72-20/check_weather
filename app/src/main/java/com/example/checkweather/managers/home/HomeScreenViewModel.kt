package com.example.checkweather.managers.home

import com.example.domain.core.api_result.Failure
import com.example.domain.core.api_result.Success
import com.example.domain.entities.WeatherDataEntity

import com.example.domain.entities.WeatherRequestEntity
import com.example.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val weatherRepository: WeatherRepository) {
    suspend fun getWeatherData() {
        when (val response = weatherRepository.getWeatherData(
            WeatherRequestEntity(
                city = "Cairo",
                country = "EG",
                days = 8
            )
        )) {
            is Success<WeatherDataEntity> -> {
                println(response.data)
                print("Success");
            }

            is Failure<WeatherDataEntity> -> {
                println("Fail")
            }

            else -> {
                println("Error")
            }
        }
    }
}