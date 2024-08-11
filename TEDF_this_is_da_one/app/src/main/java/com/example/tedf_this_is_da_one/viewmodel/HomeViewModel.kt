package com.example.tedf_this_is_da_one.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tedf_this_is_da_one.data.EnergyDrinkItem
import com.example.tedf_this_is_da_one.data.loadImage
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class HomeViewModel(val TedfCollection: CollectionReference) : ViewModel() {
    private val _energyDrinks: StateFlow<List<EnergyDrinkItem>> =
        flow<List<EnergyDrinkItem>> {
            while (true) {
                delay(1000)
                val energyDrinks = TedfCollection.get().await().toObjects<EnergyDrinkItem>()
                    .map { item -> item.loadImage(::updateProgress) }
                emit(energyDrinks.sortedByDescending { it.rating })
            }
        }.filterNotNull().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = emptyList()
        )
    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        viewModelScope.launch {
            _energyDrinks.collect {
                _uiState.update { item -> item.copy(energyDrinkItems = it) }
            }
        }
    }

    data class HomeUiState(
        val energyDrinkItems: List<EnergyDrinkItem> = emptyList(),
        val progress: Int = 0,
        val isLoading: Boolean = true,
    )

    // GUI loading
    fun updateProgress() {
        if (uiState.value.progress >= uiState.value.energyDrinkItems.size) {
            _uiState.update {
                it.copy(isLoading = false)
            }
        } else {
            _uiState.update {
                it.copy(progress = uiState.value.progress.inc())
            }
        }
    }
}
