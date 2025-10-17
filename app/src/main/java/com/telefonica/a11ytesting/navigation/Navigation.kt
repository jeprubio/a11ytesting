package com.telefonica.a11ytesting.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.telefonica.a11ytesting.screens.HomeScreen
import com.telefonica.a11ytesting.screens.FirstSampleScreen

sealed class Screen(val route: String) {
    object SampleList : Screen("sample_list")
    object FirstSample : Screen("first_sample")
}

@Composable
fun A11yTestingNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SampleList.route
    ) {
        composable(Screen.SampleList.route) {
            HomeScreen(
                onFirstItemClicked = {
                    navController.navigate(Screen.FirstSample.route)
                }
            )
        }

        composable(Screen.FirstSample.route) {
            FirstSampleScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
