package com.example.tedf.ui.EnergyDrink

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tedf.data.EnergyDrink
import com.example.tedf.data.EnergyDrinkRepository
import java.text.NumberFormat

class EnergyDrinkEntryViewModel(private val energyDrinkRepository: EnergyDrinkRepository) :
    ViewModel() {
    var energyDrinkUiState by mutableStateOf(EnergyDrinkUiState())
        private set

    private fun validateInput(uiState: EnergyDrinkDetails = energyDrinkUiState.energyDrinkDetails): Boolean {
        return with(uiState) {
            name.isNotBlank() && price.isNotBlank() && rating.isNotBlank()
        }
    }

    suspend fun saveItem() {
        if (validateInput()) {
            energyDrinkRepository.insertEnergyDrink(energyDrink = energyDrinkUiState.energyDrinkDetails.toEnergyDrink())
        }
    }
    fun updateUiState(energyDrinkDetails: EnergyDrinkDetails) {
        energyDrinkUiState = EnergyDrinkUiState(
            energyDrinkDetails = energyDrinkDetails,
            isEntryValid = validateInput(energyDrinkDetails)
        )
    }
}


// represents ui state for an energy drink item
data class EnergyDrinkUiState(
    val energyDrinkDetails: EnergyDrinkDetails = EnergyDrinkDetails(),
    val isEntryValid: Boolean = false,
)

// this describes it's contents or detail regarding the entity
data class EnergyDrinkDetails(
    val id: Int = 0,
    val name: String = "",
    val price: String = "0.0",
    val rating: String = "-1",
    val category: String = "",
)

fun EnergyDrinkDetails.toEnergyDrink(): EnergyDrink = EnergyDrink(
    id = id,
    name = name,
    price = price.toDoubleOrNull() ?: 0.0,
    rating = rating.toIntOrNull() ?: -1,
    category = category,
)

fun EnergyDrink.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(price)
}

fun EnergyDrink.toEnergyDrinkUiState(isEntryValid: Boolean = false): EnergyDrinkUiState =
    EnergyDrinkUiState(
        energyDrinkDetails = this.toEnergyDrinkDetails(),
        isEntryValid = isEntryValid,
    )

fun EnergyDrink.toEnergyDrinkDetails(): EnergyDrinkDetails = EnergyDrinkDetails(
    id = id,
    name = name,
    price = price.toString(),
    rating = rating.toString(),
    category = category,
)


