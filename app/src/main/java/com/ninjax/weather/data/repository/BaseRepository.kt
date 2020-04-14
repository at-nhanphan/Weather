package com.ninjax.weather.data.repository

import android.util.Log
import com.ninjax.weather.data.source.remote.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRepository {
    companion object {
        private const val MESSAGE_KEY = "message"
        private const val ERROR_KEY = "error"
    }

    /**
     * Function that executes the given function on Dispatchers.IO context and switch to Dispatchers.Main context when an error occurs
     * @param callFunction is the function that is returning the wanted object. It must be a suspend function. Eg:
     * override suspend fun loginUser(body: LoginUserBody, emitter: RemoteErrorEmitter): LoginUserResponse?  = safeApiCall( { authApi.loginUser(body)} , emitter)
     */
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
//                        if (e.code() == 401) emitter.onError(ErrorType.SESSION_EXPIRED)
//                        else {
//                            val body = e.response()?.errorBody()
//                            emitter.onError(getErrorMessage(body))
//                        }
                        val body = e.response()?.errorBody()
                        ResultWrapper.GenericError(e.code(), getErrorMessage(body))
                    }
//                    is SocketTimeoutException -> emitter.onError(ErrorType.TIMEOUT)
                    is IOException -> ResultWrapper.NetworkError
                    else -> ResultWrapper.GenericError(null, null)
                }
            }
        }
    }

    /**
     * Function that executes the given function in whichever thread is given. Be aware, this is not friendly with Dispatchers.IO,
     * @param callFunction is the function that is returning the wanted object. Eg:
     * override suspend fun loginUser(body: LoginUserBody, emitter: RemoteErrorEmitter): LoginUserResponse?  = safeApiCall( { authApi.loginUser(body)} , emitter)
     */
    inline fun <T> safeApiCallNoContext(callFunction: () -> T): ResultWrapper<T> {
        return try {
            ResultWrapper.Success(callFunction.invoke())
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
//                    if (e.code() == 401) emitter.onError(ErrorType.SESSION_EXPIRED)
//                    else {
//                        val body = e.response()?.errorBody()
//                        emitter.onError(getErrorMessage(body))
//                    }
                    val body = e.response()?.errorBody()
                    ResultWrapper.GenericError(e.code(), getErrorMessage(body))
                }
//                is SocketTimeoutException -> emitter.onError(ErrorType.TIMEOUT)
                is IOException -> ResultWrapper.NetworkError
                else -> ResultWrapper.GenericError(null, null)
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
