package com.g3c1.aide.feature_store.presentation.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
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
        getMyStoresInfo()
    }

    private fun getMyStoresInfo() {
        storeViewModel.getMyStoresInfoRequest()
        lifecycleScope.launch {
            storeViewModel.getMyStoresRes.collect {
                when (it) {
                    is ApiState.Success -> {
                        setContent {
                            SelectStoreScreen(
                                bossName = it.data!!.name, storeInfoList = it.data.storeList
                            )
                        }
                    }
                    is ApiState.Error -> {
                        Log.d("StoreActivity", it.status.toString())
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