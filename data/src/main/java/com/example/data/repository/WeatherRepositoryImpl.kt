package com.example.data.repository
import com.example.data.api.models.WeatherRequestModel
import com.example.data.data_source.remote_data_source.RemoteDataSource
import com.example.domain.core.api_result.ApiResult
import com.example.domain.core.api_result.executeApi
import com.example.domain.entities.WeatherEntity
import com.example.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val remoteDataSource: RemoteDataSource) : WeatherRepository {
    override suspend fun getWeatherData(): ApiResult<WeatherEntity> {
        return executeApi<WeatherEntity> {
            val response = remoteDataSource.getWeatherData(WeatherRequestModel(
                city = "city",
                country = "country",
                days = 7
            ))
           response.toDomain()
        }
    }
}