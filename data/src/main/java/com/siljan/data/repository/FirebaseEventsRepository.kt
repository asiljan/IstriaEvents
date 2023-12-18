package com.siljan.data.repository

import com.siljan.domain.models.Event
import com.siljan.domain.models.EventsFilter
import com.siljan.domain.models.Result
import com.siljan.domain.repositories.EventsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FirebaseEventsRepository(private val coroutineDispatcher: CoroutineDispatcher): EventsRepository {
    override fun fetchEvents(filter: EventsFilter?): Flow<Result<List<Event>>> {
        return flow {
            val events = if (filter == null) allEvents() else reducedEvents()
            kotlinx.coroutines.delay(1000)
            emit(Result.Success(events) as Result<List<Event>>)
        }.flowOn(coroutineDispatcher)
    }

    private fun allEvents() = listOf(
            Event(
                eventId = "123412121",
                eventName = "Some Crazy Event 1",
                isFavorite = false,
                eventDescription = "Some text description should be here with most important informations about event"
            ),
            Event(
                eventId = "123412133",
                eventName = "Some Crazy Event 2",
                isFavorite = true,
                eventDescription = "Some text description should be here with most important informations about event"
            ),
            Event(
                eventId = "1234121321",
                eventName = "Some Crazy Event 3",
                isFavorite = false,
                eventDescription = "Some text description should be here with most important informations about event"
            ),
            Event(
                eventId = "1234121131",
                eventName = "Some Crazy Event 4",
                isFavorite = true,
                eventDescription = "Some text description should be here with most important informations about event"
            )
        )

    private fun reducedEvents() = listOf(
        Event(
            eventId = "123412121",
            eventName = "Filtered event",
            isFavorite = false,
            eventDescription = "Some text description should be here with most important informations about event"
        ),
        Event(
            eventId = "123412124",
            eventName = "Filtered event 2",
            isFavorite = true,
            eventDescription = "Some text description should be here with most important informations about event"
        ),
        Event(
            eventId = "123412125",
            eventName = "Filtered event 3",
            isFavorite = false,
            eventDescription = "Some text description should be here with most important informations about event"
        )
    )

    private fun favoritesEvents() = listOf(
        Event(
            eventId = "123412126",
            eventName = "Some Crazy Event 2",
            isFavorite = true,
            eventDescription = "Some text description should be here with most important informations about event"
        ),
        Event(
            eventId = "123412126",
            eventName = "Some Crazy Event 4",
            isFavorite = true,
            eventDescription = "Some text description should be here with most important informations about event"
        ),
    )

    override fun fetchTrendingEvents(): Flow<Result<List<Event>>> {
        return flow {
            val data = listOf(
                Event(
                    eventId = "123412128",
                    eventName = "Craazy Popular Event 1",
                    isFavorite = false,
                    eventDescription = "Some text description should be here with most important informations about event"
                ),
                Event(
                    eventId = "123412129",
                    eventName = "Craazy Popular Event 1",
                    isFavorite = false,
                    eventDescription = "Some text description should be here with most important informations about event"
                ),
                Event(
                    eventId = "123412109",
                    eventName = "Craazy Popular Event 1",
                    isFavorite = false,
                    eventDescription = "Some text description should be here with most important informations about event"
                )
            )

            emit(Result.Success(data) as Result<List<Event>>)
        }.flowOn(coroutineDispatcher)
    }

    override fun fetchFavoritesEvents(): Flow<Result<List<Event>>> {
        return flow {
            emit(Result.Success(favoritesEvents()))
        }.flowOn(coroutineDispatcher)
    }
}