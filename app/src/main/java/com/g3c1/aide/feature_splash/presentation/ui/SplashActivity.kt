package com.g3c1.aide.feature_splash.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.g3c1.aide.feature_splash.presentation.ui.screen.SplashScreen
import com.g3c1.aide.ui.theme.Orange
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(Orange)
            SplashScreen()
        }
    }
}