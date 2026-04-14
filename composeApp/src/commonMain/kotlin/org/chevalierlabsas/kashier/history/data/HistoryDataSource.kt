package org.chevalierlabsas.kashier.history.data

import org.chevalierlabsas.kashier.history.domain.histItem

interface HistoryDataSource {
    fun getHistories(): List<histItem>
}