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
        //TODO implement mappers, this is just mock data
        return if (user.email.isNotEmpty()) {
            Result.Success(User(true))
        } else {
            Result.Error(UserMissingException())
        }
    }
}
