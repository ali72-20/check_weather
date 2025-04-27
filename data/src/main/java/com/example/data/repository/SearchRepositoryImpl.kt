package com.example.data.repository

import com.example.data.data_source.local_data_source.LocalDataSource
import com.example.domain.entities.CityHistoryEntity
import com.example.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private  val localDataSource: LocalDataSource) : SearchRepository {
    override suspend fun addCityToHistory(cityName: String) {
        localDataSource.saveCityHistory(cityName)
    }

    override suspend fun getSearchHistory(): List<CityHistoryEntity> {
        return localDataSource.getCityHistory().map {city->
            city.toDomain()
        }
    }

    override suspend fun clearHistory() {
        localDataSource.clearHistory()
    }
}