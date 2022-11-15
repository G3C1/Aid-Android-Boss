package com.g3c1.aide.feature_account.presentation.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.g3c1.aide.ui.theme.Font
import com.g3c1.aide.ui.theme.Gray
import com.g3c1.aide.ui.theme.Gray2
import com.g3c1.aide.ui.theme.Orange

@Composable
fun InputField(text: String, hint: String, onValueChange: (String) -> Unit, isError: Boolean) {
    OutlinedTextField(
        value = text,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth(0.9f),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Gray,
            textColor = Gray2,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = Orange
        ),
        placeholder = {
            Text(
                text = hint,
                fontFamily = Font.pretendard,
                fontWeight = FontWeight.Medium,
                color = Gray2
            )
        },
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontFamily = Font.pretendard,
            fontWeight = FontWeight.Medium
        ),
        maxLines = 1,
        isError = isError
    )
}