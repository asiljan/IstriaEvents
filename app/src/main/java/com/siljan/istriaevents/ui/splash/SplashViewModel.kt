package com.siljan.istriaevents.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siljan.domain.models.Result
import com.siljan.domain.usecases.CheckUserUseCase
import com.siljan.istriaevents.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val checkUserUseCase: CheckUserUseCase
) : ViewModel(), BaseViewModel<SplashIntent, SplashUIState> {

    private val _uiState: MutableLiveData<SplashUIState> = MutableLiveData()
    private val uiState: LiveData<SplashUIState>
        get() = _uiState

    override fun processIntent(intent: SplashIntent) {
        viewModelScope.launch {
            when (intent) {
                SplashIntent.UserAuthenticationCheck -> checkUserUseCase.execute()
                    .collect {
                        if (it is Result.Success)
                            _uiState.postValue(SplashUIState.UserExists(it.data.displayName))
                        else
                            _uiState.postValue(SplashUIState.GuestUser)
                    }
            }
        }
    }

    override fun states(): LiveData<SplashUIState> {
        return uiState
    }

}