package com.g3c1.aide.feature_account.presentation.ui.screen

import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.lifecycle.LifecycleCoroutineScope
import com.g3c1.aide.feature_account.presentation.ui.components.AccountButton
import com.g3c1.aide.feature_account.presentation.ui.components.InputField
import com.g3c1.aide.feature_account.presentation.ui.components.OnClickText
import com.g3c1.aide.feature_account.presentation.viewmodel.AccountViewModel
import com.g3c1.aide.remote.utils.ApiState
import com.g3c1.aide.ui.theme.PretendardText
import kotlinx.coroutines.launch

@Composable
fun LoginPage(
    viewModel: AccountViewModel,
    lifecycleScope: LifecycleCoroutineScope,
    context: Context,
    goSignUpScreen: () -> Unit
) {
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
        isError = id.value.isEmpty() || password.value.isEmpty(),
    ) {
        viewModel.bossSignInRequest(
            id = id.value,
            password = password.value
        )
        bossSignInRequest(lifecycleScope, viewModel, context) {
            Log.d("SignIn", "로그인 성공!")
        }
    }
}

private fun bossSignInRequest(
    lifecycleScope: LifecycleCoroutineScope,
    viewModel: AccountViewModel,
    context: Context,
    success: () -> Unit,
) {
    lifecycleScope.launch {
        viewModel.signInRes.collect {
            when (it) {
                is ApiState.Success -> {
                    success()
                }
                is ApiState.Error -> {
                    Log.d("SignIn", it.message.toString())
                    when (it.status) {
                        400 -> {
                            Toast.makeText(
                                context,
                                "비밀번호가 일치하지 않습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        404 -> {
                            Toast.makeText(
                                context,
                                "아이디를 찾을 수 없습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        else -> {
                            Toast.makeText(
                                context,
                                "알수 없는 오류가 발생했습니다.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    viewModel.signUpRes.value = ApiState.Loading()
                }
                is ApiState.Loading -> {}
            }
        }
    }
}