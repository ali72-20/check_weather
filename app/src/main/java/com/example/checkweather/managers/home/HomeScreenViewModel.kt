package com.example.checkweather.managers.home


import android.net.ConnectivityManager
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.core.network.NetWorkStatues
import com.example.core.network.NetworkConnectivityObserver

import com.example.domain.core.api_result.Failure
import com.example.domain.core.api_result.Success
import com.example.domain.entities.WeatherDataEntity

import com.example.domain.entities.WeatherRequestEntity
import com.example.domain.repository.WeatherRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val netWorkConnectivityObserver: NetworkConnectivityObserver
) :
    ViewModel() {
    lateinit var weatherDataEntity: WeatherDataEntity

    var weatherState = mutableStateOf<WeatherUiState>(WeatherUiState.LoadingState)

     val networkState : StateFlow<NetWorkStatues> = netWorkConnectivityObserver.netWorkStatue.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        NetWorkStatues.Unavailable
    )

    suspend fun getWeatherData(cityName: String?): WeatherUiState {
        return when (val response = weatherRepository.getWeatherData(
            WeatherRequestEntity(
                city = cityName ?: "Cairo",
                days = 8
            )
        )) {
            is Success<WeatherDataEntity> -> {
                WeatherUiState.SuccessState(weatherData = response.data)
            }

            is Failure<WeatherDataEntity> -> {
                WeatherUiState.ErrorState(response.exceptionMessage)
            }
        }
    }

}



