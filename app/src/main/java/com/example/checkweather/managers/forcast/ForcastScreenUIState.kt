package com.example.checkweather.managers.forcast

import com.example.domain.entities.WeatherDataEntity

sealed class ForcastScreenUIState {
     object ForcastScreenLoadingState : ForcastScreenUIState()
    data class ForcastScreenSuccessState(
        val weatherData: WeatherDataEntity
    ) : ForcastScreenUIState()
    data class ForcastScreenErrorState(
        val errorMessage: String
    ) : ForcastScreenUIState()
}