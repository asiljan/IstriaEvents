package com.siljan.istriaevents.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siljan.domain.models.LoginRequest
import com.siljan.domain.models.Result
import com.siljan.domain.usecases.AuthenticateUserUseCase
import com.siljan.istriaevents.R
import com.siljan.istriaevents.common.BaseViewModel
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
                    if (!validateEmailInput(intent.username) or intent.password.isEmpty()) {
                        _uiState.postValue(LoginUIState.UserLogInError(R.string.onboarding_label_error_fields_empty))
                    } else {
                        authenticateUserUseCase.execute(
                            LoginRequest(
                                intent.username,
                                intent.password
                            )
                        )
                            .collect {
                                when (it) {
                                    is Result.Success -> _uiState.postValue(LoginUIState.UserLoggedIn)
                                    is Result.Error -> _uiState.postValue(
                                        LoginUIState.UserLogInError(
                                            R.string.api_remote_calls_unknown_error
                                        )
                                    )
                                }
                            }
                    }
                }
            }
        }
    }

    override fun states(): LiveData<LoginUIState> {
        return uiState
    }

    private fun validateEmailInput(input: String): Boolean = input.isNotEmpty()
}