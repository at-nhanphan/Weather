package com.ninjax.weather.data.repository

import com.ninjax.weather.data.ApiService
import com.ninjax.weather.data.vo.Weather
import org.koin.core.KoinComponent
import org.koin.core.inject

class WeatherRepository : KoinComponent {
    private val apiService by inject<ApiService>()

    suspend fun getWeather(): Weather = apiService.getWeather()
}
