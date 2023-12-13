package com.siljan.istriaevents.ui.home

import com.siljan.domain.models.Event
import com.siljan.istriaevents.common.BaseIntent
import com.siljan.istriaevents.common.BaseViewState
import com.siljan.istriaevents.ui.models.EventsFilter

sealed class EventsUIState : BaseViewState {
    object EventsFetching : EventsUIState()
    object EventsFetchingError : EventsUIState()
    data class EventsFetched(val data: List<Event>) : EventsUIState()
}

sealed class EventsIntent : BaseIntent {
    object FetchAllEvents : EventsIntent()

    object FetchFavoritesEvents : EventsIntent()
//    object FetchTrendingEvents : EventsIntent()
    data class FetchEvents(val filter: EventsFilter) : EventsIntent()
}