package com.ninjax.weather.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ninjax.weather.data.repository.WeatherRepository
import com.ninjax.weather.data.source.remote.ResultWrapper
import com.ninjax.weather.data.vo.Weather
import com.ninjax.weather.util.Event
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeViewModel : ViewModel(), KoinComponent {
    private val weatherRepository by inject<WeatherRepository>()

    private val weatherResult = MutableLiveData<ResultWrapper<Weather>>()
    private val apiException = MutableLiveData<Event<String>>()

    fun callApiWeather() {
        viewModelScope.launch {
            val weather = weatherRepository.getWeather()

            weatherResult.postValue(weather)
        }
    }

    fun getWeatherResult(): LiveData<ResultWrapper<Weather>> = weatherResult
}
