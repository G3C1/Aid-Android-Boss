package com.g3c1.aide.feature_store.domain.repository

import com.g3c1.aide.feature_store.data.dto.ImageUrlDTO
import com.g3c1.aide.remote.utils.ApiState
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody

interface ImageRepository {
    suspend fun getImageUrl(file: MultipartBody.Part): Flow<ApiState<ImageUrlDTO>>
}