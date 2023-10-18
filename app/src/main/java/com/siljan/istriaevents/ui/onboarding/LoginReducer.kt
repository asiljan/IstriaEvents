package com.siljan.istriaevents.ui.onboarding

import com.siljan.istriaevents.common.BaseIntent
import com.siljan.istriaevents.common.BaseResult
import com.siljan.istriaevents.common.BaseViewState

sealed class LoginUIState : BaseViewState {
    object UserLoggedIn : LoginUIState()
    data class UserLogInError(val reasonId: Int) : LoginUIState()
}

sealed class LoginIntent : BaseIntent {
    data class UserLogIn(val username: String, val password: String): LoginIntent()
}

sealed class LoginResult : BaseResult {
    object UserLoggedIn : LoginResult()
    object UserLogInError : LoginResult()
}
