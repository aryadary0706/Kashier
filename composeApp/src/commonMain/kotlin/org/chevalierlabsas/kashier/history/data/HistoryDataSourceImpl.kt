package org.chevalierlabsas.kashier.history.data

import org.chevalierlabsas.kashier.history.domain.histItem

class HistoryDataSourceImpl: HistoryDataSource {
    override fun getHistories(): List<histItem> = listOf(
        histItem(
            totalHarga = 25000000.0,
            totalBarang = 10,
            tanggal = "2026-03-29"
        ),
        histItem(
            totalHarga = 30000.0,
            totalBarang = 10,
            tanggal = "2026-03-29"
        ),
        histItem(
            totalHarga = 150000000.0,
            totalBarang = 10,
            tanggal = "2026-03-25"
        ),
        histItem(
            totalHarga = 1500000000.0,
            totalBarang = 10,
            tanggal = "2026-03-01"
        ),
    )
}