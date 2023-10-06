package com.siljan.data.repository

import com.siljan.data.dto.UserModelMapper
import com.siljan.data.models.UserModel
import com.siljan.domain.models.Result
import com.siljan.domain.models.User
import com.siljan.domain.models.UserMissingException
import com.siljan.domain.models.toDomainError
import com.siljan.domain.repositories.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn

class FirebaseUserRepository(private val userModelDto: UserModelMapper) : UserRepository {
    override fun checkIfUserIsAuthenticated(): Flow<Result<User>> = flow<Result<User>> {
        delay(2500)
        emit(UserMissingException().toDomainError())
    }
        .flowOn(Dispatchers.IO)

    override suspend fun authenticateUserWithEmail(
        emailInput: String,
        passwordInput: String
    ): Result<User> {
        //TODO implement and execute firebase auth function in order to do proper authentication
        return userModelDto.dataToDomain(UserModel(
            displayName = "Ivica Ivic",
            avatar = null,
            email = "ivek@ivek.com"
        ))
    }

    override suspend fun fetchAuthenticatedUser(): Result<User> {
        TODO("Not yet implemented")
    }
}