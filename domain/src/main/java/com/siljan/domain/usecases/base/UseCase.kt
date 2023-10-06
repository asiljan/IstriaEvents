package com.siljan.domain.usecases.base

import kotlinx.coroutines.flow.Flow

abstract class UseCase<in Params, R> {
    internal abstract fun buildUseCase(params: Params?): Flow<R>
    open fun execute(params: Params? = null): Flow<R> = buildUseCase(params)
}