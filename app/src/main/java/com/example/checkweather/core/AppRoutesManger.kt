package com.example.checkweather.core

sealed class AppRoutesManger(val route: String) {
    data object HomeScreen : AppRoutesManger("home")
    data object SearchScreen : AppRoutesManger("search")
     object DaysForcastScreen{
         const val rounte = "days_forecast/{cityName}"
         fun passCity(cityName:String) = "days_forecast/$cityName"
     }

}