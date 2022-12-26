package com.g3c1.aide.remote.utils.token_handler

import android.os.Build
import androidx.annotation.RequiresApi
import com.g3c1.aide.di.AideBossApplication
import com.g3c1.aide.feature_account.presentation.utils.TokenType.*
import com.g3c1.aide.remote.api.TokenRefreshAPI
import kotlinx.coroutines.Dispatchers
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
        lateinit var refresh: String
        lateinit var expiredAt: String
        val request = request()
        val path = request.url().encodedPath()
        val ignorePath = listOf(
            "/api/v2/user/",
            "/api/v2/user/login"
        )
        runBlocking(Dispatchers.IO) {
            expiredAt = AideBossApplication.getInstance().getTokenManager().getTokenData(EXPIRED)
            refresh = AideBossApplication.getInstance().getTokenManager().getTokenData(REFRESH)
        }
        val expiredAtDateTime = LocalDateTime.parse(
            expiredAt,
            DateTimeFormatter.ISO_LOCAL_DATE_TIME
        )
        val currentTime = LocalDateTime.now(ZoneId.systemDefault())

        if (currentTime.isAfter(expiredAtDateTime)) {
            sendRefreshRequest(refresh)
        }

        runBlocking(Dispatchers.IO) {
            accessTokenRequest =
                request.newBuilder().addHeader(
                    "Authorization",
                    AideBossApplication.getInstance().getTokenManager().getTokenData(ACCESS)
                ).build()
        }
        return proceed(if (ignorePath.contains(path)) request else accessTokenRequest)
    }

    fun sendRefreshRequest(refresh: String): Boolean? =
        TokenRefreshAPI.sendTokenRefreshRequest(refresh)
}