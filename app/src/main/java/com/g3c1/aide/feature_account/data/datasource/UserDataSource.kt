package com.g3c1.aide.feature_account.data.datasource

import com.g3c1.aide.feature_account.data.dto.req.SignInUserInfoDTO
import com.g3c1.aide.feature_account.data.dto.req.SignUpUserInfoDTO
import com.g3c1.aide.feature_account.data.dto.res.SignInResponseDTO
import com.g3c1.aide.remote.utils.ApiState
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    suspend fun signUp(body: SignUpUserInfoDTO): Flow<ApiState<Unit>>

    suspend fun login(body: SignInUserInfoDTO): Flow<ApiState<SignInResponseDTO>>
}