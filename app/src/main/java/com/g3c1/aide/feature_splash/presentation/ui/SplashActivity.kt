package com.g3c1.aide.feature_splash.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.g3c1.aide.di.AideBossApplication
import com.g3c1.aide.feature_account.presentation.ui.AccountActivity
import com.g3c1.aide.feature_account.presentation.utils.TokenType
import com.g3c1.aide.feature_splash.presentation.ui.screen.SplashScreen
import com.g3c1.aide.feature_store.presentation.ui.StoreActivity
import com.g3c1.aide.remote.utils.token_handler.TokenInterceptor
import com.g3c1.aide.ui.theme.Orange
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(Orange)
            SplashScreen()
        }
        lifecycleScope.launch {
            val isRefreshExpired = TokenInterceptor().sendRefreshRequest(
                AideBossApplication.getInstance().getTokenManager().getTokenData(TokenType.REFRESH)
            )
            delay(1000)
            if (isRefreshExpired != null) {
                startActivity(
                    Intent(
                        this@SplashActivity,
                        if (isRefreshExpired) AccountActivity::class.java else StoreActivity::class.java
                    )
                )
            } else {
                Toast.makeText(this@SplashActivity, "알수 없는 오류가 발생했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}