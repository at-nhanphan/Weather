package com.ninjax.weather.data.vo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName

@Entity(
    indices = [Index("uuid")],
    primaryKeys = ["uuid"]
)
data class Device(
    @field:SerializedName("device_name")
    val deviceName: String,

    @field:SerializedName("os")
    val os: String,

//    @field:SerializedName("profile")
//    @field:Embedded
//    val profile: Profile,

    @field:SerializedName("uuid")
    val uuid: String
)
