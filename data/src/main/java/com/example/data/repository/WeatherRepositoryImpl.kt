package com.example.data.repository

import com.example.data.api.models.WeatherRequestModel
import com.example.data.data_source.remote_data_source.RemoteDataSource
import com.example.domain.core.api_result.ApiResult
import com.example.data.api.core.executeApi
import com.example.domain.entities.WeatherDataEntity
import com.example.domain.entities.WeatherRequestEntity
import com.example.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) :
    WeatherRepository {
    override suspend fun getWeatherData(weatherRequestEntity: WeatherRequestEntity): ApiResult<WeatherDataEntity> {
        return executeApi {
            val response = remoteDataSource.getWeatherData(
                weatherRequestModel = WeatherRequestModel.fromDomain(weatherRequestEntity)
            )
             response.toDomain()
        }
    }
}