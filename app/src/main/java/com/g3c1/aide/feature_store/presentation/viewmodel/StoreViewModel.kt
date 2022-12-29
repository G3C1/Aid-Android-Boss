package com.g3c1.aide.feature_store.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.g3c1.aide.feature_store.data.dto.ImageUrlDTO
import com.g3c1.aide.feature_store.data.dto.MyStoresInfoDTO
import com.g3c1.aide.feature_store.data.dto.StoreInfoDTO
import com.g3c1.aide.feature_store.domain.usecase.AddStoreUseCase
import com.g3c1.aide.feature_store.domain.usecase.GetImageUrlUseCase
import com.g3c1.aide.feature_store.domain.usecase.GetMyStoresInfoUseCase
import com.g3c1.aide.remote.utils.ApiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class StoreViewModel @Inject constructor(
    private val getMyStoresInfoUseCase: GetMyStoresInfoUseCase,
    private val getImageUrlUseCase: GetImageUrlUseCase,
    private val addStoreUseCase: AddStoreUseCase
): ViewModel() {
    val getMyStoresRes: MutableStateFlow<ApiState<MyStoresInfoDTO>> =
        MutableStateFlow(ApiState.Loading())

    val gerImageUrlRes: MutableStateFlow<ApiState<ImageUrlDTO>> =
        MutableStateFlow(ApiState.Loading())

    val addStoreRes: MutableStateFlow<ApiState<Unit>> =
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

    fun getImageUrl(file: MultipartBody.Part) = viewModelScope.launch {
        gerImageUrlRes.value = ApiState.Loading()
        getImageUrlUseCase.getImageUrl(file)
            .catch {
                Log.d("GetImageUrl", "body: ${it.message}")
            }.collect { value ->
                gerImageUrlRes.value = value
            }
    }

    fun addStore(name: String, description: String, imageUrl: String) = viewModelScope.launch {
        addStoreRes.value = ApiState.Loading()
        addStoreUseCase.addStore(
            StoreInfoDTO(
                name = name,
                description = description,
                img = imageUrl
            )
        ).catch {
            Log.d("AddStore", "body: ${it.message}")
        }.collect { value ->
            addStoreRes.value = value
        }
    }
}