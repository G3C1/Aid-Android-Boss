package com.g3c1.aide.remote.utils

import com.g3c1.aide.di.AideBossApplication
import com.g3c1.aide.feature_account.presentation.utils.TokenType
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        lateinit var accessToken: Request
        runBlocking {
            accessToken =
                request().newBuilder().addHeader(
                    "Authorization",
                    AideBossApplication.getInstance().getTokenManager()
                        .getTokenData(TokenType.ACCESS)
                ).build()
        }
        return proceed(accessToken)
    }
}