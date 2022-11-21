package com.g3c1.aide.feature_account.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g3c1.aide.feature_account.data.dto.SignUpUserInfoDTO
import com.g3c1.aide.feature_account.domain.usecase.SignUpUseCase
import com.g3c1.aide.remote.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    val signUpRes: MutableStateFlow<ApiState<Unit>> = MutableStateFlow(ApiState.Loading())
    var userInfo = SignUpUserInfoDTO("", "", "")

    fun login(id: String, password: String) {

    }

    fun signUp() = viewModelScope.launch {
        signUpRes.value = ApiState.Loading()
        signUpUseCase.signUp(userInfo)
            .catch {
                Log.d("SignUpRes", "status: ${signUpRes.value.status}")
            }.collect { value ->
                signUpRes.value = value
            }
    }
}