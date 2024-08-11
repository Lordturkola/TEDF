package com.example.tedf_this_is_da_one.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.example.tedf_this_is_da_one.TedfApplication
import com.google.firebase.storage.StorageException
import kotlinx.coroutines.tasks.await
import java.io.ByteArrayOutputStream

data class EnergyDrinkItem(
    val name: String = "",
    val rating: String = "-1",
    val price: String = "0.0",
    val caffeine: String = "0.0",
    val user: String = "Anonymous",
    val image: String = "",
    val bitmap: ImageBitmap? = null,
) {
    companion object {
        fun toByteArray(bitmap: ImageBitmap): ByteArray {
            val stream = ByteArrayOutputStream()
            bitmap.asAndroidBitmap().compress(Bitmap.CompressFormat.PNG, 80, stream)
            return stream.toByteArray()
        }
    }
}


suspend fun EnergyDrinkItem.loadImage(updateProgress: () -> Unit): EnergyDrinkItem {
    var updatedEnergyDrink: EnergyDrinkItem = this.copy(image = "", bitmap = null)
    try {
        val downloadImage =
            TedfApplication().container.TedfStorage.child("energydrinks/" + this.image)
                .getBytes(
                    Long.MAX_VALUE
                ).addOnFailureListener {
                    Log.d("ENERGYDRINK DOWNLOAD", "FAILED download : ${it}")

                }.addOnSuccessListener { taskSnapshot ->
                    Log.d("ENERGYDRINK DOWNLOAD", "success download")
                    val options = BitmapFactory.Options()
                    updatedEnergyDrink = this.copy(
                        bitmap =
                        BitmapFactory.decodeByteArray(
                            taskSnapshot,
                            0,
                            taskSnapshot.size,
                            options
                        ).asImageBitmap()
                    )
                }

        downloadImage.await()
    } catch (e: StorageException) {
        //set default image here
        Log.d("ENERGYDRINK DOWNLOAD EXCEPTION", "could not find image ${e}")
    }
    updateProgress()
    return updatedEnergyDrink
}

fun EnergyDrinkItem.toDatabaseEntity(): HashMap<String, Any> =
    hashMapOf(
        Pair("name", this.name),
        Pair("rating", this.rating),
        Pair("price", this.price),
        Pair("caffeine", this.caffeine),
        Pair("user", this.user),
        Pair("image", this.image)
    )

