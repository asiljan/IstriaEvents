package com.siljan.istriaevents.mvibase

import com.siljan.istriaevents.common.BaseIntent
import com.siljan.istriaevents.common.BaseViewState

/**
 * Object representing a UI that will
 * a) emit its intents to a view model,
 * b) subscribes to a view model for rendering its UI.
 *
 * @param I Top class of the [BaseIntent] that the [BaseView] will be emitting.
 * @param S Top class of the [BaseViewState] the [BaseView] will be subscribing to.
 */
interface MviEventView<I : BaseIntent, in S : BaseViewState, E: MviEvent> {
    /**
     * Entry point for the [BaseView] to render itself based on a [BaseViewState].
     */
    fun render(state: S)

    /**
     * Entry point for the [BaseView] to show event based on [MviEvent]
     */
    fun events(event: E)
}
