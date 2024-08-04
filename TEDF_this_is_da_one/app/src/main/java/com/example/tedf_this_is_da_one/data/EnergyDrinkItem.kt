package com.example.tedf_this_is_da_one.data

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.example.tedf_this_is_da_one.TedfApplication
import java.io.ByteArrayOutputStream

data class EnergyDrinkItem(
    val name: String = "",
    val rating: String = "-1",
    val price: String = "0.0",
    val caffeine: String = "0.0",
    val user: String = "Anonymous",
    val image: String = "",
    var bitmap: ImageBitmap = ImageBitmap(1, 1),
) {
    companion object {
        fun toByteArray(bitmap: ImageBitmap): ByteArray {
            val stream = ByteArrayOutputStream()
            bitmap.asAndroidBitmap().compress(Bitmap.CompressFormat.PNG, 80, stream)
            return stream.toByteArray()
        }
    }
}


fun EnergyDrinkItem.loadImage(
    energyDrinkItem: EnergyDrinkItem,
    callback: (energyDrinkItem: EnergyDrinkItem, bitmap: ImageBitmap) -> Unit,
): EnergyDrinkItem {
    if(this.image.isBlank()){
        return this
    }
    val downloadImage =
        TedfApplication().container.TedfStorage.child("energydrinks/"+this.image)
            .getBytes(
                Long.MAX_VALUE
            )
    downloadImage.addOnFailureListener {
        Log.d("ENERGYDRINK DOWNLOAD", "FAILED download : ${it}")
        callback(energyDrinkItem.copy(image=""), ImageBitmap(1,1))

    }.addOnSuccessListener { taskSnapshot ->
        Log.d("ENERGYDRINK DOWNLOAD", "success download")
        val options = BitmapFactory.Options()
        val bitmap =
            BitmapFactory.decodeByteArray(
                taskSnapshot,
                0,
                taskSnapshot.size,
                options
            ).asImageBitmap()
        Log.d("ENERGYDRINK DOWNLOAD", "copied?")
        callback(energyDrinkItem, bitmap)
    }
    return this
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

