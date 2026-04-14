package org.chevalierlabsas.kashier.home.presentation

import ItemCard
import SaveButton
import SearchBar
import SelectedItemChip
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kashier.composeapp.generated.resources.Res
import kashier.composeapp.generated.resources.add_item_fab_label
import kashier.composeapp.generated.resources.all_item_label
import kashier.composeapp.generated.resources.app_name
import kashier.composeapp.generated.resources.choosen_label
import kashier.composeapp.generated.resources.history_topbar
import kashier.composeapp.generated.resources.title_modal_add
import kashier.composeapp.generated.resources.title_modal_edit
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.chevalierlabsas.kashier.core.navigation.HistoryDestination
import org.chevalierlabsas.kashier.home.domain.Item
import org.chevalierlabsas.kashier.home.presentation.components.HomeSeparator
import org.chevalierlabsas.kashier.home.presentation.components.ItemBottomSheetContent
import org.chevalierlabsas.kashier.home.presentation.components.TotalPriceHeader
import org.chevalierlabsas.kashier.home.domain.repository.HomeRepository
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit,
    onNavigate: (Any) -> Unit
) {
    LaunchedEffect(state.items) {
        if (state.items.isEmpty()) {
            onEvent(HomeEvent.OnLoadData)
        }
    }
    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedItemForSheet by remember { mutableStateOf<Item?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(Res.string.app_name))
                },
                actions = {
                    IconButton(
                        onClick = { onNavigate(HistoryDestination) }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.History,
                            contentDescription = stringResource(Res.string.history_topbar)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    selectedItemForSheet = null
                    showBottomSheet = true
                },
                containerColor = MaterialTheme.colorScheme.tertiary,
                text = { Text(text = stringResource(Res.string.add_item_fab_label)) },
                icon = { Icon(Icons.Filled.Add, contentDescription = stringResource(Res.string.add_item_fab_label)) }
            )
        }
    ) { contentPadding ->
        LazyColumn(
            contentPadding = contentPadding,
        ) {
            item {
                TotalPriceHeader(
                    modifier = Modifier.padding(16.dp),
                    totalPrice = state.totalPrice,
                )
            }
            item {
                SaveButton(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp),
                    onSave = { TODO("Save data.") },
                    enabled = state.selectedItems.isNotEmpty() && state.totalPrice > 0.00
                )
            }
            item {
                HomeSeparator(
                    modifier = Modifier.padding(start = 16.dp, end = 4.dp),
                    title = stringResource(Res.string.choosen_label),
                    visible = state.showSelectedItem,
                    onAction = { visible ->
                        onEvent(HomeEvent.OnSelectedItemVisibilityChange(visible))
                    }
                )
            }
            item {
                AnimatedVisibility(
                    visible = state.showSelectedItem,
                    enter = expandVertically(),
                    exit = shrinkVertically()
                ) {
                    FlowRow(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        content = {
                            state.selectedItems.map { item ->
                                SelectedItemChip(
                                    onRemove = {
                                        onEvent(HomeEvent.OnRemoveItem(item))
                                    },
                                    Item = item,
                                    modifier = Modifier.padding(horizontal = 4.dp)
                                )
                            }
                        }
                    )
                }
            }
            item {
                HomeSeparator(
                    modifier = Modifier.padding(start = 16.dp, end = 4.dp),
                    title = stringResource(Res.string.all_item_label),
                    visible = state.showAllItem,
                    onAction = { visible ->
                        onEvent(HomeEvent.OnAllItemVisibilityChange(visible))
                    }
                )
            }
            item {
                SearchBar(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 13.dp, vertical = 6.dp),
                    query = state.searchQuery,
                    onQueryChange = { onEvent(HomeEvent.OnSearchQueryChange(it)) },
                )
            }
            items(state.items) { item ->
                AnimatedVisibility(
                    visible = state.showAllItem || state.searchQuery.isNotBlank(),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    ItemCard(
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                        item = item,
                        onEditClick = {
                            selectedItemForSheet = item
                            showBottomSheet = true
                        },
                        onAddClick = {
                            onEvent(HomeEvent.OnAddItem(item))
                        }
                    )
                }
            }
        }
    }
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
                selectedItemForSheet = null
            },
            sheetState = sheetState,
            dragHandle = { BottomSheetDefaults.DragHandle() }
        ) {
            ItemBottomSheetContent(
                title = if (selectedItemForSheet == null)
                    stringResource(Res.string.title_modal_add)
                else
                    stringResource(Res.string.title_modal_edit),
                initialName = selectedItemForSheet?.name ?: "",
                initialPrice = selectedItemForSheet?.price?.toString() ?: "",
                onSave = { name, price ->
                    val doublePrice = price.toDoubleOrNull() ?: 0.0
                    if (selectedItemForSheet == null) {
                        val newItem = Item(
                            id = 0,
                            name = name,
                            price = doublePrice,
                            userId = 1,
                        )
                        onEvent(HomeEvent.OnAddItem(newItem))
                    } else {
                        val updatedItem = selectedItemForSheet!!.copy(
                            name = name,
                            price = doublePrice,
                        )
                        onEvent(HomeEvent.OnEditItem(updatedItem))
                    }
                    showBottomSheet = false
                    selectedItemForSheet = null
                }
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        state = HomeState(),
        onEvent = {},
        onNavigate = {},
    )
}