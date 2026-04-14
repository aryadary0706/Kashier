package org.chevalierlabsas.kashier.history.domain.repository

import org.chevalierlabsas.kashier.history.domain.histItem

interface HistoryRepository {
    suspend fun getHistory(): List<histItem>
}