package com.g3c1.aide.component.text

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.g3c1.aide.ui.theme.Font
import com.g3c1.aide.ui.theme.DeepDarkGray
import com.g3c1.aide.ui.theme.Orange

@Composable
fun OnClickText(firstText: String, orangeText: String, lastText: String, onClick: () -> Unit) {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = DeepDarkGray,
                    fontFamily = Font.pretendard,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp
                )
            ) {
                append(firstText)
            }
            withStyle(
                style = SpanStyle(
                    color = Orange, fontFamily = Font.pretendard,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp
                )
            ) {
                append(orangeText)
            }
            withStyle(
                style = SpanStyle(
                    color = DeepDarkGray, fontFamily = Font.pretendard,
                    fontWeight = FontWeight.Normal,
                    fontSize = 13.sp
                )
            ) {
                append(lastText)
            }
        },
        modifier = Modifier.clickable { onClick() }
    )
}