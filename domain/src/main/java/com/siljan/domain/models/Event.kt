package com.siljan.domain.models

data class Event(
    val eventName: String,
    val isFavorite: Boolean,
    val eventDescription: String
) {
}