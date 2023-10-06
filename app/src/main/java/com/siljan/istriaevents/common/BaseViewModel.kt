package com.siljan.istriaevents.common

import androidx.lifecycle.LiveData
import com.siljan.istriaevents.mvibase.MviEvent

/**
 * Object that will subscribes to a [BaseView]'s [BaseIntent]s,
 * process it and emit a [BaseViewState] back.
 *
 * @param I Top class of the [BaseIntent] that the [BaseViewModel] will be subscribing
 * to.
 * @param S Top class of the [BaseViewState] the [BaseViewModel] will be emitting.
 */
interface BaseViewModel<I: BaseIntent, S: BaseViewState> {
    fun processIntent(intent: I)

    fun states(): LiveData<S>
}

/**
 * In addition to [BaseViewModel] this class uses [MviEvent] as a third param
 *
 * @param E Top class of the [MviEvent] the [BaseViewModel] will be emitting
 */
interface BaseEventViewModel<I: BaseIntent, S: BaseViewState, E: MviEvent>: BaseViewModel<I, S> {
    fun events(): SingleLiveEvent<E>
}