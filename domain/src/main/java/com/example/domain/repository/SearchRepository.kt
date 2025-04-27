package com.example.domain.repository

import com.example.domain.entities.CityHistoryEntity

interface SearchRepository {
    suspend fun addCityToHistory(cityName: String)
    suspend fun getSearchHistory(): List<CityHistoryEntity>
    suspend fun clearHistory()
}