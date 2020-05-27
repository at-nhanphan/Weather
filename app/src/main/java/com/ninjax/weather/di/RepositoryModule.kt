package com.ninjax.weather.di

import androidx.room.Room
import com.ninjax.weather.data.repository.WeatherRepository
import com.ninjax.weather.data.source.local.LocalDB
import com.ninjax.weather.util.Constants
import org.koin.dsl.module

val repositoryModule = module {
    single { WeatherRepository(get()) }
}

val roomDbModule = module {
    single {
        Room.databaseBuilder(get(), LocalDB::class.java, Constants.Config.DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<LocalDB>().weatherDao() }
}
