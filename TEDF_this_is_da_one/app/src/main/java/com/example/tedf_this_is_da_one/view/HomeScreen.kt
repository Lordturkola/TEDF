package com.example.tedf_this_is_da_one.view

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tedf_this_is_da_one.AppViewModelProvider
import com.example.tedf_this_is_da_one.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController,
    onNextClicked: () -> Unit,

    ) {
    Text("Home")
    Spacer(modifier = Modifier.padding(40.dp))
    OutlinedButton(onClick = onNextClicked) {
        Text(text = "Next")

    }
}