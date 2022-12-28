package com.g3c1.aide.feature_store.presentation.ui.components

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.g3c1.aide.R
import com.g3c1.aide.ui.theme.DeepDarkGray
import com.g3c1.aide.ui.theme.Gray
import com.g3c1.aide.ui.theme.PretendardText

@Composable
fun StoreImageField(
    selectedImage: Uri? = null,
    imageSelect: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(150.dp)
            .height(150.dp)
            .clickable { imageSelect() }
            .clip(RoundedCornerShape(10.dp))
            .background(Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        if (selectedImage != null)
            Image(
                painter = rememberImagePainter(selectedImage),
                contentDescription = "SelectedImage",
                modifier = Modifier
                    .clickable { imageSelect() },
                contentScale = ContentScale.Crop
            )
        else {
            Image(
                painter = painterResource(id = R.drawable.gallary_ic),
                contentDescription = "GallaryIcon"
            )
            Spacer(modifier = Modifier.size(12.dp))
            PretendardText(
                text = "가게 사진을\n등록해주세요.",
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                color = DeepDarkGray
            )
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}