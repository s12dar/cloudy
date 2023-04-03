package com.lyvetech.cloudy.features.home.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lyvetech.cloudy.domain.util.Resource
import com.lyvetech.cloudy.data.local.HomeLocalDataSource
import com.lyvetech.cloudy.data.remote.HomeRemoteDataSource
import com.lyvetech.cloudy.data.repository.HomeRepositoryImpl
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
class HomeRepositoryImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var systemUnderTest: HomeRepositoryImpl

    @MockK
    private lateinit var homeRemoteDataSource: HomeRemoteDataSource

    @MockK
    private lateinit var homeLocalDataSource: HomeLocalDataSource

    private val mainThreadSurrogate = newSingleThreadContext("threading")


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        Dispatchers.setMain(mainThreadSurrogate)

        systemUnderTest = HomeRepositoryImpl(
            homeRemoteDataSource,
            homeLocalDataSource,
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
                homeLocalDataSource.getWeather()
            } returns fakeNotExpiredWeatherEntity

            val result = systemUnderTest.getWeatherInfo(dummyLocation)

            result.data `should be` fakeNotExpiredWeatherInfo
            coVerify(exactly = 1) { homeLocalDataSource.getWeather() }
            coVerify(exactly = 0) { homeRemoteDataSource.getWeather(any()) }
            coVerify(exactly = 0) { homeLocalDataSource.deleteAllWeather() }
            coVerify(exactly = 0) { homeLocalDataSource.insertWeather(any()) }
        }
    }

    @Test
    fun `check that getWeather with data is expired fetches successfully from the remote source`() {
        runBlocking {

            coEvery {
                homeLocalDataSource.getWeather()
            } returns fakeExpiredWeatherEntity

            coEvery {
                homeRemoteDataSource.getWeather(dummyLocation)
            } returns Resource.Success(fakeWeatherDto)

            coEvery {
                homeLocalDataSource.deleteAllWeather()
            } just Runs

            coEvery {
                homeLocalDataSource.insertWeather(any())
            } just Runs

            val result = systemUnderTest.getWeatherInfo(dummyLocation)

            result.data `should be` fakeExpiredWeatherInfo
            coVerify(exactly = 2) { homeLocalDataSource.getWeather() }
            coVerify(exactly = 1) { homeRemoteDataSource.getWeather(any()) }
            coVerify(exactly = 1) { homeLocalDataSource.deleteAllWeather() }
            coVerify(exactly = 1) { homeLocalDataSource.insertWeather(any()) }
        }
    }
}