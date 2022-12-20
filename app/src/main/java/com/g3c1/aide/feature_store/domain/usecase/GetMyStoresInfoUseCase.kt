package com.g3c1.aide.feature_store.domain.usecase

import com.g3c1.aide.feature_store.domain.repository.StoreRepository
import javax.inject.Inject

class GetMyStoresInfoUseCase @Inject constructor(
    private val repository: StoreRepository
) {
    suspend fun getMyStoresInfo() = repository.getMyStoresInfo()
}