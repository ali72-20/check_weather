package com.example.domain.repository

import com.example.domain.core.api_result.ApiResult
import com.example.domain.entities.WeatherEntity


interface WeatherRepository {
    suspend fun getWeatherData(): ApiResult<WeatherEntity>
}