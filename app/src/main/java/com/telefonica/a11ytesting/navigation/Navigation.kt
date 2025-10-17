package com.telefonica.a11ytesting.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.telefonica.a11ytesting.screens.HomeScreen
import com.telefonica.a11ytesting.screens.SampleDetailScreen

sealed class Screen(val route: String) {
    object SampleList : Screen("sample_list")
    object SampleDetail : Screen("sample_detail/{itemId}") {
        fun createRoute(itemId: Int) = "sample_detail/$itemId"
    }
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
                onItemClick = { itemId ->
                    navController.navigate(Screen.SampleDetail.createRoute(itemId))
                }
            )
        }

        composable(Screen.SampleDetail.route) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString("itemId")?.toIntOrNull() ?: 1
            SampleDetailScreen(
                itemId = itemId,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
