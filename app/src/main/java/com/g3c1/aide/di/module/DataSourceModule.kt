package com.g3c1.aide.di.module

import com.g3c1.aide.feature_account.data.datasourceimpl.UserDataSourceImpl
import com.g3c1.aide.feature_store.data.datasourceimpl.ImageDataSourceImpl
import com.g3c1.aide.feature_store.data.datasourceimpl.StoreDataSourceImpl
import com.g3c1.aide.remote.api.ImageAPI
import com.g3c1.aide.remote.api.StoreAPI
import com.g3c1.aide.remote.api.UserAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideUserDataSource(api: UserAPI) = UserDataSourceImpl(api = api)

    @Provides
    @Singleton
    fun provideStoreDataSource(api: StoreAPI) = StoreDataSourceImpl(api = api)

    @Provides
    @Singleton
    fun provideImageDataSource(api: ImageAPI) = ImageDataSourceImpl(api = api)
}