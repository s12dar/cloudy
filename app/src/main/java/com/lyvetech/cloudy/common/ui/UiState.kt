package com.lyvetech.cloudy.common.ui

sealed class UiState<out T> {

    class Success<T>(val data: T) : UiState<T>()
    class Error(val exception: Throwable) : UiState<Nothing>()
    object Loading : UiState<Nothing>()
}
