package com.example.tedf_this_is_da_one.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tedf_this_is_da_one.data.EnergyDrinkItem
import com.example.tedf_this_is_da_one.data.loadImage
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.tasks.await

class HomeViewModel(val TedfCollection: CollectionReference) : ViewModel() {
    val uiState: StateFlow<HomeUiState> =
        flow<HomeUiState> {
            while (true) {
                delay(1000)
                val energyDrinks = TedfCollection.get().await().toObjects<EnergyDrinkItem>()
                    .map { item -> item.loadImage() }
                emit(HomeUiState(energyDrinkItems = energyDrinks))
            }
        }.filterNotNull().stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = HomeUiState()
        )

    init {

    }

    data class HomeUiState(
        val energyDrinkItems: List<EnergyDrinkItem> = emptyList(),
    )


}
