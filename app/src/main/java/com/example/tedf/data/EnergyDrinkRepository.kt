package com.example.tedf.data

import kotlinx.coroutines.flow.Flow

interface EnergyDrinkRepository {
    fun getAllEnergyDrinksStream(): Flow<List<EnergyDrink>>

    fun getEnergyDrinkStream(id: Int): Flow<EnergyDrink?>

    suspend fun insertEnergyDrink(energyDrink: EnergyDrink)

    suspend fun deleteEnergyDrink(energyDrink: EnergyDrink)

    suspend fun updateEnergyDrink(energyDrink: EnergyDrink)
}