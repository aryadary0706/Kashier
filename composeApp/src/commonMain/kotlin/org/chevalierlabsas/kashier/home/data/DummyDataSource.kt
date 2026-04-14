package org.chevalierlabsas.kashier.home.data

import org.chevalierlabsas.kashier.history.domain.histItem
import org.chevalierlabsas.kashier.home.domain.Item


interface DummyDataSource {
    fun getDatas(): List<Item>

    fun gethistory(): List<histItem> = listOf(
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

