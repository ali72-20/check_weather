package com.example.data.data_source.remote_data_source

import com.example.data.api.WeatherServices
import com.example.data.api.core.ApiConstants
import com.example.data.api.models.WeatherDataResponse
import com.example.data.api.models.WeatherRequestModel
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val weatherServices: WeatherServices) :
    RemoteDataSource {
    override suspend fun getWeatherData(weatherRequestModel: WeatherRequestModel): WeatherDataResponse {
        return weatherServices.getWeatherData(
            city = weatherRequestModel.city,
            days = weatherRequestModel.days,
            apiKey = ApiConstants.API_KEY
        )
    }

}