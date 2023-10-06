package com.siljan.istriaevents.common

/**
 * Object representing a UI that will
 * a) emit its intents to a view model,
 * b) subscribes to a view model for rendering its UI.
 *
 * @param I Top class of the [BaseIntent] that the [BaseView] will be emitting.
 * @param S Top class of the [BaseViewState] the [BaseView] will be subscribing to.
 */
interface BaseView<I : BaseIntent, in S : BaseViewState> {
    /**
     * Entry point for the [BaseView] to render itself based on a [BaseViewState].
     */
    fun render(state: S)
}