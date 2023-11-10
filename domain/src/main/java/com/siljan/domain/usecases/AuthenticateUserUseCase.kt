package com.siljan.domain.usecases

import com.siljan.domain.models.LoginRequest
import com.siljan.domain.models.Result
import com.siljan.domain.models.User
import com.siljan.domain.repositories.UserRepository
import com.siljan.domain.usecases.base.UseCase
import kotlinx.coroutines.flow.Flow

open class AuthenticateUserUseCase constructor(private val userRepository: UserRepository) : UseCase<LoginRequest, Result<User>>() {
    override fun buildUseCase(params: LoginRequest?): Flow<Result<User>> {
        requireNotNull(params)
        return userRepository.authenticateUserWithEmail(params)
    }
}