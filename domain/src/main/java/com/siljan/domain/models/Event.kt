package com.siljan.domain.models

data class Event(
    val eventId: String,
    val eventName: String,
    val isFavorite: Boolean,
    val eventDescription: String
)
