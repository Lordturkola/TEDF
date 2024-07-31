package com.example.tedf_this_is_da_one.viewmodel

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.tedf_this_is_da_one.data.EnergyDrinkItem
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel(val TedfCollection: CollectionReference) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        TedfCollection.addSnapshotListener { snapshot, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                updateEnergyDrinks(
                    snapshot.toObjects<EnergyDrinkItem>()
                )
                Log.w(TAG, "updating entries.", e)

            } else {
                Log.d(TAG, "Current data: null")
            }
        }
    }

    fun updateState(uiState: HomeUiState) {
        _uiState.update {
            it.copy(
                energyDrinkItems = it.energyDrinkItems
            )
        }
    }
    fun updateEnergyDrinks(energyDrinks: List<EnergyDrinkItem>) {
        _uiState.update {
            it.copy(energyDrinkItems = energyDrinks)
        }
    }
    data class HomeUiState(
        val energyDrinkItems: List<EnergyDrinkItem> = emptyList()
    )

}
