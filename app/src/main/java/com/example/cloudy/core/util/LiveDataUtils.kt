package com.example.cloudy.core.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

// This functions helps in transforming a [MutableLiveData] of type [T]
// to a [LiveData] of type [T]
fun <T> MutableLiveData<T>.asLiveData() = this as LiveData<T>

// This function helps to observe a [LiveData] once
fun <T> LiveData<T>.observeOnce(lifecycleOwner: LifecycleOwner, observer: Observer<T>) {
    observe(
        lifecycleOwner,
        object : Observer<T> {
            override fun onChanged(t: T?) {
                observer.onChanged(t)
                removeObserver(this)
            }
        }
    )
}