package com.example.data.repository

import com.example.data.api.models.WeatherDataResponse
import com.example.data.api.models.WeatherRequestModel
import com.example.data.data_source.remote_data_source.RemoteDataSource
import com.example.domain.core.api_result.Failure
import com.example.domain.core.api_result.Success
import com.example.domain.entities.WeatherDataEntity
import com.example.domain.entities.WeatherRequestEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherRepositoryImplTest {

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    private lateinit var weatherRepositoryImpl: WeatherRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        weatherRepositoryImpl = WeatherRepositoryImpl(remoteDataSource)
    }

    @Test
    fun `test getWeatherData returns success result`() = runTest {
        val weatherRequestEntity = WeatherRequestEntity("Cairo", days = 7)
        val mockWeatherDataEntity = WeatherDataEntity("cairo", "EG", listOf())
        val mockWeatherDataResponse =
            WeatherDataResponse(cityName = "cairo", timezone = "EG", data = listOf())

        whenever(remoteDataSource.getWeatherData(WeatherRequestModel.fromDomain(weatherRequestEntity)))
            .thenReturn(mockWeatherDataResponse)


        val result = weatherRepositoryImpl.getWeatherData(weatherRequestEntity)


        assertTrue(result is Success)
        assertEquals((result as Success).data, mockWeatherDataEntity)
    }

    @Test
    fun `test getWeatherData returns error result`() = runTest {
        val weatherRequestEntity = WeatherRequestEntity("Cairo", days = 7)
        val errorMessage = "Oops! Something went wrong. Our team is working on it"

        whenever(remoteDataSource.getWeatherData(WeatherRequestModel.fromDomain(weatherRequestEntity)))
            .thenThrow(RuntimeException(errorMessage))

        val result = weatherRepositoryImpl.getWeatherData(weatherRequestEntity)


        assertTrue(result is Failure)
        assertEquals((result as Failure).exceptionMessage, errorMessage)
    }
}
