package com.example.tedf_this_is_da_one.view

import android.graphics.Color.parseColor
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tedf_this_is_da_one.AppViewModelProvider
import com.example.tedf_this_is_da_one.TedfApplication
import com.example.tedf_this_is_da_one.data.EnergyDrinkItem
import com.example.tedf_this_is_da_one.viewmodel.HomeViewModel

@Preview
@Composable
fun EnergyDrinkCard(
    modifier: Modifier = Modifier,
    energyDrink: EnergyDrinkItem = EnergyDrinkItem(),
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
) {
    val bitmap by remember { mutableStateOf(energyDrink.bitmap) }
    val fontName = GoogleFont("Fontdiner Swanky")
    val fontFamily = FontFamily(
        Font(
            googleFont = fontName,
            fontProvider = TedfApplication().container.TedfFontProvider
        )
    )
    val colorStops = arrayOf(
        0f to Color(200, 200, 210),
        1f to Color(255, 200, 200),
    )
    Card(
        modifier = modifier
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .background(Brush.verticalGradient(colorStops = colorStops))
        ) {
            Text(
                text = energyDrink.name,
                fontFamily = fontFamily,
                fontSize = 30.sp,
                color = Color(parseColor("#db0f0f"))
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(10.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top
                ) {
                    bitmap?.let {
                        Image(
                            bitmap = it,
                            contentDescription = null,
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier
                                .height(100.dp)
                                .padding(0.dp, 0.dp, 10.dp, 0.dp)
                        )
                    }
                }

                Column {
                    Row(horizontalArrangement = Arrangement.Start) {
                        val fontSize = 20.sp
                        Column(horizontalAlignment = Alignment.Start) {
                            Text(
                                text = energyDrink.rating + " /3 BCAA",
                                fontFamily = fontFamily,
                                fontSize = fontSize
                            )
                            Text(
                                text = energyDrink.caffeine + " mg/100ml",
                                fontFamily = fontFamily,
                                fontSize = fontSize
                            )
                            Text(
                                text = energyDrink.price + " kr",
                                fontFamily = fontFamily,
                                fontSize = fontSize
                            )
                        }
                    }
                }
            }
        }
    }
}
