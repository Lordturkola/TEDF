package com.example.tedf.data

import kotlinx.coroutines.flow.Flow

class OfflineEnergyDrinkRepository(private val energyDrinkDao: EnergyDrinkDao) :
    EnergyDrinkRepository {
    override fun getAllEnergyDrinksStream(): Flow<List<EnergyDrink>> =
        energyDrinkDao.getAllEnergyDrinks()

    override fun getEnergyDrinkStream(id: Int): Flow<EnergyDrink?> =
        energyDrinkDao.getEnergyDrink(id)

    override suspend fun insertEnergyDrink(energyDrink: EnergyDrink) =
        energyDrinkDao.insert(energyDrink)

    override suspend fun deleteEnergyDrink(energyDrink: EnergyDrink) =
        energyDrinkDao.delete(energyDrink)

    override suspend fun updateEnergyDrink(energyDrink: EnergyDrink) =
        energyDrinkDao.update(energyDrink)
}