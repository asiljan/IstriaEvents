package com.siljan.data.dto

import com.siljan.data.models.UserModel
import com.siljan.domain.models.Result
import com.siljan.domain.models.User
import com.siljan.domain.models.UserMissingException

interface UserModelMapper {
    fun dataToDomain(user: UserModel): Result<User>
}

class UserModelMapperImpl : UserModelMapper {
    override fun dataToDomain(user: UserModel): Result<User> {
        //TODO implement mappers, this is just mock data
        return if (user.email.isNotEmpty()) {
            Result.Success(
                User(
                    user.displayName,
                    user.email,
                    false
                )
            )
        } else {
            Result.Error(UserMissingException())
        }
    }
}
