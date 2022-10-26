package com.example.cloudy.core.ui

sealed class UiState<out T> {

    class Success<T>(val data: T) : UiState<T>()

    class Error(val message: String) : UiState<Nothing>()

    object Loading : UiState<Nothing>()
}
