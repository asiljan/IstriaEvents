package com.siljan.istriaevents.ui.models

data class EventsFilter(
    val cityId: String,
    val cityName: String,
    val includePaidEvents: Boolean,
    val date: CustomDate
) {

    data class CustomDate(
        val dateFilter: DateFilter,
        val range: Pair<Long, Long>
    )
    sealed class DateFilter(val value: String) {
        object DateToday : DateFilter("Today")
        object DateTmrrw : DateFilter("Tomorrow")
        object DateWeekend : DateFilter("Weekend")
        object DateRange: DateFilter("Custom")

        companion object {
            fun getFilter(value: String): DateFilter =
                DateFilter::class.sealedSubclasses
                    .map { it.objectInstance  }
                    .find { it?.value == value } ?: DateToday
        }
    }
}
