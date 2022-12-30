package com.g3c1.aide.di

import android.app.Application
import com.g3c1.aide.feature_account.presentation.utils.TokenManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AideBossApplication: Application() {
    private lateinit var tokenManager: TokenManager

    override fun onCreate() {
        super.onCreate()
        aideBossApplication = this
        tokenManager = TokenManager(this)
    }

    companion object {
        private lateinit var aideBossApplication: AideBossApplication
        fun getInstance(): AideBossApplication = aideBossApplication
    }

    fun getTokenManager(): TokenManager = tokenManager
}