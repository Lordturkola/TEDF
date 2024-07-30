package com.example.tedf_this_is_da_one.data

import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

data class EnergyDrinkItem(
    val name: String = "No name",
    val rating: String = "1",
    val price: String = "1",
    val caffeine: String = "1",
    val user: String = "1",
    val image: String = "energydrinks/monster.jpg"
) {
    companion object {
        fun toByteArray(bitmap: Bitmap): ByteArray {
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, stream)
            return stream.toByteArray()
        }
    }


}

fun EnergyDrinkItem.toImage(): ImageBitmap {
    var data = ImageBitmap(1,1)
    if (this.image.isBlank()){
        return data
    }
    val uploadTask =
        FirebaseStorage.getInstance().getReference().child(this.image)
            .getBytes(
                Long.MAX_VALUE
            )
    uploadTask.addOnFailureListener {
        // Handle unsuccessful uploads
    }.addOnSuccessListener { taskSnapshot ->
        // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
        // ...
        Log.d(ContentValues.TAG, "Successfully downloaded image")
        data = BitmapFactory.decodeByteArray(taskSnapshot, 0, taskSnapshot.size).asImageBitmap()

    }
    return data
}