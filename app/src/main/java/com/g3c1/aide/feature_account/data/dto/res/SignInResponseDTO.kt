package com.g3c1.aide.feature_account.data.dto.res

data class SignInResponseDTO(
    val accessToken: String,
    val refreshToken: String,
    val expiredAt: String
)