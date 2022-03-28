package com.choice.core.util

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TokenInterceptor @Inject constructor() : Interceptor {
    companion object {
        const val AUTH_HEADER_KEY = "Authorization"
    }

    private var sessionToken: String = "9c8b06d329136da358c2d00e76946b0111ce2c48"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val requestBuilder: Request.Builder = request.newBuilder()
        if (request.header(AUTH_HEADER_KEY) == "") {
            requestBuilder.header(AUTH_HEADER_KEY, "Token $sessionToken")
        }
        return chain.proceed(requestBuilder.build())
    }
}