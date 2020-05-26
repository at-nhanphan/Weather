package com.ninjax.weather.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ninjax.weather.data.repository.WeatherRepository
import com.ninjax.weather.data.source.remote.ResultWrapper
import com.ninjax.weather.data.vo.Weather
import com.ninjax.weather.viewmodel.BaseViewModel
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeViewModel : BaseViewModel(), KoinComponent {
    private val weatherRepository by inject<WeatherRepository>()

    private val weatherResult = MutableLiveData<ResultWrapper<Weather>>()

    fun callApiWeather() {
        viewModelScope.launch {
            postStateLoadingProgress(isLoading = true)
            val weather = weatherRepository.getWeather()
            when (weather) {
                is ResultWrapper.NetworkError -> {
                    postApiException("NetworkError")
                }
                is ResultWrapper.GenericError -> {
                    postApiException(weather.msg ?: "Generic Error")
                }
            }
            weatherResult.postValue(weather)
            postStateLoadingProgress(isLoading = false)
        }
    }

    fun getWeatherResult(): LiveData<ResultWrapper<Weather>> = weatherResult
}
