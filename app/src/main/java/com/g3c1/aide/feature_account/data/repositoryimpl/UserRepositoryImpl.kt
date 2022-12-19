package com.g3c1.aide.feature_account.data.repositoryimpl

import com.g3c1.aide.feature_account.data.datasource.UserDataSource
import com.g3c1.aide.feature_account.data.dto.req.SignInUserInfoDTO
import com.g3c1.aide.feature_account.data.dto.req.SignUpUserInfoDTO
import com.g3c1.aide.feature_account.data.dto.res.SignInResponseDTO
import com.g3c1.aide.feature_account.domain.repository.UserRepository
import com.g3c1.aide.remote.utils.ApiState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dataSource: UserDataSource
) : UserRepository {
    override suspend fun signUp(body: SignUpUserInfoDTO): Flow<ApiState<Unit>> {
        return dataSource.signUp(body = body)
    }

    override suspend fun login(body: SignInUserInfoDTO): Flow<ApiState<SignInResponseDTO>> {
        return dataSource.login(body = body)
    }
}