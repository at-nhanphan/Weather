package com.ninjax.weather.data.source.remote

interface ErrorEmitter {
    fun onError(msg: String)
    fun onError(errorType: ErrorType)
}
