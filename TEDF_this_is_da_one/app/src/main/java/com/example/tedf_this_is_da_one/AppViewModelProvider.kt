package com.example.tedf_this_is_da_one

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.tedf_this_is_da_one.viewmodel.HomeViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(TedfApplication().container.TedfCollection)
        }
    }
}
// method extension to access appliction container via its own key? not sure how this works tbh
fun CreationExtras.tedfApplication(): TedfApplication =
    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as TedfApplication