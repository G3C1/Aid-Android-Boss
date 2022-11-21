package com.g3c1.aide.remote.api

import com.g3c1.aide.feature_account.data.dto.SignUpUserInfoDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {
    @POST("v1/")
    suspend fun signUp(
        @Body body: SignUpUserInfoDTO
    ): Response<Unit>
}