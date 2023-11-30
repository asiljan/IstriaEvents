package com.siljan.istriaevents.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siljan.domain.models.LoginRequest
import com.siljan.domain.models.Result
import com.siljan.domain.usecases.AuthenticateUserUseCase
import com.siljan.istriaevents.common.BaseViewModel
import com.siljan.istriaevents.common.validateEmailInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authenticateUserUseCase: AuthenticateUserUseCase) :
    ViewModel(), BaseViewModel<LoginIntent, LoginUIState> {

    private val _uiState: MutableLiveData<LoginUIState> = MutableLiveData()
    private val uiState: LiveData<LoginUIState> get() = _uiState

    override fun processIntent(intent: LoginIntent) {
        viewModelScope.launch {
            when (intent) {
                is LoginIntent.UserLogIn -> {
                    val validationError = validateUserInput(intent.email, intent.password)

                    validationError?.let {
                        _uiState.postValue(it)
                        return@launch
                    }

                    authenticateUserUseCase.execute(
                        LoginRequest(
                            intent.email,
                            intent.password
                        )
                    )
                        .collect {
                            when (it) {
                                is Result.Success -> _uiState.postValue(LoginUIState.UserLoggedIn)
                                is Result.Error -> _uiState.postValue(
                                    LoginUIState.UserLogInError(LoginError.ErrorApi(null)) //TODO implement better error handling on a data layer
                                )
                            }
                        }
                }
            }
        }
    }

    override fun states(): LiveData<LoginUIState> {
        return uiState
    }

    private fun validateUserInput(email: String, password: String): LoginUIState.UserLogInError? {
        if (email.isEmpty())
            return LoginUIState.UserLogInError(LoginError.ErrorEmailEmpty)

        if (password.isEmpty())
            return LoginUIState.UserLogInError(LoginError.ErrorPasswordEmpty)

        if (!validateEmailInput(email))
            return LoginUIState.UserLogInError(LoginError.ErrorEmailInvalid)

        if (password.length < 6)
            return LoginUIState.UserLogInError(LoginError.ErrorPasswordInvalid)

        return null
    }
}