package com.ninjax.weather.data

import com.ninjax.weather.data.vo.Weather
import retrofit2.http.GET

interface WeatherApi {

    @GET("weather?q=London")
    suspend fun getWeather(): Weather
}
