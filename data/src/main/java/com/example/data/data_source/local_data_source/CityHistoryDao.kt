package com.example.data.data_source.local_data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface CityHistoryDao{

    @Insert
    suspend fun insert(cityHistory: CityHistory)

    @Query("SELECT * FROM city_history")
    suspend fun getAllCities(): List<CityHistory>


    @Query("DELETE FROM CITY_HISTORY")
    suspend fun deleteAll()
}