package com.example.tedf.data

import com.example.tedf.R
import com.example.tedf.model.EnergyDrink

class DataSource() {
    fun loadEnergyDrinks(): List<EnergyDrink>{
        return listOf<EnergyDrink>(
            EnergyDrink("numbah 1", R.drawable.monster_1, 1),
            EnergyDrink("numbah 2", R.drawable.monster_1, 2),
            EnergyDrink("numbH 3", R.drawable.monster_1, 3),
            EnergyDrink(R.string.monster_1.toString(), R.drawable.monster_1,4),
            EnergyDrink(R.string.monster_1.toString(), R.drawable.monster_1,5),
            EnergyDrink(R.string.monster_1.toString(), R.drawable.monster_1,6),
            EnergyDrink(R.string.monster_1.toString(), R.drawable.monster_1,7)
        )
    }
    fun loadTopThree():List<EnergyDrink>{
        return loadEnergyDrinks().filter { it.ranking in 1..3 }.sortedBy { it.ranking }
    }
}