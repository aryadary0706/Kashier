package org.chevalierlabsas.kashier

import androidx.compose.ui.window.ComposeUIViewController
import org.chevalierlabsas.kashier.core.App
import org.chevalierlabsas.kashier.core.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }