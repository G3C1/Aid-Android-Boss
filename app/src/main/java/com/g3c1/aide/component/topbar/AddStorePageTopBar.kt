package com.g3c1.aide.component.topbar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.g3c1.aide.ui.theme.Orange
import com.g3c1.aide.ui.theme.PretendardText

@Composable
fun AddStorePageTopBar(goBackToStoreListPage: () -> Unit) {
    Card(
        elevation = 5.dp,
        shape = RectangleShape
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.08f)
                .background(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .clickable { goBackToStoreListPage() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.size(13.dp))
                Icon(
                    Default.ArrowBackIos,
                    contentDescription = "BackButtonIcon",
                    tint = Orange,
                    modifier = Modifier.fillMaxHeight(0.3f)
                )
                PretendardText(
                    text = "뒤로가기",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Normal,
                    color = Orange
                )
            }
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                PretendardText(
                    text = "가게 추가",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }
}