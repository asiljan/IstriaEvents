package com.siljan.istriaevents.ui.onboarding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siljan.istriaevents.R
import com.siljan.istriaevents.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel(), BaseViewModel<LoginIntent, LoginUIState> {

    private val _uiState: MutableLiveData<LoginUIState> = MutableLiveData()
    private val uiState: LiveData<LoginUIState> get() = _uiState
    override fun processIntent(intent: LoginIntent) {
        viewModelScope.launch {
            when(intent) {
                is LoginIntent.UserLogIn -> {
                    if (intent.username.isEmpty() or intent.password.isEmpty())
                        _uiState.postValue(LoginUIState.UserLogInError(R.string.onboarding_label_error_fields_empty))
                    else {
                        delay(2000)
                        _uiState.postValue(LoginUIState.UserLoggedIn)
                    }
                }
            }
        }
    }

    override fun states(): LiveData<LoginUIState> {
        return uiState
    }
}