package com.example.tedf.data
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EnergyDrink(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val price: Double,
    val rating: Int,
    val category: String,
)