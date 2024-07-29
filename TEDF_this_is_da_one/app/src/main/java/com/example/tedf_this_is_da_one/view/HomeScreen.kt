package com.example.tedf_this_is_da_one.view

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
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
import com.example.tedf_this_is_da_one.viewmodel.HomeViewModel
import com.example.tedf_this_is_da_one.viewmodel.energyDrinkItem
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController,
    onNextClicked: () -> Unit,

    ) {
    val state by viewModel.uiState.collectAsState()
    val TedfCollection: CollectionReference = FirebaseFirestore.getInstance().collection("TEDF")

    val db = TedfCollection.document("energydrinks")
    val entry = hashMapOf(
        Pair("name", "mmmonster"),
        Pair("rating", "0"),
        Pair("price", "24"),
        Pair("caffeine", "100"),
        Pair("user", "Adolf"),
    )
    db.set(entry).addOnSuccessListener {
        Log.d(TAG, "successfullly uploaded")
    }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error uploading.", exception)
        }
    Text(text = state.energyDrinkItem.name, modifier = Modifier.clickable {
        db.get().addOnSuccessListener {
            if (it.exists()) {
                viewModel.updateState(
                    greeting = "uploaded in homo",
                    energyDrinkItem = it.toObject<energyDrinkItem>(energyDrinkItem::class.java)
                )

            }
            Log.d(TAG, "successfullly downloaded")
        }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error downloading.", exception)

            }
    })
    Spacer(modifier = Modifier.padding(40.dp))
    OutlinedButton(onClick = onNextClicked) {
        Text(text = "Next")

    }
}