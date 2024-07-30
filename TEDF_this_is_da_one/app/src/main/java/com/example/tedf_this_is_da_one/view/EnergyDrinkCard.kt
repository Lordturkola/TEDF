package com.example.tedf_this_is_da_one.view

import android.content.ContentValues
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tedf_this_is_da_one.AppViewModelProvider
import com.example.tedf_this_is_da_one.data.EnergyDrinkItem
import com.example.tedf_this_is_da_one.viewmodel.HomeViewModel
import com.google.firebase.storage.FirebaseStorage

@Preview
@Composable
fun EnergyDrinkCard(
    modifier: Modifier = Modifier,
    energyDrink: EnergyDrinkItem = EnergyDrinkItem(),
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val state by viewModel.uiState.collectAsState()

    val coroutineScope = rememberCoroutineScope()
    var _bitmap = ImageBitmap(100, 100)
    val bitmap by remember {
        mutableStateOf(_bitmap)
    }
    Card(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(width = 10.dp, color = Color(200, 0, 0, 1))
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val uploadTask =
                FirebaseStorage.getInstance().getReference().child(energyDrink.image)
                    .getBytes(
                        Long.MAX_VALUE
                    )
            uploadTask.addOnFailureListener {
                // Handle unsuccessful uploads
            }.addOnSuccessListener { taskSnapshot ->
                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                // ...
                val options = BitmapFactory.Options()
                viewModel.updateBitmap(
                    BitmapFactory.decodeByteArray(taskSnapshot, 0, taskSnapshot.size, options)
                        .asImageBitmap()
                )
                Log.d(
                    ContentValues.TAG,
                    "Successfully downloaded image ${taskSnapshot.size}"
                )

            }

            Image(
                painter = BitmapPainter(state.imageBitmap),
                contentDescription = null,
            )
            Image(
                bitmap = state.imageBitmap,
                contentDescription = null,
            )

            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(text = energyDrink.rating + " /3 BCAA")
                Text(text = energyDrink.name)
                Text(text = energyDrink.caffeine + " mg/100ml")
                Text(text = energyDrink.price + " kr")
            }
        }
    }
}