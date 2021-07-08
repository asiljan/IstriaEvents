package com.siljan.istriaevents.mvibase

import androidx.lifecycle.LiveData
import com.siljan.istriaevents.common.SingleLiveEvent

/**
 * Object that will subscribes to a [MviView]'s [MviIntent]s,
 * process it and emit a [MviViewState] back.
 *
 * @param I Top class of the [MviIntent] that the [MviViewModel] will be subscribing
 * to.
 * @param S Top class of the [MviViewState] the [MviViewModel] will be emitting.
 */
interface MviViewModel<I: MviIntent, S: MviViewState> {
    fun processIntent(intent: I)

    fun states(): LiveData<S>
}

/**
 * In addition to [MviViewModel] this class uses [MviEvent] as a third param
 *
 * @param E Top class of the [MviEvent] the [MviViewModel] will be emitting
 */
interface MviEventViewModel<I: MviIntent, S: MviViewState, E: MviEvent>: MviViewModel<I, S> {
    fun events(): SingleLiveEvent<E>
}