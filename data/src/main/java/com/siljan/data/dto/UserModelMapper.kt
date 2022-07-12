package com.siljan.data.dto

import com.siljan.data.models.UserModel
import com.siljan.domain.models.Result
import com.siljan.domain.models.User
import com.siljan.domain.models.UserMissingException

internal interface UserModelMapper {
    fun dataToDomain(user: UserModel): Result<User>
}

internal class UserModelMapperImpl : UserModelMapper {
    override fun dataToDomain(user: UserModel): Result<User> {
        return if (user.userExists) {
            Result.Success(User(true))
        } else {
            Result.Error(UserMissingException())
        }
    }
}
