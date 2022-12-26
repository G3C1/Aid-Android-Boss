package com.g3c1.aide.remote.api

import android.util.Log
import com.g3c1.aide.BuildConfig
import com.g3c1.aide.di.AideBossApplication
import com.g3c1.aide.feature_account.presentation.utils.TokenType
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

object TokenRefreshAPI {
    fun sendTokenRefreshRequest(refresh: String): Boolean {
        var isRefreshExpired = false
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(BuildConfig.REFRESH_API_URL)
            .patch(RequestBody.create(MediaType.parse("application/json"), ""))
            .addHeader("Refresh-Token", refresh)
            .build()
        val jsonParser = JsonParser()
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            val token = jsonParser.parse(response.body()!!.string()) as JsonObject
            runBlocking {
                AideBossApplication.getInstance().getTokenManager()
                    .setTokenData("Bearer " + token["accessToken"].toString(), TokenType.ACCESS)
                AideBossApplication.getInstance().getTokenManager()
                    .setTokenData(token["refreshToken"].toString(), TokenType.REFRESH)
                AideBossApplication.getInstance().getTokenManager()
                    .setTokenData(token["expiredAt"].toString(), TokenType.EXPIRED)
            }
            Log.d("Interceptor", token.toString())
        } else {
            isRefreshExpired = true
        }
        return isRefreshExpired
    }
}