package com.g3c1.aide.feature_store.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g3c1.aide.feature_store.data.dto.MyStoresInfoDTO
import com.g3c1.aide.feature_store.domain.usecase.GetMyStoresInfoUseCase
import com.g3c1.aide.remote.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val getMyStoresInfoUseCase: GetMyStoresInfoUseCase
) : ViewModel() {
    val getMyStoresRes: MutableStateFlow<ApiState<MyStoresInfoDTO>> =
        MutableStateFlow(ApiState.Loading())

    fun getMyStoresInfoRequest() = viewModelScope.launch {
        getMyStoresRes.value = ApiState.Loading()
        getMyStoresInfoUseCase.getMyStoresInfo()
            .catch {
                Log.d("SignUpRes", "body: ${it.message}")
            }.collect { value ->
                getMyStoresRes.value = value
            }
    }
}