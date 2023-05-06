package com.lyvetech.cloudy.features.home.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lyvetech.cloudy.domain.util.Resource
import com.lyvetech.cloudy.data.local.WeatherLocalDataSource
import com.lyvetech.cloudy.data.remote.WeatherRemoteDataSource
import com.lyvetech.cloudy.data.repository.WeatherRepositoryImpl
import com.lyvetech.cloudy.utils.*
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class WeatherRepositoryImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var systemUnderTest: WeatherRepositoryImpl

    @MockK
    private lateinit var weatherRemoteDataSource: WeatherRemoteDataSource

    @MockK
    private lateinit var weatherLocalDataSource: WeatherLocalDataSource

    private val mainThreadSurrogate = newSingleThreadContext("threading")


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        Dispatchers.setMain(mainThreadSurrogate)

        systemUnderTest = WeatherRepositoryImpl(
            weatherRemoteDataSource,
            weatherLocalDataSource,
            Dispatchers.Main
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `check that getWeather with data is not expired fetches successfully from the local source`() {
        runBlocking {
            coEvery {
                weatherLocalDataSource.getWeather()
            } returns fakeNotExpiredWeatherEntity

            val result = systemUnderTest.getWeatherInfo(dummyLocation)

            result.data `should be` fakeNotExpiredWeatherInfo
            coVerify(exactly = 1) { weatherLocalDataSource.getWeather() }
            coVerify(exactly = 0) { weatherRemoteDataSource.getWeather(any()) }
            coVerify(exactly = 0) { weatherLocalDataSource.deleteAllWeather() }
            coVerify(exactly = 0) { weatherLocalDataSource.insertWeather(any()) }
        }
    }

    @Test
    fun `check that getWeather with data is expired fetches successfully from the remote source`() {
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

            val result = systemUnderTest.getWeatherInfo(dummyLocation)

            result.data `should be` fakeExpiredWeatherInfo
            coVerify(exactly = 2) { weatherLocalDataSource.getWeather() }
            coVerify(exactly = 1) { weatherRemoteDataSource.getWeather(any()) }
            coVerify(exactly = 1) { weatherLocalDataSource.deleteAllWeather() }
            coVerify(exactly = 1) { weatherLocalDataSource.insertWeather(any()) }
        }
    }
}