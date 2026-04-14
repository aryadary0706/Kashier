package org.chevalierlabsas.kashier.core.di

import org.chevalierlabsas.kashier.home.data.DummyDataSource
import org.chevalierlabsas.kashier.home.data.DummyDataSourceImpl
import org.chevalierlabsas.kashier.home.domain.repository.HomeRepository
import org.chevalierlabsas.kashier.home.domain.repository.HomeRepositoryImpl
import org.chevalierlabsas.kashier.home.presentation.HomeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModules: Module

val sharedModules = module {
    singleOf(::DummyDataSourceImpl).bind<DummyDataSource>()
    singleOf(::HomeRepositoryImpl).bind<HomeRepository>()

    factory { HomeViewModel(get()) }
}