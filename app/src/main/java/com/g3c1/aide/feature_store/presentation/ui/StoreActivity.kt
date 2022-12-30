package com.g3c1.aide.feature_store.presentation.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.g3c1.aide.feature_store.presentation.ui.screen.AddStoreScreen
import com.g3c1.aide.feature_store.presentation.ui.screen.SelectStoreScreen
import com.g3c1.aide.feature_store.presentation.viewmodel.StoreViewModel
import com.g3c1.aide.remote.utils.ApiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StoreActivity: ComponentActivity() {
    private val storeViewModel by viewModels<StoreViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        storeViewModel.getMyStoresInfoRequest()
        getMyStoresRequest()
    }

    private fun getMyStoresRequest() {
        lifecycleScope.launch {
            storeViewModel.getMyStoresRes.collect { result ->
                when (result) {
                    is ApiState.Success -> {
                        Log.d("StoreActivity", result.data.toString())
                        setContent {
                            val navController = rememberNavController()
                            Column(modifier = Modifier.fillMaxSize()) {
                                NavHost(
                                    navController = navController,
                                    startDestination = "SellectStoreScreen"
                                ) {
                                    composable("SellectStoreScreen") {
                                        SelectStoreScreen(
                                            bossName = result.data!!.userName,
                                            storeList = result.data.storeList
                                        ) {
                                            navController.navigate("StoreAddScreen") {
                                                popUpTo("StoreAddScreen") {
                                                    inclusive = true
                                                }
                                            }
                                        }
                                    }
                                    composable("StoreAddScreen") {
                                        AddStoreScreen(
                                            lifecycleCoroutineScope = lifecycleScope,
                                            viewModel = viewModel(LocalContext.current as StoreActivity),
                                            context = this@StoreActivity
                                        ) {
                                            navController.navigate("SellectStoreScreen") {
                                                popUpTo("SellectStoreScreen") {
                                                    inclusive = true
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    is ApiState.Error -> {
                        Log.d("StoreActivity", result.status.toString())
                        Toast.makeText(
                            this@StoreActivity, "알수 없는 오류가 발생했습니다.", Toast.LENGTH_SHORT
                        ).show()
                        storeViewModel.getMyStoresRes.value = ApiState.Loading()
                    }
                    is ApiState.Loading -> {}
                }
            }
        }
    }
}