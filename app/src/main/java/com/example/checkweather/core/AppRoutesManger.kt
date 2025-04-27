package com.example.checkweather.core

sealed class AppRoutesManger(val route: String) {
    data object HomeScreen : AppRoutesManger("home")
    data object SearchScreen : AppRoutesManger("search")
    data object DaysForcastScreen:AppRoutesManger("days_forcast")
}