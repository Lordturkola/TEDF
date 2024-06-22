package com.example.tedf.data
import android.content.Context

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val energyDrinkRepository: EnergyDrinkRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineEnergyDrinkRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [EnergyDrinkRepository]
     */
    override val energyDrinkRepository: EnergyDrinkRepository by lazy {
        OfflineEnergyDrinkRepository(TedfDatabase.getDatabase(context).EnergyDrinkDao())
    }
}
