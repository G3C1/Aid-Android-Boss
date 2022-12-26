package com.g3c1.aide.remote.utils

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.g3c1.aide.di.AideBossApplication
import com.g3c1.aide.feature_account.presentation.utils.TokenType.*
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.Dispatchers
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
        runBlocking(Dispatchers.IO) {
            expiredAt = AideBossApplication.getInstance().getTokenManager().getTokenData(EXPIRED)
                .replace("\"", "")
            refresh = AideBossApplication.getInstance().getTokenManager().getTokenData(REFRESH)
                .replace("\"", "")
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
                        .replace("\"", "")
                ).build()
            Log.d(
                "Interceptor",
                AideBossApplication.getInstance().getTokenManager().getTokenData(ACCESS)
                    .replace("\"", "")
            )
        }

        return proceed(if (ignorePath.contains(path)) request else accessTokenRequest)
    }

    fun sendRefreshRequest(refresh: String): Boolean {
        var isRefreshExpired = false
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
                val token = jsonParser.parse(response.body()!!.string()) as JsonObject
                runBlocking {
                    AideBossApplication.getInstance().getTokenManager()
                        .setTokenData("Bearer " + token["accessToken"].toString(), ACCESS)
                    AideBossApplication.getInstance().getTokenManager()
                        .setTokenData(token["refreshToken"].toString(), REFRESH)
                    AideBossApplication.getInstance().getTokenManager()
                        .setTokenData(token["expiredAt"].toString(), EXPIRED)
                }
                Log.d("Interceptor", token.toString())
            } else {
                isRefreshExpired = true
            }
        } catch (e: Exception) {
            Log.d("Interceptor", e.toString())
        }

        return isRefreshExpired
    }
}