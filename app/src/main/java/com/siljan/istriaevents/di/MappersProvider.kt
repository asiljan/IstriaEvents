package com.siljan.istriaevents.di

import com.siljan.data.dto.UserModelMapper
import com.siljan.data.dto.UserModelMapperImpl
import com.siljan.istriaevents.ui.mappers.EventsMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MappersProvider {
    @Provides
    fun provideUserModelMapper(): UserModelMapper = UserModelMapperImpl()

    @Provides
    fun providesEventsFilterModelMapper(): EventsMapper = EventsMapper()
}

