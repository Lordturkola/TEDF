package com.example.tedf_this_is_da_one

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tedf_this_is_da_one.ui.theme.TEDF_this_is_da_oneTheme
import com.example.tedf_this_is_da_one.viewmodel.HomeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TEDF_this_is_da_oneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    TedfApp()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory)) {
    val uiState by viewModel.uiState.collectAsState()
    Text(
        text = uiState.greeting,
        modifier = modifier
    )
    Button(onClick = { viewModel.updateState("No, fucckk you!!!") }) {

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TEDF_this_is_da_oneTheme {
        Greeting("Android")
    }
}