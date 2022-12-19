package com.g3c1.aide.feature_store.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.g3c1.aide.feature_store.presentation.ui.screen.SelectStoreScreen

class StoreActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SelectStoreScreen()
        }
    }
}