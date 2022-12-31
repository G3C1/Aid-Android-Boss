package com.g3c1.aide.remote.utils.token_handler

import android.os.Build
import androidx.annotation.RequiresApi
import com.g3c1.aide.di.AideBossApplication
import com.g3c1.aide.feature_account.presentation.utils.TokenType.*
import com.g3c1.aide.remote.api.TokenRefreshAPI
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class TokenInterceptor : Interceptor {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun intercept(chain: Interceptor.Chain): Response = with(chain) {
        lateinit var accessTokenRequest: Request
        val request = chain.request()
        val path = request.url().encodedPath()
        val ignorePath = listOf(
            "/v2/user", "/v2/user/login"
        )
        if (ignorePath.contains(path)) {
            return chain.proceed(request)
        }
        runBlocking {
            val expiredAt =
                AideBossApplication.getInstance().getTokenManager().getTokenData(EXPIRED)
            val refresh =
                AideBossApplication.getInstance().getTokenManager().getTokenData(REFRESH)
            if (expiredAt.isNotEmpty()) {
                val expiredAtDateTime = LocalDateTime.parse(
                    expiredAt, DateTimeFormatter.ISO_LOCAL_DATE_TIME
                )
                val currentTime = LocalDateTime.parse(
                    LocalDateTime.now(ZoneId.systemDefault()).toString(),
                    DateTimeFormatter.ISO_LOCAL_DATE_TIME
                )
                if (currentTime.isAfter(expiredAtDateTime)) {
                    sendRefreshRequest(refresh)
                }
            }
            accessTokenRequest = request.newBuilder().addHeader(
                "Authorization",
                AideBossApplication.getInstance().getTokenManager().getTokenData(ACCESS)
            ).build()
        }
        return chain.proceed(accessTokenRequest)
    }

    private fun sendRefreshRequest(refresh: String): Boolean? =
        TokenRefreshAPI.sendTokenRefreshRequest(refresh)
}