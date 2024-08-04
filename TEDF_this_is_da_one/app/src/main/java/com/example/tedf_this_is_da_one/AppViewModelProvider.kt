package com.example.tedf_this_is_da_one

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tedf_this_is_da_one.view.EnergyDrinkViewModel
import com.example.tedf_this_is_da_one.viewmodel.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(TedfApplication().container.TedfCollection)
        }
        initializer {
            EnergyDrinkViewModel(TedfApplication().container.TedfStorage, TedfApplication().container.TedfCollection)
        }
    }
}
