package com.ninjax.weather.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [], version = 1, exportSchema = false)
abstract class LocalDB : RoomDatabase() {
    abstract fun weatherDao(): WeatherDAO
}
