package com.g3c1.aide.remote.utils

import com.g3c1.aide.di.AideBossApplication
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        val tokenRequest =
            request().newBuilder().addHeader(
                "Authorization",
                AideBossApplication.getInstance().getTokenManager().accessToken.toString()
            ).build()
        return proceed(tokenRequest)
    }
}