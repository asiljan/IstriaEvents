package com.siljan.domain.models

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error<out T>(val throwable: Throwable) : Result<T>()
}

fun <T> Throwable.toDomainError(): Result.Error<T> =
    Result.Error(this)

class UserMissingException : Throwable("User is not authenticated user")