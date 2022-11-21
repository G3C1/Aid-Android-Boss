package com.g3c1.aide.feature_account.data.datasourceimpl

import com.g3c1.aide.feature_account.data.datasource.UserDataSource
import com.g3c1.aide.feature_account.data.dto.SignUpUserInfoDTO
import com.g3c1.aide.remote.api.UserAPI
import com.g3c1.aide.remote.utils.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.io.IOException
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val api: UserAPI
) : UserDataSource {
    override suspend fun signUp(body: SignUpUserInfoDTO): Flow<ApiState<Unit>> {
        return flow {
            try {
                val response = api.signUp(body = body)
                if (response.isSuccessful) {
                    response.body()?.let {
                        emit(ApiState.Success(it, status = response.code()))
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