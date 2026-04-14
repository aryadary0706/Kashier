package org.chevalierlabsas.kashier.core

import SearchBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.chevalierlabsas.kashier.core.navigation.HistoryDestination
import org.chevalierlabsas.kashier.core.navigation.HomeDestination
import org.chevalierlabsas.kashier.history.presentation.HistoryScreen
import org.chevalierlabsas.kashier.history.presentation.HistoryViewModel
import org.chevalierlabsas.kashier.home.presentation.HomeScreen
import org.chevalierlabsas.kashier.home.presentation.HomeViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel


@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = HomeDestination
        ){
            composable<HomeDestination> {
                val viewModel = koinViewModel<HomeViewModel>()
                val state by viewModel.state.collectAsStateWithLifecycle()
                HomeScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigate = { navController.navigate(it) }
                )
            }
            composable<HistoryDestination> {
                val viewModel = koinViewModel<HistoryViewModel>()
                val state by viewModel.state.collectAsState()
                HistoryScreen(
                    state = state,
                    onEvent = viewModel::onEvent,
                    onNavigateBack = { navController.navigateUp() }
                )
            }
        }
    }
}