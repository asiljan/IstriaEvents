package com.siljan.domain.models

data class LoginRequest(
    val userEmail: String,
    val password: String
)
