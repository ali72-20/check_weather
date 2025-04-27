package com.example.data.data_source.local_data_source

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [CityHistory::class], version = 1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun cityHistoryDao(): CityHistoryDao
}