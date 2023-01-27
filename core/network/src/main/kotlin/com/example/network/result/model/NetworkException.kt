package com.example.network.result.model

import java.io.IOException

sealed class NetworkException : IOException() {

    abstract val traceId: String?

    data class NoInternetAccess(
        override val traceId: String? = null,
        override val message: String = "No internet connection"
    ) : NetworkException()

    data class TimeoutError(
        override val traceId: String? = null,
        override val message: String = "TimeOut exception"
    ) : NetworkException()

    data class Unauthorized(
        val code: Int,
        override val traceId: String? = null,
        override val message: String = "Unauthorized exception",
    ) : NetworkException()

    data class Obsolete(
        val code: Int,
        override val traceId: String? = null,
        override val message: String = "Obsolete api need update of the application",
    ) : NetworkException()

    data class ServerError(
        val code: Int,
        override val traceId: String,
        override val message: String,
        val details: Map<String, String>? = null
    ) : NetworkException()

    data class Unknown(
        val code: Int = -1,
        override val traceId: String? = null,
        override val message: String? = "Unknown error",
        override val cause: Throwable? = null
    ) : NetworkException()
}
