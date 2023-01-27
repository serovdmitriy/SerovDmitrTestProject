package com.example.network.result.errorHandler

import com.example.network.model.ServerErrorResponse
import com.example.network.result.model.NetworkException
import com.squareup.moshi.Moshi
import okhttp3.ResponseBody

internal class NetworkErrorHandlerImpl(
    private val moshi: Moshi,
) : NetworkErrorHandler {

    private val unknownErrorResponse = NetworkException.Unknown()

    override fun networkErrorToThrow(
        code: Int,
        responseBody: ResponseBody?,
        message: String?,
        url: String,
    ): NetworkException {
        return when (code) {
            UNAUTHORIZED -> NetworkException.Unauthorized(code)
            in SERVER_ERROR -> {
                val serverError = parseErrorBody(responseBody) ?: return unknownErrorResponse.copy(code = code)
                if (serverError.code == OBSOLETE_CODE_STATUS) {
                    NetworkException.Obsolete(
                        code = serverError.code,
                        traceId = serverError.traceId
                    )
                } else {
                    NetworkException.ServerError(
                        code = serverError.code,
                        traceId = serverError.traceId,
                        message = serverError.message,
                        details = serverError.details
                    )
                }
            }
            else -> unknownErrorResponse.copy(
                code = code,
                message = message
            )
        }
    }

    private fun parseErrorBody(body: ResponseBody?): ServerErrorResponse? {
        if (body == null) return null
        return runCatching {
            moshi.adapter(ServerErrorResponse::class.java).fromJson(body.string())
        }.getOrNull()
    }

    companion object {
        private val SERVER_ERROR = 400..404
        private const val UNAUTHORIZED = 401
        private const val OBSOLETE_CODE_STATUS = 410
    }
}
