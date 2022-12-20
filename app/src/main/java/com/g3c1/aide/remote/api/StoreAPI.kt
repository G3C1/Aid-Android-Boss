package com.g3c1.aide.remote.api

import com.g3c1.aide.feature_store.data.dto.MyStoresInfoDTO
import retrofit2.Response
import retrofit2.http.GET

interface StoreAPI {
    @GET("v2/store/")
    suspend fun getStoreInfoList(): Response<MyStoresInfoDTO>
}