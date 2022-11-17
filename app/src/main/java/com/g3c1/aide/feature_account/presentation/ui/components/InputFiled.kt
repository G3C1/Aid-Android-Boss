package com.g3c1.aide.feature_account.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    val passwordVisible = remember {
        mutableStateOf(isPassword)
    }
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
                fontWeight = FontWeight.Medium,
                color = if (isError) Red else Gray2
            ),
            visualTransformation = if (passwordVisible.value) PasswordVisualTransformation() else VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text),
            trailingIcon = {
                val image = if (passwordVisible.value)
                    Icons.Filled.Visibility
                else Icons.Filled.VisibilityOff
                val description = if (passwordVisible.value) "Hide password" else "Show password"

                if (isPassword) {
                    IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
                        Icon(imageVector = image, description, tint = Gray2)
                    }
                }
            },
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