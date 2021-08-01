package com.siljan.istriaevents.ui.splash

import io.reactivex.Observable

class SplashReducer {
    companion object {
        fun Observable<SplashViewResult>.reduceState(initViewState: SplashViewState): Observable<SplashViewState> =
            this.scan(initViewState) { previousState, result ->
                when(result) {
                    SplashViewResult.User -> previousState.copy(session = true)
                    SplashViewResult.Guest -> previousState.copy(session = false)
                }
            }
    }
}