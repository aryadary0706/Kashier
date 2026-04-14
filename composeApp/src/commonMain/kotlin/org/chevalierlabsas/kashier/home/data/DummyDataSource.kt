package org.chevalierlabsas.kashier.home.data

import org.chevalierlabsas.kashier.history.domain.histItem
import org.chevalierlabsas.kashier.home.domain.Item


interface DummyDataSource {
    fun getDatas(): List<Item>

}

