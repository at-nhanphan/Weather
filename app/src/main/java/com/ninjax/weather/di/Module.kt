package com.ninjax.weather.di

import androidx.room.Room
import com.ninjax.weather.BuildConfig
import com.ninjax.weather.data.WeatherApi
import com.ninjax.weather.data.repository.WeatherRepository
import com.ninjax.weather.data.source.local.LocalDB
import com.ninjax.weather.data.source.remote.AuthInterceptor
import com.ninjax.weather.util.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val repositoriesModule = module {
    single { WeatherRepository(get()) }
}

val networkModule = module {
    factory { AuthInterceptor() }
    factory { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    factory {
        provideWeatherApi(get())
    }
}

val roomModule = module {
    single {
        Room.databaseBuilder(get(), LocalDB::class.java, Constants.Config.DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<LocalDB>().weatherDao() }
}

fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
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

    return OkHttpClient.Builder()
        .addInterceptor(authInterceptor)
        .addInterceptor(httpLogging)
        .addInterceptor(interceptor)
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun provideWeatherApi(retrofit: Retrofit): WeatherApi {
    return retrofit.create(WeatherApi::class.java)
}
