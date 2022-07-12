package com.siljan.domain.usecases.base

import io.reactivex.Completable

internal abstract class CompletableUseCase<in Params> {
    abstract fun buildUseCase(params: Params?): Completable
    open fun execute(params: Params? = null): Completable = buildUseCase(params)
}