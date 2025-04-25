package com.example.domain.repository

import com.example.domain.core.api_result.ApiResult
import com.example.domain.entities.WeatherEntity
import com.example.domain.entities.WeatherRequestEntity


interface WeatherRepository {
    suspend fun getWeatherData(weatherRequestEntity: WeatherRequestEntity): ApiResult<WeatherEntity>
}