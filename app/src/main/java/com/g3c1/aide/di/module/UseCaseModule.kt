package com.g3c1.aide.di.module

import com.g3c1.aide.feature_account.domain.repository.UserRepository
import com.g3c1.aide.feature_account.domain.usecase.SignInUseCase
import com.g3c1.aide.feature_account.domain.usecase.SignUpUseCase
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
    fun provideSignInpUseCase(repository: UserRepository): SignInUseCase =
        SignInUseCase(repository)
}