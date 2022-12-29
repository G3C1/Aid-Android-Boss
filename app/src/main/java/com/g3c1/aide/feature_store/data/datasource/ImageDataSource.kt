package com.g3c1.aide.feature_store.data.datasource

import com.g3c1.aide.remote.utils.ApiState
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface ImageDataSource {
    suspend fun getImageUrl(file: MultipartBody.Part): Flow<ApiState<String>>
}