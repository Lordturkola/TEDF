package com.example.tedf.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface EnergyDrinkDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(energyDrink: EnergyDrink)

    @Update
    suspend fun update(energyDrink: EnergyDrink)

    @Delete
    suspend fun delete(energyDrink: EnergyDrink)

    @Query("SELECT * from EnergyDrink WHERE id = :id")
    fun getEnergyDrink(id: Int): Flow<EnergyDrink>

    @Query("SELECT * from EnergyDrink ORDER BY name ASC")
    fun getAllEnergyDrinks(): Flow<List<EnergyDrink>>
}