package com.g3c1.aide.feature_account.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.g3c1.aide.feature_account.presentation.ui.components.AccountButton
import com.g3c1.aide.feature_account.presentation.ui.components.InputField
import com.g3c1.aide.feature_account.presentation.ui.components.OnClickText
import com.g3c1.aide.feature_account.presentation.viewmodel.AccountViewModel
import com.g3c1.aide.ui.theme.PretendardText

@Composable
fun LoginPage(viewModel: AccountViewModel, goSignUpScreen: () -> Unit) {
    val id = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PretendardText(
            text = "로그인",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.size(12.dp))
        PretendardText(
            text = "이앱은 AiD 사장님들을 위한 \n" +
                    "가게 관리앱이에요.",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
        Spacer(modifier = Modifier.size(36.dp))
        InputField(
            text = id.value,
            hint = "아이디를 입력해주세요.",
            isError = false,
            onValueChange = {
                id.value = it
            },
            errorMsg = "",
            isPassword = false
        )
        Spacer(modifier = Modifier.size(12.dp))
        InputField(
            text = password.value,
            hint = "비밀번호를 입력해주세요.",
            isError = false,
            onValueChange = {
                password.value = it
            },
            errorMsg = "",
            isPassword = true
        )
        Spacer(modifier = Modifier.size(16.dp))
        OnClickText(
            firstText = "처음이신가요? ",
            orangeText = "회원가입",
            lastText = "하러가기",
            onClick = goSignUpScreen
        )
    }
    AccountButton(
        text = "로그인",
        isError = id.value.isEmpty() && id.value.isEmpty(),
    ) {

    }
}