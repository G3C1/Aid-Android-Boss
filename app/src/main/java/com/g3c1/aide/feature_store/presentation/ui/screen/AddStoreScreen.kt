package com.g3c1.aide.feature_store.presentation.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.g3c1.aide.feature_store.presentation.ui.components.AddStorePageTopBar

@Composable
fun AddStoreScreen(goBackToStoreListPage: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        AddStorePageTopBar {
            goBackToStoreListPage()
        }
    }
}