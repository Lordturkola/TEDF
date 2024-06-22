package com.example.tedf.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EnergyDrink::class], version = 1, exportSchema = false)
abstract class TedfDatabase: RoomDatabase() {
    abstract fun EnergyDrinkDao(): EnergyDrinkDao
    companion object {
        @Volatile
        private var Instance : TedfDatabase? = null
        fun getDatabase(context: Context): TedfDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, TedfDatabase::class.java, "tedf_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it}
            }
        }
    }
}