package com.g3c1.aide.remote.api

import com.g3c1.aide.feature_account.data.dto.req.SignInUserInfoDTO
import com.g3c1.aide.feature_account.data.dto.req.SignUpUserInfoDTO
import com.g3c1.aide.feature_account.data.dto.res.SignInResponseDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAPI {
    @POST("v2/user/")
    suspend fun signUp(
        @Body body: SignUpUserInfoDTO
    ): Response<Unit>

    @POST("v2/user/login")
    suspend fun signIn(
        @Body body: SignInUserInfoDTO
    ): Response<SignInResponseDTO>
}