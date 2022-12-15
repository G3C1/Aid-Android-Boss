package com.g3c1.aide.di

import android.app.Application
import com.g3c1.aide.feature_account.presentation.utils.TokenManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AideBossApplication : Application() {
    private lateinit var tokenManager: TokenManager

    companion object {
        private lateinit var aideBossApplication: AideBossApplication
        fun getInstance(): AideBossApplication = aideBossApplication
    }

    override fun onCreate() {
        super.onCreate()
        aideBossApplication = this
        tokenManager = TokenManager(this)
    }

    fun getTokenManager(): TokenManager = tokenManager
}