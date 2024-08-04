package com.example.tedf_this_is_da_one.view

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import com.example.tedf_this_is_da_one.data.EnergyDrinkItem
import com.example.tedf_this_is_da_one.data.toDatabaseEntity
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.storage.StorageReference

data class EnergyDrinkUiState(
    val energyDrinkItem: EnergyDrinkItem = EnergyDrinkItem(),
    val imageState: ImageBitmap = ImageBitmap(1, 1),
    val uploadState: Boolean = false,
)

class EnergyDrinkViewModel(
    private val TedfStorage: StorageReference,
    private val TedfCollection: CollectionReference,
) : ViewModel() {
    var itemState by mutableStateOf((EnergyDrinkItem()))
    var uiState by mutableStateOf(EnergyDrinkUiState(energyDrinkItem = itemState))
        private set

    fun updateItem(energyDrinkItem: EnergyDrinkItem) {
        itemState = energyDrinkItem.copy()
    }

    fun updateUiState(state: EnergyDrinkUiState) {
        uiState = state.copy(energyDrinkItem = itemState.copy())
    }

    fun upload() {
        TedfCollection.document().set(uiState.energyDrinkItem.toDatabaseEntity())
            .addOnSuccessListener {
                Log.d(TAG, "successfullly uploaded")
                uiState = uiState.copy(uploadState = true)
            }
            .addOnFailureListener { exception ->
                Log.e(TAG, "Error uploading.", exception)
                uiState = uiState.copy(uploadState = false)
            }

        val data = EnergyDrinkItem.toByteArray(uiState.imageState)
        val uploadTask = TedfStorage.child("energydrinks/${uiState.energyDrinkItem.image}")
            .putBytes(data)
        uploadTask.addOnFailureListener {
            // Handle unsuccessful uploads
        }.addOnSuccessListener { taskSnapshot ->
            // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
            // ...
            Log.d(TAG, "Succesfully uploaded image")
        }
    }
}