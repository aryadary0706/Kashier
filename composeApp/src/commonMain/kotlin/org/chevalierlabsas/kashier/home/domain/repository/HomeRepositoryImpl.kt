package org.chevalierlabsas.kashier.home.domain.repository

import org.chevalierlabsas.kashier.home.data.DummyDataSource
import org.chevalierlabsas.kashier.home.domain.Item

class HomeRepositoryImpl(private val dataSource: DummyDataSource): HomeRepository {

    override suspend fun getItems(): List<Item> {
        return dataSource.getDatas()
    }

    override suspend fun postItem(item: Item): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteItem(id: Int): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun putItem(item: Item): Result<Boolean> {
        TODO("Not yet implemented")
    }

    override suspend fun postTransaction(): Result<Boolean> {
        TODO("Not yet implemented")
    }
}