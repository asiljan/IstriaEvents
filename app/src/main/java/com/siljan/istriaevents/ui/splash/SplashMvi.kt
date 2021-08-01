package com.siljan.istriaevents.ui.splash

import com.siljan.istriaevents.mvibase.MviAction
import com.siljan.istriaevents.mvibase.MviIntent
import com.siljan.istriaevents.mvibase.MviResult
import com.siljan.istriaevents.mvibase.MviViewState

data class SplashViewState(val session: Boolean): MviViewState

sealed class SplashViewIntent: MviIntent {
    object CheckAuthState: SplashViewIntent()
}

sealed class SplashAction: MviAction {
    object None: SplashAction()
    object SessionExists: SplashAction()
    object SessionInvalid: SplashAction()
}

sealed class SplashViewResult: MviResult {
    object User: SplashViewResult()
    object Guest: SplashViewResult()
}