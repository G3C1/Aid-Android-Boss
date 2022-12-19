package com.g3c1.aide.feature_store.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.g3c1.aide.R
import com.g3c1.aide.ui.theme.PretendardText

@Composable
fun TopBar(bossName: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.08f)
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.fillMaxWidth(0.03f))
        PretendardText(
            text = "$bossName 사장님",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.fillMaxWidth(0.85f))
        Image(painter = painterResource(id = R.drawable.boss_ic), contentDescription = "bossIcon")
    }
}