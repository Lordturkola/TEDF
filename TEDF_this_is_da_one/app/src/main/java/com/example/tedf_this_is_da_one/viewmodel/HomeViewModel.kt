package com.example.tedf_this_is_da_one.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(): ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()
    fun updateState(greeting: String, energyDrinkItem: energyDrinkItem? = energyDrinkItem()) {
        _uiState.update {
            it.copy(
                greeting = greeting,
                energyDrinkItem = energyDrinkItem as energyDrinkItem
            )
        }
    }
}


data class HomeUiState(
    val greeting: String = "fuck you",
    val energyDrinkItem: energyDrinkItem = energyDrinkItem()
)

data class energyDrinkItem(
    val name: String ="No name",
    val rating: String ="",
    val price: String="",
    val caffeine: String="",
    val user: String="",
)