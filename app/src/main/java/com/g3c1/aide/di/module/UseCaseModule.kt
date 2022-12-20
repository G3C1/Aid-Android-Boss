package com.g3c1.aide.di.module

import com.g3c1.aide.feature_account.domain.repository.UserRepository
import com.g3c1.aide.feature_account.domain.usecase.LoginUseCase
import com.g3c1.aide.feature_account.domain.usecase.SignUpUseCase
import com.g3c1.aide.feature_store.domain.repository.StoreRepository
import com.g3c1.aide.feature_store.domain.usecase.GetMyStoresInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideSignUpUseCase(repository: UserRepository): SignUpUseCase =
        SignUpUseCase(repository)

    @Provides
    @Singleton
    fun provideSignInUseCase(repository: UserRepository): LoginUseCase =
        LoginUseCase(repository)

    @Provides
    @Singleton
    fun provideGetMyStoresInfoUseCase(repository: StoreRepository): GetMyStoresInfoUseCase =
        GetMyStoresInfoUseCase(repository)
}