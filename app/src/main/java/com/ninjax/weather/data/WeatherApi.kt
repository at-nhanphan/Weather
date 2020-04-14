package com.ninjax.weather.data

import com.ninjax.weather.data.vo.Weather
import com.ninjax.weather.util.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/weather")
    suspend fun getWeather(
        @Query("id") city: String = "2172797",
        @Query("appid") api: String = Constants.Config.WEATHER_API_KEY
    ): Weather
}
