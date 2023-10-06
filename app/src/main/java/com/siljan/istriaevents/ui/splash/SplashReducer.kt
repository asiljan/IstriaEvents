package com.siljan.istriaevents.ui.splash

import com.siljan.domain.models.User
import com.siljan.istriaevents.common.BaseIntent
import com.siljan.istriaevents.common.BaseResult
import com.siljan.istriaevents.common.BaseViewState


sealed class SplashUIState : BaseViewState {
    data class UserExists(val username: String) : SplashUIState()
    object GuestUser : SplashUIState()
}

sealed class SplashIntent : BaseIntent {
    object UserAuthenticationCheck : SplashIntent()
}

sealed class SplashResult : BaseResult {
    data class UserAuthenticated(val user: User) : SplashResult()
    object UserAuthenticationFailed : SplashResult()
}
