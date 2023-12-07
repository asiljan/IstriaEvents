package com.siljan.istriaevents.ui.models

data class EventsFilter(
    val includePaidEvents: Boolean,
    val date: DateFilter
) {
    sealed class DateFilter(val value: String) {
        object DateToday : DateFilter("Today")
        object DateTmrrw : DateFilter("Tomorrow")
        object DateWeekend : DateFilter("Weekend")
        data class DateRange(val from: Long, val to: Long) : DateFilter("Custom")

        companion object {
            fun getFilter(value: String): DateFilter =
                DateFilter::class.sealedSubclasses
                    .map { it.objectInstance  }
                    .find { ((it ?: DateToday).value == value) } ?: DateToday
        }
    }
}
