package com.example.tedf_this_is_da_one

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tedf_this_is_da_one.data.AppContainer
import com.example.tedf_this_is_da_one.view.HomeScreen
import com.example.tedf_this_is_da_one.view.ReviewItemScreen
import com.example.tedf_this_is_da_one.view.ViewItemScreen

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun TedfApp(navHostController: NavHostController = rememberNavController()) {

    NavHost(
        navController = navHostController,
        startDestination = AppContainer.Companion.HomeScreenNav.Home.name,
        modifier = Modifier.padding(5.dp)
    ) {
        composable(route = AppContainer.Companion.HomeScreenNav.Home.name) {
            HomeScreen(
                modifier = Modifier,
                navController = navHostController,
                onNextClicked = { navHostController.navigate(AppContainer.Companion.HomeScreenNav.Review.name)},
                context = LocalContext.current)
        }
        composable(route = AppContainer.Companion.HomeScreenNav.View.name) {
            ViewItemScreen(
                modifier = Modifier,
                onNextClicked =
                { navHostController.navigate(AppContainer.Companion.HomeScreenNav.Review.name) },
                onCancelClicked = {
                    navHostController.popBackStack(route = AppContainer.Companion.HomeScreenNav.Home.name, inclusive = false)
                })
            // gets the context of the the current active state navigated to ?
            val context = LocalContext.current
        }
        composable(route = AppContainer.Companion.HomeScreenNav.Review.name) {
            ReviewItemScreen(
                context = LocalContext.current,
                modifier = Modifier,
                onNextClicked =
                { navHostController.navigate(AppContainer.Companion.HomeScreenNav.Home.name) },
                onCancelClicked = {
                    navHostController.popBackStack(route = AppContainer.Companion.HomeScreenNav.Home.name, inclusive = false)
                })
        }
    }
}