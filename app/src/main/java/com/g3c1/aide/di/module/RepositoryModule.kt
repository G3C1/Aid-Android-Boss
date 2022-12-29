package com.g3c1.aide.di.module

import com.g3c1.aide.feature_account.data.datasourceimpl.UserDataSourceImpl
import com.g3c1.aide.feature_account.data.repositoryimpl.UserRepositoryImpl
import com.g3c1.aide.feature_account.domain.repository.UserRepository
import com.g3c1.aide.feature_store.data.datasourceimpl.ImageDataSourceImpl
import com.g3c1.aide.feature_store.data.datasourceimpl.StoreDataSourceImpl
import com.g3c1.aide.feature_store.data.repositoryimpl.ImageRepositoryImpl
import com.g3c1.aide.feature_store.data.repositoryimpl.StoreRepositoryImpl
import com.g3c1.aide.feature_store.domain.repository.ImageRepository
import com.g3c1.aide.feature_store.domain.repository.StoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideUserRepository(dataSource: UserDataSourceImpl): UserRepository =
        UserRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideStoreRepository(dataSource: StoreDataSourceImpl): StoreRepository =
        StoreRepositoryImpl(dataSource)

    @Provides
    @Singleton
    fun provideImageRepository(dataSource: ImageDataSourceImpl): ImageRepository =
        ImageRepositoryImpl(dataSource)
}