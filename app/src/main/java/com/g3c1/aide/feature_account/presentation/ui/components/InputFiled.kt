package com.g3c1.aide.feature_account.presentation.ui.components

import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.g3c1.aide.ui.theme.*

@Composable
fun InputField(
    text: String,
    hint: String,
    onValueChange: (String) -> Unit,
    isError: Boolean,
    errorMsg: String,
    isPassword: Boolean
) {
    Column {
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
                errorIndicatorColor = Red,
                errorCursorColor = Red,
                cursorColor = Orange
            ),
            placeholder = {
                Text(
                    text = hint,
                    fontFamily = Font.pretendard,
                    fontWeight = FontWeight.Medium,
                    color = if (isError) Red else Gray2
                )
            },
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontFamily = Font.pretendard,
                fontWeight = FontWeight.Medium
            ),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            maxLines = 1,
            isError = isError
        )
        if (isError) {
            Spacer(modifier = Modifier.size(4.dp))
            Box(modifier = Modifier.padding(start = 11.dp)) {
                PretendardText(
                    text = errorMsg,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = Red
                )
            }
        }
    }
}