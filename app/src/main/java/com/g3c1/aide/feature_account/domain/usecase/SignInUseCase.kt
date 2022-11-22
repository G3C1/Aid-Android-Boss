package com.g3c1.aide.feature_account.domain.usecase

import com.g3c1.aide.feature_account.data.dto.req.SignInUserInfoDTO
import com.g3c1.aide.feature_account.domain.repository.UserRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun signIn(body: SignInUserInfoDTO) = repository.signIn(body = body)
}