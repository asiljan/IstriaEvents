package com.siljan.istriaevents.di

import com.siljan.data.dto.UserModelMapper
import com.siljan.data.repository.FirebaseEventsRepository
import com.siljan.data.repository.FirebaseUserRepository
import com.siljan.domain.repositories.EventsRepository
import com.siljan.domain.repositories.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class RepositoriesProvider {
    @Provides
    fun provideUserRepository(userModelMapper: UserModelMapper, @IoDispatcher ioDispatcher: CoroutineDispatcher): UserRepository {
        return FirebaseUserRepository(userModelMapper, ioDispatcher)
    }

    @Provides
    fun provideEventsRepository(@IoDispatcher ioDispatcher: CoroutineDispatcher): EventsRepository {
        return FirebaseEventsRepository(ioDispatcher)
    }
}
