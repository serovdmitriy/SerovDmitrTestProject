@file:Suppress("unused")

package com.example.network.result

import com.example.network.result.model.NetworkException

sealed class Result<out T> {

    sealed class Success<T> : Result<T>() {

        abstract val value: T

        override fun toString() = "Success($value)"

        data class Value<T>(override val value: T) : Success<T>()

        data class HttpResponse<T>(
            override val value: T,
            override val statusCode: Int,
            override val statusMessage: String? = null,
            override val url: String? = null,
        ) : Success<T>(), com.example.network.result.HttpResponse

        object Empty : Success<Nothing>() {

            override val value: Nothing get() = error("No value")

            override fun toString() = "Success"
        }
    }

    sealed class Failure(open val error: Throwable) : Result<Nothing>() {

        override fun toString() = "Failure($error)"

        data class Error(override val error: Throwable) : Failure(error)

        data class NetworkError(override val error: NetworkException) : Failure(error)
    }
}

typealias EmptyResult = Result<Nothing>
