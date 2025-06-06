package com.example.domain.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import com.example.domain.entities.CityHistoryEntity


@OptIn(ExperimentalCoroutinesApi::class)
class SearchRepositoryTest {

    @Mock
    private lateinit var searchRepository: SearchRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun testAddCityToHistory() = runTest {
        val cityName = "Cairo"
        searchRepository.addCityToHistory(cityName)
        Mockito.verify(searchRepository).addCityToHistory(cityName)
    }

    @Test
    fun testGetSearchHistory() = runTest {

        val cityHistory = listOf(
            CityHistoryEntity(1,"Cairo"),
            CityHistoryEntity(2,"Pairs")
        )
        whenever(searchRepository.getSearchHistory()).thenReturn(cityHistory)


        val result = searchRepository.getSearchHistory()


        assertEquals(cityHistory, result)
    }
}
