package com.g3c1.aide.feature_store.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.g3c1.aide.R
import com.g3c1.aide.ui.theme.DarkGray
import com.g3c1.aide.ui.theme.PretendardText

@Composable
fun StoreListEmptyScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight()
    ) {
        PretendardText(
            text = "등록된 가게가 없어요",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = DarkGray
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.03f))
        Image(
            painter = painterResource(id = R.drawable.add_store_ic),
            contentDescription = "AddStoreIcon",
            modifier = Modifier.clickable { }
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.03f))
    }
}