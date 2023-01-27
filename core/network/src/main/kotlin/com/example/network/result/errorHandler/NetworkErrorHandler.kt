package com.example.network.result.errorHandler

import com.example.network.result.model.NetworkException
import okhttp3.ResponseBody

interface NetworkErrorHandler {
    fun networkErrorToThrow(code: Int, responseBody: ResponseBody?, message: String?, url: String): NetworkException
}
