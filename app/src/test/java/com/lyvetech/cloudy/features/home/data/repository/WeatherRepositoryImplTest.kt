package com.lyvetech.cloudy.features.home.data.repository

import com.lyvetech.cloudy.data.local.WeatherLocalDataSource
import com.lyvetech.cloudy.data.remote.WeatherRemoteDataSource
import com.lyvetech.cloudy.data.repository.WeatherRepositoryImpl
import com.lyvetech.cloudy.domain.model.Weather
import com.lyvetech.cloudy.domain.util.Resource
import com.lyvetech.cloudy.utils.dummyLocation
import com.lyvetech.cloudy.utils.fakeExpiredWeatherEntity
import com.lyvetech.cloudy.utils.fakeExpiredWeatherInfo
import com.lyvetech.cloudy.utils.fakeNotExpiredWeatherEntity
import com.lyvetech.cloudy.utils.fakeNotExpiredWeatherInfo
import com.lyvetech.cloudy.utils.fakeWeatherDto
import com.lyvetech.cloudy.utils.`should be`
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class WeatherRepositoryImplTest {

    private var weatherRemoteDataSource = mockk<WeatherRemoteDataSource>()
    private var weatherLocalDataSource = mockk<WeatherLocalDataSource>()

    private lateinit var systemUnderTest: WeatherRepositoryImpl

    @Before
    fun setUp() {
        systemUnderTest = WeatherRepositoryImpl(
            weatherRemoteDataSource,
            weatherLocalDataSource
        )
    }

    @Test
    fun `check that getWeather with data is not expired fetches successfully from the local source`() =
        runBlocking {
            coEvery {
                weatherLocalDataSource.getWeather()
            } returns fakeNotExpiredWeatherEntity

            val result = mutableListOf<Resource<Weather>>()
            systemUnderTest.getWeather(dummyLocation).collect { result.add(it) }

            result.first() `should be` fakeNotExpiredWeatherInfo
            coVerify(exactly = 1) { weatherLocalDataSource.getWeather() }
            coVerify(exactly = 0) { weatherRemoteDataSource.getWeather(any()) }
            coVerify(exactly = 0) { weatherLocalDataSource.deleteAllWeather() }
            coVerify(exactly = 0) { weatherLocalDataSource.insertWeather(any()) }
        }


    @Test
    fun `check that getWeather with data is expired fetches successfully from the remote source`() =
        runBlocking {

            coEvery {
                weatherLocalDataSource.getWeather()
            } returns fakeExpiredWeatherEntity

            coEvery {
                weatherRemoteDataSource.getWeather(dummyLocation)
            } returns Resource.Success(fakeWeatherDto)

            coEvery {
                weatherLocalDataSource.deleteAllWeather()
            } just Runs

            coEvery {
                weatherLocalDataSource.insertWeather(any())
            } just Runs

            val result = mutableListOf<Resource<Weather>>()
            systemUnderTest.getWeather(dummyLocation).collect { result.add(it) }

            result.first().data `should be` fakeExpiredWeatherInfo
            coVerify(exactly = 2) { weatherLocalDataSource.getWeather() }
            coVerify(exactly = 1) { weatherRemoteDataSource.getWeather(any()) }
            coVerify(exactly = 1) { weatherLocalDataSource.deleteAllWeather() }
            coVerify(exactly = 1) { weatherLocalDataSource.insertWeather(any()) }
        }
}