package com.g3c1.aide.feature_store.data.datasourceimpl

import com.g3c1.aide.feature_store.data.datasource.StoreDataSource
import com.g3c1.aide.feature_store.data.dto.MyStoresInfoDTO
import com.g3c1.aide.feature_store.data.dto.StoreInfoDTO
import com.g3c1.aide.remote.api.StoreAPI
import com.g3c1.aide.remote.utils.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class StoreDataSourceImpl @Inject constructor(
    private val api: StoreAPI
): StoreDataSource {
    override suspend fun getMyStoresInfo(): Flow<ApiState<MyStoresInfoDTO>> {
        return flow {
            try {
                val response = api.getStoreInfoList()
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ApiState.Success(data = it, status = response.code()))
                    }
                } else {
                    try {
                        emit(
                            ApiState.Error(
                                response.errorBody()!!.string(),
                                status = response.code()
                            )
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } as Unit
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun addStore(body: StoreInfoDTO): Flow<ApiState<Unit>> {
        return flow {
            try {
                val response = api.addStore(body = body)
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ApiState.Success(data = it, status = response.code()))
                    }
                } else {
                    try {
                        emit(
                            ApiState.Error(
                                response.errorBody()!!.string(),
                                status = response.code()
                            )
                        )
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } as Unit
        }.flowOn(Dispatchers.IO)
    }
}