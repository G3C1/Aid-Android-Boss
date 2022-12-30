package com.g3c1.aide.feature_store.presentation.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.g3c1.aide.feature_store.data.dto.StoreInfoDTO
import com.g3c1.aide.feature_store.presentation.ui.components.TopBar

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SelectStoreScreen(
    bossName: String,
    storeList: List<StoreInfoDTO>,
    moveToAddStorePage: () -> Unit
) {
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