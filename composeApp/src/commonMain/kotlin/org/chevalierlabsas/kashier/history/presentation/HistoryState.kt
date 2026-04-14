package org.chevalierlabsas.kashier.history.presentation

import org.chevalierlabsas.kashier.history.domain.histItem

data class HistoryState(
    val historyItems: List<histItem> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

sealed interface HistoryEvent {
    data object OnLoadHistory : HistoryEvent
}
