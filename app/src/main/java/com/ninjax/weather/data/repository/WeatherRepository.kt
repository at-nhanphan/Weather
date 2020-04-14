package com.ninjax.weather.data.repository

import com.ninjax.weather.data.WeatherApi
import com.ninjax.weather.data.source.remote.ResultWrapper
import com.ninjax.weather.data.source.remote.SafeApi
import com.ninjax.weather.data.vo.Weather
import com.ninjax.weather.util.Event

class WeatherRepository(private val weatherApi: WeatherApi) : SafeApi() {

    suspend fun getWeather(): Event<ResultWrapper<Weather>> = safeApiCall {
        weatherApi.getWeather()
    }
}
