package com.siljan.istriaevents.di

import com.siljan.domain.repositories.EventsRepository
import com.siljan.domain.repositories.UserRepository
import com.siljan.domain.usecases.AuthenticateUserUseCase
import com.siljan.domain.usecases.CheckUserUseCase
import com.siljan.domain.usecases.GetAllEventsUseCase
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

    @Provides
    @ViewModelScoped
    fun provideAuthenticateUserUseCase(userRepository: UserRepository): AuthenticateUserUseCase {
        return AuthenticateUserUseCase(userRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideEventsUseCase(eventsRepository: EventsRepository): GetAllEventsUseCase {
        return GetAllEventsUseCase(eventsRepository)
    }
}
