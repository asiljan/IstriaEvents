package com.siljan.domain.usecases

import com.siljan.domain.models.Event
import com.siljan.domain.models.EventsFilter
import com.siljan.domain.models.Result
import com.siljan.domain.repositories.EventsRepository
import com.siljan.domain.usecases.base.UseCase
import kotlinx.coroutines.flow.Flow

class GetAllEventsUseCase constructor(private val repository: EventsRepository) : UseCase<EventsFilter?, Result<List<Event>>>() {
    override fun buildUseCase(params: EventsFilter?): Flow<Result<List<Event>>> {
        //TODO if req.params is null, then fetch all events
        return repository.fetchEvents(params)
    }
}