package com.example.data.data_source.local_data_source

interface LocalDataSource {
    suspend fun saveCityHistory(cityName: String)
    suspend fun getCityHistory():List<CityHistory>
}