package com.example.tedf.model

import androidx.annotation.DrawableRes

data class EnergyDrink(
    val stringResourceId: String,
    @DrawableRes val imageResourceId: Int,
    val ranking: Int,
)