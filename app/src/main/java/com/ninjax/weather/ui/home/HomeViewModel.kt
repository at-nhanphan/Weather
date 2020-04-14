package com.ninjax.weather.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.ninjax.weather.data.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent
import org.koin.core.inject

class HomeViewModel : ViewModel(), KoinComponent {
    private val weatherRepository by inject<WeatherRepository>()

    val weatherInfo = liveData(Dispatchers.IO) {
        val weather = weatherRepository.getWeather()
        emit(weather)
    }
}
