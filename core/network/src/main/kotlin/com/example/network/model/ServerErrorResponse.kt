package com.example.network.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
internal class ServerErrorResponse(
    val code: Int,
    val message: String,
    val traceId: String,
    val details: Map<String, String>?
)
