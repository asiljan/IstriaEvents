package com.siljan.istriaevents.di

import com.siljan.data.dto.UserModelMapper
import com.siljan.data.repository.FirebaseUserRepository
import com.siljan.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesProvider {
    @Provides
    fun provideUserRepository(userModelMapper: UserModelMapper): UserRepository {
        return FirebaseUserRepository(userModelMapper)
    }
}
