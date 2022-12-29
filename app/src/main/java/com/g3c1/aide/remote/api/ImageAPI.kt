package com.g3c1.aide.remote.api

import com.g3c1.aide.feature_store.data.dto.ImageUrlDTO
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageAPI {
    @Multipart
    @POST("v2/image")
    suspend fun getImageUrl(
        @Part file: MultipartBody.Part
    ): Response<ImageUrlDTO>
}