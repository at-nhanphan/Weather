package com.ninjax.weather.data.source.remote

import com.ninjax.weather.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var req = chain.request()
        val url = req.url().newBuilder()
            .addQueryParameter("appid", BuildConfig.API_WEATHER_KEY).build()

        req = req.newBuilder().url(url).build()
        return chain.proceed(req)
    }
}
