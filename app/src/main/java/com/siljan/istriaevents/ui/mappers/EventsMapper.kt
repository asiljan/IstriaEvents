package com.siljan.istriaevents.ui.mappers

import com.siljan.istriaevents.ui.models.EventsFilter

class EventsMapper {
    fun uiModelToDomain(filter: EventsFilter): com.siljan.domain.models.EventsFilter {
        return com.siljan.domain.models.EventsFilter(
            cityId = filter.cityId,
            dateRange = when(filter.date) {
                EventsFilter.DateFilter.DateToday -> com.siljan.domain.models.EventsFilter.DateRange(122382372,3232322)
                EventsFilter.DateFilter.DateTmrrw -> com.siljan.domain.models.EventsFilter.DateRange(232323232,2323232)
                EventsFilter.DateFilter.DateWeekend -> com.siljan.domain.models.EventsFilter.DateRange(32433443, 2324242234)
                else -> com.siljan.domain.models.EventsFilter.DateRange(3243434, 23232323)
            }
        )
    }
}