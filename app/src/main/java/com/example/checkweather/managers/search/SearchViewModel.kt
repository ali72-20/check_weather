package com.example.checkweather.managers.search

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor() : ViewModel() {
    var cityName = mutableStateOf("")
    var errorMessage = mutableStateOf<String?>(null)
    fun validateCityName(): Boolean {
        return if (cityName.value.isEmpty() || cityName.value.isBlank()) {
            errorMessage.value = "Please Enter City Name"
            false
        } else {
            errorMessage.value = null;
            true
        }
    }
}