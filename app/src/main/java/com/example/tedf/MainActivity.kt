package com.example.tedf

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tedf.R.drawable.logo_v3
import com.example.tedf.data.DataSource
import com.example.tedf.model.EnergyDrink
import com.example.tedf.ui.theme.TedfTheme
import com.example.tedf.viewmodel.RankingViewModel

private const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                Log.d(TAG,"onCreate called")
                TedfTheme {
                       TedfApp()
                    }
                }
            }
        override fun onStart(){
            super.onStart()
            Log.d(TAG, "onStart Called")
        }
    override fun onResume(){
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onStop(){
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onDestroy(){
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
    }

val titleFontFamily = FontFamily(
    Font(R.font.jaini_regular)
)
@Composable
fun SplashPage(slogan: String, rankingViewModel: RankingViewModel = viewModel(), modifier: Modifier = Modifier) {

    val uiStateSpashPage by rankingViewModel.uiState.collectAsState()
    val image = painterResource(id = logo_v3)
    var descriptionField by remember {mutableStateOf("Say something...?")}

    Column(verticalArrangement = Arrangement.Top, modifier = Modifier.verticalScroll(
        rememberScrollState())) {
        Icon(
            Icons.Filled.Menu, contentDescription = "Hamburger menu, yum",
            modifier = modifier.size(40.dp)
        )

        Image(
            painter = image,
            contentDescription = "logo",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .align(Alignment.CenterHorizontally)
        )
        SloganText(
            slogan = slogan,
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(20.dp))
/*
        CommentField(
            value = descriptionField,
            onValueChange = {descriptionField = it},
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            leadingIcon = R.drawable.icon
        )
*/
        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = "Ranking - top three",
            fontSize = 40.sp,
            color = colorResource(R.color.titleColor),
            fontFamily = titleFontFamily ,
            modifier = Modifier.align(Alignment.CenterHorizontally))
        HorizontalDivider(
            color=colorResource(id = R.color.titleColor),
            thickness = 3.dp,
            modifier = Modifier.padding(20.dp))
        //EnergyDrinkList(energyDrinkList = DataSource().loadEnergyDrinks(), Modifier.height(400.dp))
        EnergyDrinkTopThreeList(modifier=Modifier.align(Alignment.CenterHorizontally))
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(colorResource(R.color.titleColor)),
            onClick = { /*TODO*/ }) {
            Text(text="See full list", fontSize = 30.sp, modifier= Modifier.padding(10.dp))
        }


    }

}
@Composable
fun SloganText(slogan: String, modifier: Modifier = Modifier) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = slogan,
            fontFamily = FontFamily.Default,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 36.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
        )
    }
}

@Composable
fun EnergyDrinkCard(energyDrink: EnergyDrink, modifier:Modifier = Modifier){
    Card (modifier = modifier){
        Column{
         Image(painter = painterResource(id = energyDrink.imageResourceId), 
             contentDescription = energyDrink.stringResourceId,
             modifier = modifier
                 .fillMaxWidth()
                 .height(194.dp),
            contentScale = ContentScale.Crop)
        }
        Text(text = energyDrink.stringResourceId,
        modifier = modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineSmall
            )
    }
}
@Composable
fun TopDrinkCard(energyDrink: EnergyDrink, modifier:Modifier = Modifier){
    Text(
        text = "Rank ${energyDrink.ranking}. ${energyDrink.stringResourceId}",
        fontSize = 40.sp,
        color = colorResource(R.color.titleColor),
        fontFamily = titleFontFamily ,
        modifier = modifier
    )
    EnergyDrinkCard(energyDrink,
        modifier
            .padding(30.dp)
            .size(150.dp))
}
@Composable
fun EnergyDrinkList(energyDrinkList: List<EnergyDrink>, modifier: Modifier = Modifier){
    LazyColumn (modifier = modifier){
        items(energyDrinkList){
            energyDrink ->
            EnergyDrinkCard(
                energyDrink = energyDrink,
                modifier = modifier.padding(8.dp))
        }
    }
}

@Composable
fun EnergyDrinkTopThreeList(modifier: Modifier = Modifier){
    Column (modifier = modifier){
    DataSource().loadTopThree().forEach{
        TopDrinkCard(energyDrink = it, modifier = modifier)
    }
    }
}
@Composable
fun UploadReviewPage(){

}
@Composable
fun CommentField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    keyboardOptions: KeyboardOptions,
    @DrawableRes leadingIcon: Int
){

    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        keyboardOptions = keyboardOptions,
        leadingIcon = { Icon(imageVector = Icons.Filled.Star,null) },
        label = {
            Text(text="Description")
        }
    )

}
internal fun aTestMethod(){

}
@Preview
@Composable
private fun EnergyDrinkPreview(){
val energyDrink = EnergyDrink(R.string.monster_1.toString(), R.drawable.monster_1, 4)
    EnergyDrinkCard(energyDrink = energyDrink)
    TopDrinkCard(energyDrink = energyDrink)
}
@Preview(showBackground = true)
@Composable
fun TedfApp(modifier: Modifier = Modifier) {
    TedfTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            SplashPage(
                slogan = stringResource(R.string.slogan),
                modifier = modifier.wrapContentSize(Alignment.TopCenter)
            )
        }
    }
}