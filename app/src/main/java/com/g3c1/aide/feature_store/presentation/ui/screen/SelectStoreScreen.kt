package com.g3c1.aide.feature_store.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.g3c1.aide.feature_store.data.dto.StoreInfoDTO
import com.g3c1.aide.feature_store.presentation.ui.components.TopBar

@Composable
fun SelectStoreScreen(
    bossName: String,
    storeInfoList: List<StoreInfoDTO>,
    moveToAddStorePage: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TopBar(bossName = bossName)
        if (storeInfoList.isEmpty())
            StoreListEmptyScreen {
                moveToAddStorePage()
            }
        else
            StoreListScreen(item = storeInfoList) {
                moveToAddStorePage()
            }
    }
}