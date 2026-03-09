package org.chevalierlabsas.kashier.home.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import org.chevalierlabsas.kashier.home.data.DummyDataSource
import org.chevalierlabsas.kashier.home.domain.Item

class HomeViewModel: ViewModel() {

    private val _items = DummyDataSource().getData()
    private val _state = MutableStateFlow(HomeState(items = _items))
    val state = _state.asStateFlow()

    fun onEvent(event: HomeEvent){
        when(event){
            is HomeEvent.OnAddItem -> addItem(event.item)
            is HomeEvent.OnAllItemVisibilityChange -> setAllItemVisibility(event.visible)
            is HomeEvent.OnRemoveItem -> removeItem(event.item)
            is HomeEvent.OnSearchQueryChange -> updateQuery(event.query)
            is HomeEvent.OnSelectedItemVisibilityChange -> setSelectedItemVisibility(event.visible)

            //Tambahan assignment
            is HomeEvent.OnAddItemToList -> addItemList(event.item)
            is HomeEvent.OnEditItem -> editItem(event.updatedItem)

            HomeEvent.OnSaveTransaction -> saveTransaction()
            HomeEvent.OnSearchQuerySubmit -> search()

        }
    }

    private fun updateQuery(query: String) {
        _state.update { it.copy(searchQuery = query) }
        if (state.value.searchQuery.isBlank()) {
            _state.update { it.copy(items = _items) }
        }
    }

    private fun addItem(item: Item) {
        _state.update {
            it.copy(
                selectedItems = it.selectedItems + item,
                totalPrice = it.totalPrice + item.price
            )
        }
    }

    private fun removeItem(item: Item) {
        _state.update {
            it.copy(
                selectedItems = it.selectedItems - item,
                totalPrice = it.totalPrice - item.price
            )
        }
    }

    private fun setAllItemVisibility(visible: Boolean) {
        _state.update { it.copy(showAllItem = visible) }
    }

    private fun setSelectedItemVisibility(visible: Boolean) {
        _state.update { it.copy(showSelectedItem = visible) }
    }

    private fun saveTransaction() {
        TODO("Save Data to API.")
    }

    private fun search() {
        if (state.value.searchQuery.isNotEmpty()) {
            _state.update {
                it.copy(
                    items = it.items.filter { item ->
                        item.name.contains(_state.value.searchQuery, ignoreCase = true)
                    }
                )
            }
        } else {
            _state.update { it.copy(items = _items) }
        }
    }

    private fun editItem(updatedItem: Item) {
        _state.update { currentState ->
            val newItems = currentState.items.map {
                if (it.id == updatedItem.id) updatedItem else it
            }
            currentState.copy(items = newItems)
        }
    }

    private fun addItemList(item: Item) {
        _state.update { currentState ->
            currentState.copy(
                items = currentState.items + item
            )
        }
    }
}