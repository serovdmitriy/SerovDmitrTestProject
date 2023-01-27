package com.example.network.result.retrofit

import com.example.network.result.Result
import com.example.network.result.errorHandler.NetworkErrorHandler
import com.example.network.result.model.NetworkException
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

internal class ResultCall<T>(proxy: Call<T>, private val networkErrorHandler: NetworkErrorHandler) :
    CallDelegate<T, Result<T>>(proxy) {

    override fun enqueueImpl(callback: Callback<Result<T>>) {
        proxy.enqueue(ResultCallback(this, callback, networkErrorHandler))
    }

    override fun cloneImpl(): ResultCall<T> {
        return ResultCall(proxy.clone(), networkErrorHandler)
    }

    private class ResultCallback<T>(
        private val proxy: ResultCall<T>,
        private val callback: Callback<Result<T>>,
        private val networkErrorHandler: NetworkErrorHandler,
    ) : Callback<T> {

        @Suppress("UNCHECKED_CAST")
        override fun onResponse(call: Call<T>, response: Response<T>) {
            val result: Result<T> = if (response.isSuccessful) {
                Result.Success.HttpResponse(
                    value = response.body() as T,
                    statusCode = response.code(),
                    statusMessage = response.message(),
                    url = call.request().url.toString(),
                )
            } else {
                Result.Failure.NetworkError(
                    networkErrorHandler.networkErrorToThrow(
                        code = response.code(),
                        responseBody = response.errorBody(),
                        message = response.message(),
                        url = call.request().url.toString()
                    )
                )
            }
            callback.onResponse(proxy, Response.success(result))
        }

        override fun onFailure(call: Call<T>, error: Throwable) {
            val result = when (error) {
                is retrofit2.HttpException -> Result.Failure.NetworkError(
                    NetworkException.Unknown(error.code(), error.message(), cause = error)
                )
                is UnknownHostException, is ConnectException -> Result.Failure.NetworkError(
                    NetworkException.NoInternetAccess()
                )
                is TimeoutException, is SocketTimeoutException -> Result.Failure.NetworkError(
                    NetworkException.TimeoutError()
                )
                else -> Result.Failure.Error(error)
            }

            Timber.e("Request error: ${error.localizedMessage}")
            callback.onResponse(proxy, Response.success(result))
        }
    }

    override fun timeout(): Timeout {
        return proxy.timeout()
    }
}
