package com.example.tedf

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tedf.ui.EnergyDrink.EnergyDrinkEntryViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory{
        initializer {
            EnergyDrinkEntryViewModel(TedfApplication().container.energyDrinkRepository)
        }
    }
}