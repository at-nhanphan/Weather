package com.ninjax.weather.data.source.remote

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

abstract class SafeApi {
    companion object {
        private const val MESSAGE_KEY = "message"
        private const val ERROR_KEY = "error"
    }

    suspend inline fun <T> safeApiCall(
        crossinline callFunction: suspend () -> T
    ): ResultWrapper<T> {
        return withContext(Dispatchers.IO) {
            try {
                ResultWrapper.Success(callFunction.invoke())
            } catch (e: Exception) {
                Log.e("BaseRemoteRepo", "Call error: ${e.localizedMessage}", e.cause)
                when (e) {
                    is HttpException -> {

                        val body = e.response()?.errorBody()
                        ResultWrapper.GenericError(e.code(), getErrorMessage(body))
                    }
                    is IOException -> ResultWrapper.NetworkError
                    else -> ResultWrapper.GenericError(null, null)
                }
            }
        }
    }

    fun getErrorMessage(responseBody: ResponseBody?): String {
        return try {
            val jsonObject = JSONObject(responseBody!!.string())
            when {
                jsonObject.has(MESSAGE_KEY) -> jsonObject.getString(
                    MESSAGE_KEY
                )
                jsonObject.has(ERROR_KEY) -> jsonObject.getString(
                    ERROR_KEY
                )
                else -> "Something wrong happened"
            }
        } catch (e: Exception) {
            "Something wrong happened"
        }
    }
}
