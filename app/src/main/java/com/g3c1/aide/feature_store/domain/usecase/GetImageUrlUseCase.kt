package com.g3c1.aide.feature_store.domain.usecase

import com.g3c1.aide.feature_store.domain.repository.ImageRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class GetImageUrlUseCase @Inject constructor(
    private val repository: ImageRepository
) {
    suspend fun getImageUrl(file: MultipartBody.Part) = repository.getImageUrl(file)
}