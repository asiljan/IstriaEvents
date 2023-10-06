package com.siljan.domain.usecases

import com.siljan.domain.models.Result
import com.siljan.domain.models.User
import com.siljan.domain.repositories.UserRepository
import com.siljan.domain.usecases.base.UseCase
import kotlinx.coroutines.flow.Flow

open class CheckUserUseCase(
    private val userRepository: UserRepository) : UseCase<Unit, Result<User>>() {
    override fun buildUseCase(params: Unit?): Flow<Result<User>> {
        return userRepository.checkIfUserIsAuthenticated()
    }
}
