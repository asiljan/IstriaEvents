package com.siljan.data.repository

import com.siljan.data.dto.UserModelMapper
import com.siljan.data.models.UserModel
import com.siljan.domain.models.LoginRequest
import com.siljan.domain.models.Result
import com.siljan.domain.models.User
import com.siljan.domain.models.UserMissingException
import com.siljan.domain.models.toDomainError
import com.siljan.domain.repositories.UserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn

class FirebaseUserRepository(
    private val userModelDto: UserModelMapper,
    private val ioDispatcher: CoroutineDispatcher) : UserRepository {
    override fun checkIfUserIsAuthenticated(): Flow<Result<User>> = flow<Result<User>> {
        delay(2500)
        emit(UserMissingException().toDomainError())
    }
        .flowOn(Dispatchers.IO)

    override fun authenticateUserWithEmail(input: LoginRequest): Flow<Result<User>> {
        //TODO implement and execute firebase auth function in order to do proper authentication
        //TODO use catch to catch third party errors and emit something meaningful down the stream
        return flow {
            delay(2000)
            val result = MockAuthData().validateMockAuth(input.userEmail, input.password)

            emit(result?.let {
                userModelDto.dataToDomain(it)
            } ?: Result.Error(UserMissingException()))
        }.flowOn(ioDispatcher)
    }

    override suspend fun fetchAuthenticatedUser(): Result<User> {
        TODO("Not yet implemented")
    }

    private inner class MockAuthData {
        private val userEmail: String = "ivek@ivek.com"
        private val userPassword: String = "123456"


        fun validateMockAuth(email: String, password: String): UserModel? {
            return if (email == userEmail && password == userPassword)
                UserModel(
                    displayName = "Ivica Ivic",
                    avatar = null,
                    email = "ivek@ivek.com"
                )
            else null
        }
    }
}