package com.g3c1.aide.feature_store.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.g3c1.aide.ui.theme.Orange
import com.g3c1.aide.ui.theme.TransparentGray

@Composable
fun Progressbar(isLoading: Boolean) {
    if (isLoading) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(TransparentGray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp),
                color = Orange
            )
        }
    }
}
