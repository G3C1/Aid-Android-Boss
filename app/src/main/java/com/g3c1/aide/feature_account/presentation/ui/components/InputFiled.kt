package com.g3c1.aide.feature_account.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.g3c1.aide.ui.theme.*

@Composable
fun InputField(
    text: String,
    hint: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    errorMsg: String?
) {
    Column {
        Spacer(modifier = Modifier.size(2.dp))
        PretendardText(
            text = if (isError) errorMsg!! else "",
            fontSize = 10.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Red
        )
        Spacer(modifier = Modifier.size(2.dp))
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        maxLines = 1,
        isError = isError
    )
    }
}