package org.chevalierlabsas.kashier.core.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(appDeclaration: KoinAppDeclaration = {}) {
    startKoin {
        appDeclaration()
        // modules(commonModule) // nanti tambahkan common modules di sini
    }
}