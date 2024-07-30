package com.example.tedf_this_is_da_one.view

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tedf_this_is_da_one.AppViewModelProvider
import com.example.tedf_this_is_da_one.data.EnergyDrinkItem
import com.example.tedf_this_is_da_one.viewmodel.HomeViewModel


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController,
    onNextClicked: () -> Unit,

    ) {
    val state by viewModel.uiState.collectAsState()
    val db = viewModel.TedfCollection
    Log.d(TAG, "starting upload")

    Column {
        Text(text = state.energyDrinkItems.firstOrNull().toString())
        Button(onClick = {
            val entry = hashMapOf(
                Pair("name", "mmmonster"),
                Pair("rating", "0"),
                Pair("price", "24"),
                Pair("caffeine", "100"),
                Pair("user", "kokkkkol"),
                Pair("image", "energydrinks/monster.jpg")
            )
            db.document("cat").set(entry).addOnSuccessListener {
                Log.d(TAG, "successfullly uploaded")
            }
                .addOnFailureListener { exception ->
                    Log.e(TAG, "Error uploading.", exception)
                }
            /*
            val drawable: Drawable? = Resources.getSystem().getDrawable(com.example.tedf_this_is_da_one.R.drawable.monster, null)
           // val drawable2: Drawable? = ResourcesCompat.getDrawable(Current., R.drawable.monster, null)
            val data = EnergyDrinkItem.toByteArray((drawable as Drawable).toBitmap())

            val uploadTask =
                FirebaseStorage.getInstance().getReference().child("energydrinks/monster.jpg")
                    .putBytes(data)
            uploadTask.addOnFailureListener {
                // Handle unsuccessful uploads
            }.addOnSuccessListener { taskSnapshot ->
                // taskSnapshot.metadata contains file metadata such as size, content-type, etc.
                // ...
                Log.d(TAG, "Succesfully uploaded image")
            }
*/
        }) {
            Text(text = "click me to upload")
        }
        EnergyDrinkCard(energyDrink = state.energyDrinkItems.firstOrNull() ?: EnergyDrinkItem())
        Spacer(modifier = Modifier.padding(40.dp))
        OutlinedButton(onClick = onNextClicked) {
            Text(text = "Next")

        }
    }

}

@Composable
fun ImagePicker(onImageSelected: (Uri) -> Unit) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? -> uri?.let { onImageSelected(it) } }
    )

    Button(
        onClick = { launcher.launch("image/*") }
    ) {
        Text("Select Image")
    }
}