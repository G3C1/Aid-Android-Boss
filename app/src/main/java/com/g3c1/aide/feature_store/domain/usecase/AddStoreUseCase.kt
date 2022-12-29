package com.g3c1.aide.feature_store.domain.usecase

import com.g3c1.aide.feature_store.data.dto.StoreInfoDTO
import com.g3c1.aide.feature_store.domain.repository.StoreRepository
import javax.inject.Inject

class AddStoreUseCase @Inject constructor(
    private val repository: StoreRepository
) {
    suspend fun addStore(body: StoreInfoDTO) = repository.addStore(body = body)
}