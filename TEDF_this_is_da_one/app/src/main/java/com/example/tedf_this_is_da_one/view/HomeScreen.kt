package com.example.tedf_this_is_da_one.view

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.tedf_this_is_da_one.AppViewModelProvider
import com.example.tedf_this_is_da_one.R
import com.example.tedf_this_is_da_one.TedfApplication
import com.example.tedf_this_is_da_one.viewmodel.HomeViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navController: NavHostController,
    onNextClicked: () -> Unit,
    context: Context,

    ) {
    val state by viewModel.uiState.collectAsState()
    val db = viewModel.TedfCollection
    Log.d(TAG, "starting upload")
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier.padding(10.dp),
        topBar = {
            HomeLogo(modifier = Modifier.height(150.dp))
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNextClicked,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(20.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add drink")
            }
        },
        bottomBar = {
            Text(
                text = "TEDF Colllective 2024",
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxWidth()
                    .padding(10.dp),
                color = Color.Red,
            )
        }
    ) { innerpadding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerpadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                items(items = state.energyDrinkItems) { item ->
                    EnergyDrinkCard(energyDrink = item,
                        modifier = Modifier
                            .padding(10.dp)
                            .clickable { })
                }

            }
        }
    }
}

@Preview
@Composable
fun HomeLogo(modifier: Modifier = Modifier) {
    val fontName = GoogleFont("Lobster Two")

    val fontFamily = FontFamily(
        Font(googleFont = fontName, fontProvider = TedfApplication().container.TedfFontProvider)
    )
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(Color.Black)
            .fillMaxWidth()
            .padding(0.dp, 0.dp, 0.dp, 20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "home",
            contentScale = ContentScale.FillHeight,
            modifier = modifier
        )
        Text(
            text = "Terrible Energy Drink Friday",
            fontFamily = fontFamily,
            color = Color.Red,
            fontSize = 30.sp
        )
    }
}

