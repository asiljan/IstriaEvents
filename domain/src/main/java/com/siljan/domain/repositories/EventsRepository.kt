package com.siljan.domain.repositories

import com.siljan.domain.models.Event
import com.siljan.domain.models.EventsFilter
import com.siljan.domain.models.Result
import kotlinx.coroutines.flow.Flow

interface EventsRepository {
    fun fetchEvents(filter: EventsFilter? = null): Flow<Result<List<Event>>>
    fun fetchTrendingEvents(): Flow<Result<List<Event>>>

    fun fetchFavoritesEvents(): Flow<Result<List<Event>>>
}