package com.g3c1.aide.remote.utils

import com.g3c1.aide.di.AideBossApplication
import com.g3c1.aide.feature_account.presentation.utils.TokenType
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        lateinit var accessTokenRequest: Request
        val request = chain.request()
        val path = request.url().encodedPath()
        val ignorePath = listOf(
            "v2/user/",
            "v2/user/login"
        )
        runBlocking {
            accessTokenRequest =
                request.newBuilder().addHeader(
                    "Authorization",
                    AideBossApplication.getInstance().getTokenManager()
                        .getTokenData(TokenType.ACCESS)
                ).build()
        }
        return proceed(if (ignorePath.contains(path)) request else accessTokenRequest)
    }
}