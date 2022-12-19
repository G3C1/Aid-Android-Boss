package com.g3c1.aide.feature_store.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.g3c1.aide.feature_store.data.dto.StoreInfoDTO
import com.g3c1.aide.feature_store.presentation.ui.components.StoreListItem
import com.g3c1.aide.ui.theme.Gray
import com.g3c1.aide.ui.theme.Orange
import com.g3c1.aide.ui.theme.PretendardText

@Composable
fun StoreListScreen(item: List<StoreInfoDTO>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Gray),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for (i in item.indices) {
            item {
                Spacer(modifier = Modifier.size(12.dp))
                StoreListItem(
                    storeName = item[i].name,
                    storeLogoImgUrl = item[i].img,
                    storeDescription = item[i].description
                )
            }
        }
        item {
            Spacer(modifier = Modifier.size(24.dp))
            Box(modifier = Modifier.clickable { }) {
                PretendardText(
                    text = "+가게 추가하기",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Orange
                )
            }
            Spacer(modifier = Modifier.size(15.dp))
        }
    }

}