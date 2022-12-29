package com.g3c1.aide.feature_store.data.datasource

import com.g3c1.aide.feature_store.data.dto.MyStoresInfoDTO
import com.g3c1.aide.feature_store.data.dto.StoreInfoDTO
import com.g3c1.aide.remote.utils.ApiState
import kotlinx.coroutines.flow.Flow

interface StoreDataSource {
    suspend fun getMyStoresInfo(): Flow<ApiState<MyStoresInfoDTO>>

    suspend fun addStore(body: StoreInfoDTO): Flow<ApiState<Unit>>
}