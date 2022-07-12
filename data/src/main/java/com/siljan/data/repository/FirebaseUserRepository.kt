package com.siljan.data.repository

import com.siljan.data.dto.UserModelMapper
import com.siljan.data.models.UserModel
import com.siljan.domain.models.Result
import com.siljan.domain.models.User
import com.siljan.domain.repositories.AuthRepository
import io.reactivex.Single

internal class FirebaseUserRepository(private val userModelDto: UserModelMapper) : AuthRepository {
    override fun checkUserSession(): Single<Result<User>> {
        return Single.just(UserModel(true))
            .map { userModelDto.dataToDomain(it) }
    }
}