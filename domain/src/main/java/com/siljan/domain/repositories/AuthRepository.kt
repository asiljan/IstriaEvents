package com.siljan.domain.repositories

import com.siljan.domain.models.Result
import com.siljan.domain.models.User
import io.reactivex.Single

interface AuthRepository {
    fun checkUserSession(): Single<Result<User>>
}