package com.example.checkweather.managers.search
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    var searchFragmentStates = mutableStateOf<SearchFragmentStates>(SearchFragmentStates.LoadingState)
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

    suspend fun getHistory():SearchFragmentStates{
        cityHistory = mutableStateOf(searchRepository.getSearchHistory())
        return SearchFragmentStates.SuccessState<List<CityHistoryEntity>>(data = cityHistory.value)
    }

    suspend fun clearHistory(){
        searchRepository.clearHistory()
        cityHistory = mutableStateOf(emptyList())
        searchFragmentStates.value = SearchFragmentStates.SuccessState<List<CityHistoryEntity>>(data = cityHistory.value)
    }
}