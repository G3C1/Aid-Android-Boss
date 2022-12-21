package com.g3c1.aide.feature_account.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g3c1.aide.di.AideBossApplication
import com.g3c1.aide.feature_account.data.dto.req.SignInUserInfoDTO
import com.g3c1.aide.feature_account.data.dto.req.SignUpUserInfoDTO
import com.g3c1.aide.feature_account.data.dto.res.SignInResponseDTO
import com.g3c1.aide.feature_account.domain.usecase.LoginUseCase
import com.g3c1.aide.feature_account.domain.usecase.SignUpUseCase
import com.g3c1.aide.feature_account.presentation.utils.TokenType.*
import com.g3c1.aide.remote.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: LoginUseCase
): ViewModel() {
    val signUpRes: MutableStateFlow<ApiState<Unit>> = MutableStateFlow(ApiState.Loading())
    val signInRes: MutableStateFlow<ApiState<SignInResponseDTO>> =
        MutableStateFlow(ApiState.Loading())

    var userInfo = SignUpUserInfoDTO("", "", "")

    fun bossSignInRequest(id: String, password: String) = viewModelScope.launch {
        signInRes.value = ApiState.Loading()
        signInUseCase.login(
            SignInUserInfoDTO(
                id = id.trim().replace(" ", ""),
                password = password.trim().replace(" ", "")
            )
        ).catch {
            Log.d("SignIn", "body: ${it.message}")
        }.collect { value ->
            AideBossApplication.getInstance().getTokenManager()
                .setTokenData("Bearer " + value.data!!.accessToken, ACCESS)
            AideBossApplication.getInstance().getTokenManager()
                .setTokenData(value.data.refreshToken, REFRESH)
            AideBossApplication.getInstance().getTokenManager()
                .setTokenData(value.data.expiredAt, EXPIRED)
            signInRes.value = value
        }
    }

    fun bossSignUpRequest() = viewModelScope.launch {
        signUpRes.value = ApiState.Loading()
        signUpUseCase.signUp(userInfo)
            .catch {
                Log.d("SignUpRes", "body: ${it.message}")
            }.collect { value ->
                signUpRes.value = value
            }
    }
}