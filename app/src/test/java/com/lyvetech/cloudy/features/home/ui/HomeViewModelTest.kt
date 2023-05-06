package com.lyvetech.cloudy.features.home.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lyvetech.cloudy.common.ui.UiState
import com.lyvetech.cloudy.domain.util.Resource
import com.lyvetech.cloudy.data.repository.WeatherRepositoryImpl
import com.lyvetech.cloudy.domain.service.LocationTracker
import com.lyvetech.cloudy.domain.pref.PreferencesManager
import com.lyvetech.cloudy.utils.dummyLocation
import com.lyvetech.cloudy.utils.fakeAppPreferences
import com.lyvetech.cloudy.utils.fakeNotExpiredWeatherInfo
import com.lyvetech.cloudy.utils.`should be`
import com.google.common.truth.Truth.assertThat
import com.lyvetech.cloudy.presentation.home.HomeViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var systemUnderTest: HomeViewModel

    @MockK
    private lateinit var homeRepository: WeatherRepositoryImpl

    @MockK
    private lateinit var locationTracker: LocationTracker

    @MockK
    private lateinit var preferencesManager: PreferencesManager

    private val mainThreadSurrogate = newSingleThreadContext("threading")

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        Dispatchers.setMain(mainThreadSurrogate)

        every {
            preferencesManager.appPreferences
        } returns fakeAppPreferences

        systemUnderTest = HomeViewModel(
            homeRepository,
            locationTracker,
            preferencesManager
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun `initial setup of viewModel uiState should return Loading`() {

        val currentState = systemUnderTest.getUiState()
        currentState.value `should be` UiState.Loading
    }

    @Test
    fun `get weatherInfo from repo returns success`() = runBlocking {
        coEvery {
            locationTracker.getCurrentLocation()
        } returns Resource.Success(dummyLocation)

        coEvery {
            homeRepository.getWeatherInfo(dummyLocation)
        } returns Resource.Success(fakeNotExpiredWeatherInfo)

        systemUnderTest.getWeatherInfo()
        val currentState = systemUnderTest.getUiState()

        delay(4)
        assertThat(currentState.value is UiState.Success).isTrue()
        val data = (currentState.value as UiState.Success).data
        assertThat(data.weatherInfo).isEqualTo(fakeNotExpiredWeatherInfo)
        assertThat(data.appPreferences).isEqualTo(fakeAppPreferences)
    }

    @Test
    fun `get weatherInfo from repo returns error`() = runBlocking {
        coEvery {
            locationTracker.getCurrentLocation()
        } returns Resource.Success(dummyLocation)

        coEvery {
            homeRepository.getWeatherInfo(dummyLocation)
        }.throws(Exception("Some error message here"))

        systemUnderTest.getWeatherInfo()
        val currentState = systemUnderTest.getUiState()

        delay(4)
        assertThat(currentState.value is UiState.Error).isTrue()
    }
}