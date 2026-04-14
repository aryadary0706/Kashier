package org.chevalierlabsas.kashier.history.domain.repository

import org.chevalierlabsas.kashier.history.domain.histItem
import org.chevalierlabsas.kashier.history.data.HistoryDataSource


class HistoryRepositoryImpl(private val dataSource: HistoryDataSource) : HistoryRepository {
    override suspend fun getHistory(): List<histItem> {
        return dataSource.getHistories()
    }
}