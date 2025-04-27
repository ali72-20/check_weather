package com.example.checkweather.managers.search

import com.example.domain.entities.WeatherDataEntity

sealed class SearchFragmentStates {
    object LoadingState : SearchFragmentStates()
    data class SuccessState<T>(val data : T) : SearchFragmentStates()
    data class ErrorState(val errorMessage: String) : SearchFragmentStates()
}