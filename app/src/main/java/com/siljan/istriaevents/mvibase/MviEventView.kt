package com.siljan.istriaevents.mvibase

/**
 * Object representing a UI that will
 * a) emit its intents to a view model,
 * b) subscribes to a view model for rendering its UI.
 *
 * @param I Top class of the [MviIntent] that the [MviView] will be emitting.
 * @param S Top class of the [MviViewState] the [MviView] will be subscribing to.
 */
interface MviEventView<I : MviIntent, in S : MviViewState, E: MviEvent> {
    /**
     * Entry point for the [MviView] to render itself based on a [MviViewState].
     */
    fun render(state: S)

    /**
     * Entry point for the [MviView] to show event based on [MviEvent]
     */
    fun events(event: E)
}
