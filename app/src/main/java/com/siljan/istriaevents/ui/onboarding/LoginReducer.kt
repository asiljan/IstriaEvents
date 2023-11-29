package com.siljan.istriaevents.ui.onboarding

import com.siljan.istriaevents.R
import com.siljan.istriaevents.common.BaseIntent
import com.siljan.istriaevents.common.BaseResult
import com.siljan.istriaevents.common.BaseViewState

sealed class LoginUIState : BaseViewState {
    object UserLoggedIn : LoginUIState()
    data class UserLogInError(val error: LoginError) : LoginUIState()
}

sealed class LoginError(val reasonId: Int = R.string.api_remote_calls_unknown_error, val reasonText: String? = null) {
    object ErrorEmailInvalid : LoginError(R.string.onboarding_label_error_email_not_match)
    object ErrorEmailEmpty : LoginError(R.string.onboarding_label_error_email_empty)
    object ErrorPasswordEmpty : LoginError(R.string.onboarding_label_error_password_empty)
    object ErrorPasswordInvalid : LoginError(R.string.onboarding_label_error_password_too_short)
    data class ErrorApi(val reason: String?): LoginError(reasonText = reason)
}

sealed class LoginIntent : BaseIntent {
    data class UserLogIn(val email: String, val password: String): LoginIntent()
}

sealed class LoginResult : BaseResult {
    object UserLoggedIn : LoginResult()
    object UserLogInError : LoginResult()
}
