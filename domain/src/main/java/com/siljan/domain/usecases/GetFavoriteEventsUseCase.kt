package com.siljan.domain.usecases

import com.siljan.domain.models.Event
import com.siljan.domain.models.Result
import com.siljan.domain.repositories.EventsRepository
import com.siljan.domain.usecases.base.UseCase
import kotlinx.coroutines.flow.Flow

class GetFavoriteEventsUseCase constructor(private val eventsRepository: EventsRepository):
    UseCase<Unit, Result<List<Event>>>() {
    override fun buildUseCase(params: Unit?): Flow<Result<List<Event>>> {
        return eventsRepository.fetchFavoritesEvents()
    }
}