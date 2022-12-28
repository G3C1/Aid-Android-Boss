package com.g3c1.aide.remote.api

import com.g3c1.aide.feature_store.data.dto.MyStoresInfoDTO
import com.g3c1.aide.feature_store.data.dto.StoreInfoDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface StoreAPI {
    @GET("v2/store")
    suspend fun getStoreInfoList(): Response<MyStoresInfoDTO>

    @POST("v2/store")
    suspend fun createStore(): Response<StoreInfoDTO>
}