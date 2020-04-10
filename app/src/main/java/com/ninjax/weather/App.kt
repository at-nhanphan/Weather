package com.ninjax.weather

import android.app.Application
import com.ninjax.weather.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Init DI
        startKoin {
            androidContext(this@App)
            modules(
                repository,
                remoteDataSource,
                localDataSource,
                splashVM,
                homeVM
            )
        }
    }
}
