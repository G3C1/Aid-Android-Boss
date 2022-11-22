package com.g3c1.aide.feature_account.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g3c1.aide.feature_account.data.dto.req.SignInUserInfoDTO
import com.g3c1.aide.feature_account.data.dto.req.SignUpUserInfoDTO
import com.g3c1.aide.feature_account.data.dto.res.SignInResponseDTO
import com.g3c1.aide.feature_account.domain.usecase.SignInUseCase
import com.g3c1.aide.feature_account.domain.usecase.SignUpUseCase
import com.g3c1.aide.remote.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    val signUpRes: MutableStateFlow<ApiState<Unit>> = MutableStateFlow(ApiState.Loading())
    val signInRes: MutableStateFlow<ApiState<SignInResponseDTO>> =
        MutableStateFlow(ApiState.Loading())

    var userInfo = SignUpUserInfoDTO("", "", "")

    fun signIn(id: String, password: String) = viewModelScope.launch {
        Log.d("SignIn", "id: $id, password: $password")
        signInRes.value = ApiState.Loading()
        signInUseCase.signIn(SignInUserInfoDTO(id = id, password = password))
            .catch {
                Log.d("SignIn", "body: ${it.message}")
            }.collect { value ->
                signInRes.value = value
            }
    }

    fun signUp() = viewModelScope.launch {
        Log.d("SignUpRes", userInfo.toString())
        signUpRes.value = ApiState.Loading()
        signUpUseCase.signUp(userInfo)
            .catch {
                Log.d("SignUpRes", "body: ${it.message}")
            }.collect { value ->
                signUpRes.value = value
            }
    }
}