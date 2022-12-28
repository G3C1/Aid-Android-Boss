package com.g3c1.aide.feature_store.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.g3c1.aide.ui.theme.*

@Composable
fun StoreInfoInputField(
    text: String,
    hint: String,
    isDescription: Boolean,
    onValueChange: (String) -> Unit,
) {
    Column {
        OutlinedTextField(
            value = text,
            onValueChange = onValueChange,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Gray,
                textColor = DeepDarkGray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Red,
                errorCursorColor = Red,
                cursorColor = Orange
            ),
            placeholder = {
                PretendardText(
                    fontSize = 14.sp,
                    text = hint,
                    fontWeight = FontWeight.Medium,
                    color = DeepDarkGray,
                )
            },
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontFamily = Font.pretendard,
                fontWeight = FontWeight.Medium,
                color = DeepDarkGray
            ),
            visualTransformation = VisualTransformation.None,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            maxLines = if (isDescription) 4 else 1
        )
    }
}