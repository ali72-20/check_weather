package com.example.checkweather.managers.search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.entities.CityHistoryEntity
import com.example.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {
    var cityName = mutableStateOf("")
    var errorMessage = mutableStateOf<String?>(null)
    var cityHistory = mutableStateOf<List<CityHistoryEntity>>(emptyList())
    fun validateCityName(): Boolean {
        return if (cityName.value.isEmpty() || cityName.value.isBlank()) {
            errorMessage.value = "Please Enter City Name"
            false
        } else {
            errorMessage.value = null;
            saveCityToHistory()
            true
        }
    }

    private fun saveCityToHistory(){
        viewModelScope.launch {
            searchRepository.addCityToHistory(cityName = cityName.value)
        }
    }

    suspend fun getHistory(): List<CityHistoryEntity>{
        cityHistory = mutableStateOf(searchRepository.getSearchHistory())
        return cityHistory.value
    }
}