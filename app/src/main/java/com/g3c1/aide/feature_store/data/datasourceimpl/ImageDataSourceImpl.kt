package com.g3c1.aide.feature_store.data.datasourceimpl

import com.g3c1.aide.feature_store.data.datasource.ImageDataSource
import com.g3c1.aide.remote.api.ImageAPI
import com.g3c1.aide.remote.utils.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import java.io.IOException
import javax.inject.Inject

class ImageDataSourceImpl @Inject constructor(
    private val api: ImageAPI
) : ImageDataSource {
    override suspend fun getImageUrl(file: MultipartBody.Part): Flow<ApiState<String>> {
        return flow {
            try {
                val response = api.getImageUrl(file)
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