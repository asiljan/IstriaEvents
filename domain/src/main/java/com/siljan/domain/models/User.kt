package com.siljan.domain.models

data class User(
    val displayName: String,
    val email: String,
    val isGuestUser: Boolean
)