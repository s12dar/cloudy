package com.example.cloudy.features.home.data.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cloudy.features.home.data.local.HomeLocalDataSource
import com.example.cloudy.features.home.data.remote.HomeRemoteDataSource
import com.example.cloudy.utils.dummyLocation
import com.example.cloudy.utils.fakeNotExpiredWeatherEntity
import com.example.cloudy.utils.fakeNotExpiredWeatherInfo
import com.example.cloudy.utils.`should be`
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
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

    private val mainThreadSurrogate = newSingleThreadContext("thread")


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
    fun `check that getWeather with data is expired fetches successfully from the local source`() {
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

//    @Test
//    fun `check that getWeather with data is not expired fetches successfully from the local source`() {
//        runBlocking {
//
//        }
//    }
}