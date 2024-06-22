package com.example.tedf.ui.EnergyDrink

class EnergyDrinkEntryScreen {
    ItemEntryBody(
    energyDrinkUiState = viewModel.itemUiState,
    onItemValueChange = viewModel::updateUiState,
    onSaveClick =
    {},
    modifier = Modifier
    .padding(innerPadding)
    .verticalScroll(rememberScrollState())
    .fillMaxWidth()
    )
}