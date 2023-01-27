package com.example.network.result.retrofit

import com.example.network.result.Result
import com.example.network.result.errorHandler.NetworkErrorHandler
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResultAdapterFactory(
    private val networkErrorHandler: NetworkErrorHandler
) : CallAdapter.Factory() {

    @Suppress("ReturnCount")
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        val rawReturnType: Class<*> = getRawType(returnType)
        if (rawReturnType == Call::class.java && returnType is ParameterizedType) {
            val callInnerType: Type = getParameterUpperBound(0, returnType)
            if (getRawType(callInnerType) == Result::class.java) {
                // resultType is Call<Result<*>> | callInnerType is Result<*>
                if (callInnerType is ParameterizedType) {
                    val resultInnerType = getParameterUpperBound(0, callInnerType)
                    return ResultCallAdapter<Any?>(resultInnerType, networkErrorHandler)
                }
                return ResultCallAdapter<Nothing>(Nothing::class.java, networkErrorHandler)
            }
        }

        return null
    }
}

private class ResultCallAdapter<R>(
    private val type: Type,
    private val networkErrorHandler: NetworkErrorHandler,
) :
    CallAdapter<R, Call<Result<R>>> {

    override fun responseType() = type

    override fun adapt(call: Call<R>): Call<Result<R>> = ResultCall(call, networkErrorHandler)
}
