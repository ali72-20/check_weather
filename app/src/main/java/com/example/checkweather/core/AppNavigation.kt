package com.example.checkweather.core

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.checkweather.Screen.search.SearchFragment
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.checkweather.Screen.Home.HomeScreenContent

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppRoutesManger.HomeScreen.route) {
        composable(AppRoutesManger.HomeScreen.route) {
           HomeScreenContent(navController = navController)
        }
        composable(AppRoutesManger.SearchScreen.route) {
            SearchFragment()
        }
    }
}

