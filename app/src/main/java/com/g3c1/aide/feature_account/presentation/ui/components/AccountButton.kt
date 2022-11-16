package com.g3c1.aide.feature_account.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.g3c1.aide.ui.theme.Orange
import com.g3c1.aide.ui.theme.PretendardText

@Composable
fun AccountButton(onClick: () -> Unit, text: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.18f),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Orange,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            PretendardText(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}