package com.g3c1.aide.remote.utils

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.g3c1.aide.di.AideBossApplication
import com.g3c1.aide.feature_account.presentation.utils.TokenType.*
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.runBlocking
import okhttp3.*
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
        runBlocking {
            expiredAt = AideBossApplication.getInstance().getTokenManager().getTokenData(EXPIRED)
                .replace("\"", "")
            refresh = AideBossApplication.getInstance().getTokenManager().getTokenData(REFRESH)
                .replace("\"", "")

            accessTokenRequest =
                request.newBuilder().addHeader(
                    "Authorization",
                    AideBossApplication.getInstance().getTokenManager().getTokenData(ACCESS)
                        .replace("\"", "")
                ).build()
        }

        val expiredAtDateTime = LocalDateTime.parse(
            expiredAt,
            DateTimeFormatter.ISO_LOCAL_DATE_TIME
        )
        val currentTime = LocalDateTime.now(ZoneId.systemDefault())
        Log.d("Interceptor", "ex: $expiredAtDateTime, cur: $currentTime")
        if (currentTime.isAfter(expiredAtDateTime)) {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("http://10.82.18.78:8000/api/v2/user")
                    .patch(RequestBody.create(MediaType.parse("application/json"), ""))
                    .addHeader("Refresh-Token", refresh)
                    .build()
                val jsonParser = JsonParser()
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    Log.d("Interceptor", "성공")
                    val token = jsonParser.parse(response.body()!!.string()) as JsonObject
                    runBlocking {
                        AideBossApplication.getInstance().getTokenManager()
                            .setTokenData(token["accessToken"].toString(), ACCESS)
                        AideBossApplication.getInstance().getTokenManager()
                            .setTokenData(token["refreshToken"].toString(), REFRESH)
                        AideBossApplication.getInstance().getTokenManager()
                            .setTokenData(token["expiredAt"].toString(), EXPIRED)
                        accessTokenRequest =
                            request.newBuilder().addHeader(
                                "Authorization",
                                AideBossApplication.getInstance().getTokenManager()
                                    .getTokenData(ACCESS)
                            ).build()
                    }
                    Log.d("Interceptor", token.toString())
                }
            } catch (e: Exception) {
                Log.d("Interceptor", e.toString())
            }
        }

        return proceed(if (ignorePath.contains(path)) request else accessTokenRequest)
    }
}