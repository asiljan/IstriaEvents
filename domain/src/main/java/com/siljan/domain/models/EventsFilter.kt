package com.siljan.domain.models

data class EventsFilter(
    val cityId: String,
    val dateRange: DateRange
) {
    data class DateRange(val from: Long, val to: Long)
}
