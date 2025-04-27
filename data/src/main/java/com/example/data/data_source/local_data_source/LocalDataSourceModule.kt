package com.example.data.data_source.local_data_source

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context = context,
            AppDataBase::class.java,
            "weather_database"
        ).build()
    }

    @Provides
    fun provideCityHistoryDao(dataBase: AppDataBase): CityHistoryDao {
        return dataBase.cityHistoryDao()
    }

}

