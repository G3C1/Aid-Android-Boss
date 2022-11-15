package com.g3c1.aide.feature_account.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import com.g3c1.aide.feature_account.presentation.ui.screen.LoginPage
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class AccountActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(Color.White)
            LoginPage()
        }
    }
}