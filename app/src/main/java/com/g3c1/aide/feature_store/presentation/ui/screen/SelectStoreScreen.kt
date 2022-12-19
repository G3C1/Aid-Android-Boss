package com.g3c1.aide.feature_store.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.g3c1.aide.feature_store.presentation.ui.components.TopBar

@Composable
fun SelectStoreScreen() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        TopBar(bossName = "윤지현")
    }
}