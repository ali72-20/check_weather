package com.example.domain.repository

import com.example.domain.core.api_result.Failure
import com.example.domain.core.api_result.Success
import com.example.domain.entities.WeatherDataEntity
import com.example.domain.entities.WeatherRequestEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherRepositoryTest {

    private lateinit var weatherRepository: WeatherRepository

    @Before
    fun setUp() {
        weatherRepository = mock(WeatherRepository::class.java)
    }

    @Test
    fun testGetWeatherDataReturnsSuccess() = runTest {
        val request = WeatherRequestEntity(city = "Cairo", days = 7)
        val expectedResponse = WeatherDataEntity(
            cityName = "Cairo",
            timeZone = "EG",
            data = listOf()
        )

        whenever(weatherRepository.getWeatherData(request)).thenReturn(Success(expectedResponse))

        val result = weatherRepository.getWeatherData(request)

        assertTrue(result is Success)
        assertEquals(expectedResponse, (result as Success).data)
    }

    @Test
    fun testGetWeatherDataReturnsError() = runTest {

        val request = WeatherRequestEntity("Cairo", days = 7)
        val errorMessage = "Network Error"

        whenever(weatherRepository.getWeatherData(request)).thenReturn(Failure(errorMessage))


        val result = weatherRepository.getWeatherData(request)

        assertTrue(result is Failure)
        assertEquals(errorMessage, (result as Failure).exceptionMessage)
    }
}
