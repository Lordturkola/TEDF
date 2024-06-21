package com.example.tedf.viewmodel

import androidx.lifecycle.ViewModel
import com.example.tedf.model.UiStateSplashPageModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RankingViewModel: ViewModel() {
    private lateinit var currentTop:String
    private val _uiState = MutableStateFlow(UiStateSplashPageModel())
    val uiState: StateFlow<UiStateSplashPageModel> = _uiState.asStateFlow()


}