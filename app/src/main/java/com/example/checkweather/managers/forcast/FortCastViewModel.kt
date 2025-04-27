package com.example.checkweather.managers.forcast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.core.api_result.Failure
import com.example.domain.core.api_result.Success
import com.example.domain.entities.WeatherRequestEntity
import com.example.domain.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

@HiltViewModel
class FortCastViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    private val _forcastScreenUIState =
        MutableStateFlow<ForcastScreenUIState>(ForcastScreenUIState.ForcastScreenLoadingState)
    var forcastUiState = _forcastScreenUIState.asStateFlow()


    private suspend fun getForcastData(cityName: String?) {
        val city = cityName ?: "Cairo"
        val result = weatherRepository.getWeatherData(
            WeatherRequestEntity(
                city = city,
                days = 7
            )
        )

        when (result) {
            is Success -> {
                _forcastScreenUIState.value = ForcastScreenUIState.ForcastScreenSuccessState(result.data)
            }

            is Failure -> {
                _forcastScreenUIState.value =
                    ForcastScreenUIState.ForcastScreenErrorState(result.exceptionMessage)

            }
        }
    }

    fun doAction(forcastScreenActions: ForcastScreenActions) {
        when (forcastScreenActions) {
            is ForcastScreenActions.GetForcastDataAction -> {
                viewModelScope.launch {
                    getForcastData(forcastScreenActions.cityName)
                }
            }
        }
    }
}