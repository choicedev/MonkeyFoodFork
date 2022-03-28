package com.choice.core.remote

import com.choice.core.util.IResult
import com.choice.core.util.NetworkCallException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import okio.IOException
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

typealias NetworkAPIInvoke<T> = suspend () -> Response<T>

suspend fun <T: Any> performnetworkCall(
    messageInCaseOfError: String = "Network Error",
    allowRetries: Boolean = true,
    numberOfRetries: Int = 2,
    networkAPICall: NetworkAPIInvoke<T>
): Flow<IResult<T>> {
    var delayDuration = 1000L
    val delayFactor = 2
    return flow {
        val response = networkAPICall()
        if (response.isSuccessful) {
            response.body()?.let {
                emit(IResult.OnSuccess(it)) } ?:
                emit(IResult.OnFailed(
                    NetworkCallException("API call successful but empty response body")
                )
            )
            return@flow
        }
        when(response.code()){
            404 -> {
                throw NetworkCallException("API address not found")
            }
        }

        throw NetworkCallException(response.errorBody()?.string() ?: messageInCaseOfError)
    }.retryWhen { cause, attempt ->
        if (!allowRetries || attempt > numberOfRetries || cause !is java.io.IOException) return@retryWhen false
        delay(delayDuration)
        delayDuration *= delayFactor
        return@retryWhen true
    }.catch { e ->

        if (e is UnknownHostException) {
            emit(IResult.OnFailed(UnknownHostException("Check your internet connection and try again.")))
            return@catch
        }
        if (e is SocketTimeoutException) {
            emit(IResult.OnFailed(Exception("Connection time exceeded")))
            return@catch
        }
        emit(IResult.OnFailed(NetworkCallException("Please report the error to support: $e")))
    }
}