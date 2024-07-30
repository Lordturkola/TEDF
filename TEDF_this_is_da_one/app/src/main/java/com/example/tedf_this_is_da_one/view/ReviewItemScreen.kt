package com.example.tedf_this_is_da_one.view

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tedf_this_is_da_one.AppViewModelProvider
import com.example.tedf_this_is_da_one.viewmodel.HomeViewModel

@Composable
fun ReviewItemScreen(
    modifier: Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onNextClicked: ()->Unit,
    onCancelClicked: ()->Unit,
) {
    val db = viewModel.TedfCollection.document("energydrinks")

    Column {
        Text(text = "Rev")
        OutlinedButton(onClick = onNextClicked) {
            Text(text = "Next")
        }

        OutlinedButton(onClick = onCancelClicked) {
            Text(text = "Cancel")
        }
    }
}