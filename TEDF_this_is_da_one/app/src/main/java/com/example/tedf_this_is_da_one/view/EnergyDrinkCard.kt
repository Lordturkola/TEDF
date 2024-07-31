package com.example.tedf_this_is_da_one.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tedf_this_is_da_one.AppViewModelProvider
import com.example.tedf_this_is_da_one.data.EnergyDrinkItem
import com.example.tedf_this_is_da_one.viewmodel.HomeViewModel

@Preview
@Composable
fun EnergyDrinkCard(
    modifier: Modifier = Modifier,
    energyDrink: EnergyDrinkItem = EnergyDrinkItem(),
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        border = BorderStroke(width = 10.dp, color = Color(200, 0, 0, 1))
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                bitmap = energyDrink.bitmap,
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