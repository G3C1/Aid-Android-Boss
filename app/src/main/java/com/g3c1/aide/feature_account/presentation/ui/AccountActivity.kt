package com.g3c1.aide.feature_account.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.g3c1.aide.feature_account.presentation.ui.screen.LoginPage
import com.g3c1.aide.feature_account.presentation.ui.screen.RealNameScreen
import com.g3c1.aide.feature_account.presentation.ui.screen.SignUpScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(Color.White)
            val navController = rememberNavController()
            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.White)) {
                Spacer(modifier = Modifier.fillMaxHeight(0.2f))
                NavHost(navController = navController, startDestination = "SignInScreen") {
                    composable("SignInScreen") {
                        LoginPage(
                            viewModel = viewModel(LocalContext.current as AccountActivity),
                            lifecycleScope,
                            applicationContext
                        ) {
                            navController.navigate("SignUpScreen") {
                                popUpTo("SignUpScreen") {
                                    inclusive = true
                                }
                            }
                        }
                    }
                    composable("SignUpScreen") {
                        SignUpScreen(viewModel = viewModel(LocalContext.current as AccountActivity),
                            goLoginScreen = {
                                navController.navigate("SignInScreen") {
                                    popUpTo("SignInScreen") {
                                        inclusive = true
                                    }
                                }
                            },
                            goRealNameScreen = {
                                navController.navigate("RealNameScreen") {
                                    popUpTo("RealNameScreen") {
                                        inclusive = true
                                    }
                                }
                            }
                        )
                    }
                    composable("RealNameScreen") {
                        RealNameScreen(
                            viewModel = viewModel(LocalContext.current as AccountActivity),
                            lifecycleScope,
                            applicationContext
                        ) {
                            navController.navigate("SignInScreen") {
                                popUpTo("SignInScreen") {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}