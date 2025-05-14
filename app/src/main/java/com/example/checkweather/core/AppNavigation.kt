package com.example.checkweather.core

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import com.example.checkweather.Screen.search.SearchFragment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.checkweather.Screen.Home.HomeScreenContent
import com.example.checkweather.Screen.forcast.DaysForcastFragment
import dagger.hilt.android.qualifiers.ApplicationContext

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppRoutesManger.HomeScreen.route) {
        composable(AppRoutesManger.HomeScreen.route) {
           HomeScreenContent(navController = navController)
        }
        composable(AppRoutesManger.SearchScreen.route) {
            SearchFragment(navController = navController)
        }
        composable(
            route = AppRoutesManger.DaysForcastScreen.rounte,
            arguments = listOf(
                navArgument(ConstKey.cityName) {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString(ConstKey.cityName) ?: "Cairo"
            DaysForcastFragment(navController = navController, cityName = cityName)
        }
    }
}

