package com.ninjax.weather.di

import androidx.room.Room
import com.ninjax.weather.data.ApiService
import com.ninjax.weather.data.repository.WeatherRepository
import com.ninjax.weather.data.source.local.LocalDB
import com.ninjax.weather.util.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repository = module {
    single { WeatherRepository() }
}

val remoteDataSource = module {
    single<ApiService> {
        val httpLogging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val interceptor = Interceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .method(original.method(), original.body())
                .build()
            chain.proceed(request)
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLogging)
            .addInterceptor(interceptor)
            .build()
        Retrofit.Builder()
            .baseUrl("http://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiService::class.java)
    }
}

val localDataSource = module {
    single {
        Room.databaseBuilder(get(), LocalDB::class.java, Constants.Config.DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<LocalDB>().weatherDao() }
}
