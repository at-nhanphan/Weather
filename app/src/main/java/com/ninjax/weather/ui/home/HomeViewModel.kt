package com.ninjax.weather.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ninjax.weather.data.repository.WeatherRepository
import com.ninjax.weather.data.source.remote.ErrorEmitter
import com.ninjax.weather.data.source.remote.ErrorType
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeViewModel : ViewModel(), KoinComponent, ErrorEmitter {
    private val weatherRepository by inject<WeatherRepository>()

    val weatherInfo = liveData(Dispatchers.IO) {
        val weather = weatherRepository.getWeather(this@HomeViewModel)
        emit(weather?.name)
    }

    override fun onError(msg: String) {

    }

    override fun onError(errorType: ErrorType) {
    }
}
