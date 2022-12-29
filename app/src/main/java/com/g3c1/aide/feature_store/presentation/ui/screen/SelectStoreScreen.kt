package com.g3c1.aide.feature_store.presentation.ui.screen

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.lifecycle.LifecycleCoroutineScope
import com.g3c1.aide.feature_store.data.dto.StoreInfoDTO
import com.g3c1.aide.feature_store.presentation.ui.components.TopBar
import com.g3c1.aide.feature_store.presentation.viewmodel.StoreViewModel
import com.g3c1.aide.remote.utils.ApiState
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SelectStoreScreen(
    bossName: String,
    storeList: List<StoreInfoDTO>,
    viewModel: StoreViewModel,
    lifecycleCoroutineScope: LifecycleCoroutineScope,
    context: Context,
    moveToAddStorePage: () -> Unit
) {
    getMyStoresInfoRequest(viewModel, lifecycleCoroutineScope, context)
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TopBar(bossName = bossName)
        if (storeList.isEmpty())
            StoreListEmptyScreen {
                moveToAddStorePage()
            }
        else
            StoreListScreen(item = storeList) {
                moveToAddStorePage()
            }
    }
}

private fun getMyStoresInfoRequest(
    viewModel: StoreViewModel,
    lifecycleCoroutineScope: LifecycleCoroutineScope,
    context: Context,
) {
    viewModel.getMyStoresInfoRequest()
    lifecycleCoroutineScope.launch {
        viewModel.getMyStoresRes.collect { result ->
            when (result) {
                is ApiState.Success -> {
                    Log.d("StoreActivity", result.data.toString())
                }
                is ApiState.Error -> {
                    Log.d("StoreActivity", result.status.toString())
                    Toast.makeText(
                        context, "알수 없는 오류가 발생했습니다.", Toast.LENGTH_SHORT
                    ).show()
                    viewModel.getMyStoresRes.value = ApiState.Loading()
                }
                is ApiState.Loading -> {}
            }
        }
    }
}