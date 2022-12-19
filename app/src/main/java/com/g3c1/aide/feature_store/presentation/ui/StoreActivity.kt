package com.g3c1.aide.feature_store.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.g3c1.aide.feature_store.data.dto.StoreInfoDTO
import com.g3c1.aide.feature_store.presentation.ui.screen.SelectStoreScreen

class StoreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SelectStoreScreen("윤지빈", listOf(StoreInfoDTO("zhzhzh","https://ccimg.hellomarket.com/images/2020/item/12/11/11/1123259_880051_10.jpg","zzzzz")))
        }
    }
}