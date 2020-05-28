package com.ninjax.weather.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ninjax.weather.util.Event

abstract class BaseViewModel : ViewModel() {
    private val loadingProgress = MutableLiveData<Event<Boolean>>()
    private val apiException = MutableLiveData<Event<String>>()

    internal fun getApiException(): LiveData<Event<String>> = apiException

    internal fun getLoadingApiException(): LiveData<Event<Boolean>> = loadingProgress

    protected fun postApiException(msg: String) = apiException.postValue(Event(msg))

    protected fun postStateLoadingProgress(isLoading: Boolean) =
        loadingProgress.postValue(Event(isLoading))
}
