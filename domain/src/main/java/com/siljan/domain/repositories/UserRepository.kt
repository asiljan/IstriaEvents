package com.siljan.domain.repositories

import com.siljan.domain.models.LoginRequest
import com.siljan.domain.models.Result
import com.siljan.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun checkIfUserIsAuthenticated(): Flow<Result<User>>
    fun authenticateUserWithEmail(input: LoginRequest): Flow<Result<User>>
    suspend fun fetchAuthenticatedUser(): Result<User>
}