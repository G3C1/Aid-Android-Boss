package com.g3c1.aide.di.module

import com.g3c1.aide.feature_account.data.datasourceimpl.UserDataSourceImpl
import com.g3c1.aide.feature_account.data.repositoryimpl.UserRepositoryImpl
import com.g3c1.aide.feature_account.domain.repository.UserRepository
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

}