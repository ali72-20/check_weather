package com.example.data.api
import com.example.data.api.core.ApiConstants
import com.example.data.api.models.WeatherDataResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherServices {
    @GET(ApiConstants.FORECAST_DAILY_END_POINT)
    suspend fun getWeatherData(
        @Query(ApiConstants.CITY_KEY) city: String,
        @Query(ApiConstants.DAYS_KEY) days: Int,
        @Query(ApiConstants.KEY) apiKey: String
    ): WeatherDataResponse
}