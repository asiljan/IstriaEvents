package com.siljan.domain.usecases.base

import io.reactivex.Single

internal abstract class UseCase<in Params, R> {
    abstract fun buildUseCase(params: Params?): Single<R>
    open fun execute(params: Params? = null): Single<R> = buildUseCase(params)
}