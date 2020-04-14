package com.ninjax.weather.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ninjax.weather.data.vo.Device

@Database(entities = [Device::class], version = 1, exportSchema = false)
abstract class LocalDB : RoomDatabase() {
    abstract fun weatherDao(): WeatherDAO
}
