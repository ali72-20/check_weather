package com.example.data.data_source.local_data_source

import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val cityHistoryDao: CityHistoryDao) :
    LocalDataSource {
    override suspend fun saveCityHistory(cityName: String) {
        cityHistoryDao.insert(CityHistory(cityName = cityName))
    }

    override suspend fun getCityHistory(): List<CityHistory> {
        return cityHistoryDao.getAllCities()
    }

    override suspend fun clearHistory() {
        cityHistoryDao.deleteAll()
    }

}