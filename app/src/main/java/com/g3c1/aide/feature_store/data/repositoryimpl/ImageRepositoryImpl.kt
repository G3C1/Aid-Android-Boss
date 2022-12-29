package com.g3c1.aide.feature_store.data.repositoryimpl

import com.g3c1.aide.feature_store.data.datasource.ImageDataSource
import com.g3c1.aide.feature_store.data.dto.ImageUrlDTO
import com.g3c1.aide.feature_store.domain.repository.ImageRepository
import com.g3c1.aide.remote.utils.ApiState
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import javax.inject.Inject

class ImageRepositoryImpl @Inject constructor(
    private val dataSoure: ImageDataSource
): ImageRepository {
    override suspend fun getImageUrl(file: MultipartBody.Part): Flow<ApiState<ImageUrlDTO>> {
        return dataSoure.getImageUrl(file)
    }
}