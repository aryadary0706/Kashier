package org.chevalierlabsas.kashier.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.chevalierlabsas.kashier.history.domain.repository.HistoryRepository

class HistoryViewModel(
    private val repository: HistoryRepository
) : ViewModel() {

    private val _state = MutableStateFlow(HistoryState())
    val state: StateFlow<HistoryState> = _state.asStateFlow()

    fun onEvent(event: HistoryEvent) {
        when (event) {
            HistoryEvent.OnLoadHistory -> loadHistory()
        }
    }

    private fun loadHistory() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            delay(2000) /* Simulate Network Call */
            val data = repository.getHistory()
            _state.update { it.copy(historyItems = data, isLoading = false) }
        }
    }
}