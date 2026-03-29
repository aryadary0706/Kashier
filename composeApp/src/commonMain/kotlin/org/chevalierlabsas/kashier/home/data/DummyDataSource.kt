package org.chevalierlabsas.kashier.home.data

import org.chevalierlabsas.kashier.history.domain.histItem
import org.chevalierlabsas.kashier.home.domain.Item


class DummyDataSource {
    fun getData(): List<Item> = listOf(
        Item(
            id = 1,
            userId = 1,
            name = "Item 1",
            price = 100000.0
        ),
        Item(
            id = 2,
            userId = 1,
            name = "Item 2",
            price = 200000.0
        ),
        Item(
            id = 3,
            userId = 1,
            name = "Item 3",
            price = 300000.0
        ),
        Item(
            id = 4,
            userId = 2,
            name = "Item 4",
            price = 400000.0
        ),
        Item(
            id = 5,
            userId = 1,
            name = "Item 5",
            price = 500000.0
        ),
        Item(
            id = 6,
            userId = 2,
            name = "Item 6",
            price = 600000.0
        ),
        Item(
            id = 7,
            userId = 1,
            name = "Item 7",
            price = 700000.0
        ),
        Item(
            id = 8,
            userId = 1,
            name = "Item 8",
            price = 800000.0
        )
    )

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