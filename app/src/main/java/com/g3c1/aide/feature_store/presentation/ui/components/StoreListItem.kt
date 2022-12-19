package com.g3c1.aide.feature_store.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.g3c1.aide.ui.theme.Gray2
import com.g3c1.aide.ui.theme.PretendardText

@Composable
fun StoreListItem(storeName: String, storeLogoImgUrl: String, storeDescription: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .height(105.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(10.dp))
        Image(
            painter = rememberImagePainter(data = storeLogoImgUrl),
            contentDescription = "FoodImage",
            modifier = Modifier
                .width(85.dp)
                .height(85.dp)
        )
        Spacer(modifier = Modifier.size(10.dp))
        Column(modifier = Modifier.fillMaxHeight(0.7f)) {
            PretendardText(
                text = storeName,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            PretendardText(
                text = storeDescription,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Gray2
            )
        }
    }
}