package com.g3c1.aide.feature_account.domain.usecase

import com.g3c1.aide.feature_account.data.dto.SignUpUserInfoDTO
import com.g3c1.aide.feature_account.domain.repository.UserRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend fun signUp(body: SignUpUserInfoDTO) = repository.signUp(body = body)
}