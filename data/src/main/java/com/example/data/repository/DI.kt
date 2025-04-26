package com.example.data.repository

import com.example.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class DI {

    @Binds
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository
}
