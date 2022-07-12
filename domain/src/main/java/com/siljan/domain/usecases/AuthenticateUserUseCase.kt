package com.siljan.domain.usecases

import com.siljan.domain.models.Result
import com.siljan.domain.models.User
import com.siljan.domain.models.toDomainError
import com.siljan.domain.repositories.AuthRepository
import com.siljan.domain.usecases.base.UseCase
import io.reactivex.Single

internal class AuthenticateUserUseCase(
    private val authRepository: AuthRepository) : UseCase<Unit, Result<User>>() {
    override fun buildUseCase(params: Unit?): Single<Result<User>> {
        return authRepository.checkUserSession()
            .onErrorReturn { it.toDomainError() }
    }
}