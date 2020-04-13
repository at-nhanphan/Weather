package com.ninjax.weather.data.repository

import com.ninjax.weather.data.ApiService
import com.ninjax.weather.data.source.remote.ErrorEmitter
import com.ninjax.weather.data.vo.Weather
import org.koin.core.KoinComponent
import org.koin.core.inject

class WeatherRepository : KoinComponent, BaseRepository() {
    private val apiService by inject<ApiService>()

    suspend fun getWeather(errorEmitter: ErrorEmitter): Weather? = safeApiCall(errorEmitter) {
        apiService.getWeather()
    }
}
