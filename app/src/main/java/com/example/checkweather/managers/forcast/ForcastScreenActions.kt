package com.example.checkweather.managers.forcast

sealed class ForcastScreenActions {
    data class GetForcastDataAction(
        val cityName: String?
    ) : ForcastScreenActions() {}
}

