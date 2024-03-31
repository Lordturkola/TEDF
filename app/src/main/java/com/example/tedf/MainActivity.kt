package com.example.tedf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tedf.R.drawable.logo_v3
import com.example.tedf.ui.theme.TedfTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TedfTheme {
                   TedfApp()
                }
            }
        }
    }

@Composable
fun SplashPage(slogan: String, modifier: Modifier = Modifier) {
    val image = painterResource(id = logo_v3)
    Column(verticalArrangement = Arrangement.Top) {
        Icon(
            Icons.Filled.Menu, contentDescription = "Hamburger menu, yum",
            modifier = modifier.size(80.dp)
        )

        Image(
            painter = image,
            contentDescription = "logo",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )
        SloganText(
            slogan = slogan,
            modifier = modifier.align(alignment = Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(Color(0xFFAC0000)),
            onClick = { /*TODO*/ }) {
            Text(text="Upload", fontSize = 30.sp, modifier= Modifier.padding(10.dp))
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
            fontSize = 29.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 36.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(start = 10.dp, end = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TedfApp(modifier: Modifier = Modifier) {
    Surface( modifier = Modifier.fillMaxSize()) {
        SplashPage(stringResource(R.string.slogan), modifier.wrapContentSize(Alignment.TopCenter))
    }
}