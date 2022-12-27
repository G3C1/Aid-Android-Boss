package com.g3c1.aide.feature_store.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.g3c1.aide.feature_store.presentation.ui.components.AddStorePageTopBar

@Composable
fun AddStoreScreen(goBackToStoreListPage: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        AddStorePageTopBar {
            goBackToStoreListPage()
        }
    }
}