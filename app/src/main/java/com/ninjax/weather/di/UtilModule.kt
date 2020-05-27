package com.ninjax.weather.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.ninjax.weather.util.Constants.Util.PREFERENCES_KEY
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val utilModule = module {
    single { providePreferences(androidApplication()) }
}

private fun providePreferences(app: Application): SharedPreferences =
    app.getSharedPreferences(PREFERENCES_KEY, Context.MODE_PRIVATE)
