package com.siljan.istriaevents.di

import com.siljan.data.dto.UserModelMapper
import com.siljan.data.dto.UserModelMapperImpl
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
}

