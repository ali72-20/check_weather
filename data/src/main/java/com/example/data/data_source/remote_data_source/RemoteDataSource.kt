package com.example.data.data_source.remote_data_source

import com.example.data.api.models.WeatherDataResponse
import com.example.data.api.models.WeatherRequestModel

interface RemoteDataSource {
    suspend fun getWeatherData(weatherRequestModel: WeatherRequestModel):WeatherDataResponse
}