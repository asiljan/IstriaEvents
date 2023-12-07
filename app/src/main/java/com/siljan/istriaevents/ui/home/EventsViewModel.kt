package com.siljan.istriaevents.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siljan.domain.models.Result
import com.siljan.domain.usecases.GetAllEventsUseCase
import com.siljan.istriaevents.common.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EventsViewModel @Inject constructor(private val getEventsUseCase: GetAllEventsUseCase) :
    ViewModel(), BaseViewModel<EventsIntent, EventsUIState> {

    private val _uiState: MutableLiveData<EventsUIState> = MutableLiveData()

    private val uiState: LiveData<EventsUIState> get() = _uiState
    override fun processIntent(intent: EventsIntent) {
        viewModelScope.launch {
            when (intent) {
                EventsIntent.FetchAllEvents -> getEventsUseCase.execute()
                    .onStart { _uiState.postValue(EventsUIState.EventsFetching) }
                    .collect {
                        _uiState.postValue(
                            if (it is Result.Success) EventsUIState.EventsFetched(it.data)
                            else EventsUIState.EventsFetchingError
                        )
                    }

                is EventsIntent.FetchEvents -> {
                    //TODO filtering
                }
            }
        }
    }

    override fun states(): LiveData<EventsUIState> = uiState
}