package com.g3c1.aide.ui.theme

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

@Composable
fun PretendardText(text: String, fontSize: TextUnit, fontWeight: FontWeight, color: Color) {
    Text(
        text = text, style = TextStyle(
            fontSize = fontSize,
            fontFamily = Font.pretendard,
            fontWeight = fontWeight,
            color = color
        )
    )
}
