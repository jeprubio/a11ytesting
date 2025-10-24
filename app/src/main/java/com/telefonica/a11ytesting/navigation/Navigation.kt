package com.telefonica.a11ytesting.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.telefonica.a11ytesting.screens.HomeScreen
import com.telefonica.a11ytesting.screens.FirstSampleScreen
import com.telefonica.a11ytesting.screens.SecondSampleScreen

sealed class Screen(val route: String) {
    object SampleList : Screen("sample_list")
    object FirstSample : Screen("first_sample")
    object SecondSample : Screen("second_sample")
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
                },
                onSecondItemClicked = {
                    navController.navigate(Screen.SecondSample.route)
                },
            )
        }

        composable(Screen.FirstSample.route) {
            FirstSampleScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Screen.SecondSample.route) {
            SecondSampleScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
