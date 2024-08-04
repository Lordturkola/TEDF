package com.example.tedf_this_is_da_one.view

import android.content.Context
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tedf_this_is_da_one.AppViewModelProvider

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun ReviewItemScreen(
    modifier: Modifier = Modifier,
    viewModel: EnergyDrinkViewModel = viewModel(factory = AppViewModelProvider.Factory),
    onNextClicked: () -> Unit,
    onCancelClicked: () -> Unit,
    context: Context,
) {
    val state = viewModel.uiState
    val itemState = viewModel.itemState
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .background(Color.White)
    ) {
        OutlinedTextField(
            value = itemState.name,
            onValueChange = { viewModel.updateItem(itemState.copy(name = it)) },
            label = {
                Text("Energy drink title")
            })
        OutlinedTextField(
            value = itemState.rating,
            onValueChange = { viewModel.updateItem(itemState.copy(rating = it)) },
            label = {
                Text("your rating (0 - 3)")
            })
        OutlinedTextField(
            value = itemState.price,
            onValueChange = { viewModel.updateItem(itemState.copy(price = it)) },
            label = {
                Text("price")
            })
        OutlinedTextField(
            value = itemState.caffeine,
            onValueChange = { viewModel.updateItem(itemState.copy(caffeine = it)) },
            label = {
                Text("caffeine amount")
            })

        OutlinedTextField(
            value = itemState.user,
            onValueChange = { viewModel.updateItem(itemState.copy(user = it)) },
            label = {
                Text("your name ( optional)")
            })

        ImagePicker {
            val source = ImageDecoder.createSource(context.contentResolver, it)
            viewModel.updateItem(itemState.copy(image = it.toString()))
            viewModel.updateUiState(
                state.copy(
                    imageState = ImageDecoder.decodeBitmap(source).asImageBitmap()
                )
            )
        }
        OutlinedButton(onClick = {
            viewModel.upload()
            onNextClicked()
        }) {
            Text(text = "Upload")
        }
        OutlinedButton(onClick = onCancelClicked) {
            Text(text = "Cancel")
        }
    }
}

@Composable
fun ImagePicker(onImageSelected: (Uri) -> Unit) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri: Uri? -> uri?.let { onImageSelected(it) } }
    )

    Button(
        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
        onClick = {
            launcher.launch(PickVisualMediaRequest(
                ActivityResultContracts.PickVisualMedia.ImageOnly
            ))
        }
    ) {
        Text("Select Image", color = Color.White)
    }
}