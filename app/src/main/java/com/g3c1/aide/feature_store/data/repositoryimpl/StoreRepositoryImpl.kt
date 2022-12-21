package com.g3c1.aide.feature_store.data.repositoryimpl

import com.g3c1.aide.feature_store.data.datasource.StoreDataSource
import com.g3c1.aide.feature_store.data.dto.MyStoresInfoDTO
import com.g3c1.aide.feature_store.domain.repository.StoreRepository
import com.g3c1.aide.remote.utils.ApiState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val dataSource: StoreDataSource
): StoreRepository {
    override suspend fun getMyStoresInfo(): Flow<ApiState<MyStoresInfoDTO>> {
        return dataSource.getMyStoresInfo()
    }
}