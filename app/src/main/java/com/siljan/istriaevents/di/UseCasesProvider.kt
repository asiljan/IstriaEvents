package com.siljan.istriaevents.di

import com.siljan.domain.repositories.UserRepository
import com.siljan.domain.usecases.CheckUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCasesProvider {
    @Provides
    @ViewModelScoped
    fun provideCheckUserUseCase(userRepository: UserRepository): CheckUserUseCase {
        return CheckUserUseCase(userRepository)
    }
}
