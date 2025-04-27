package com.example.checkweather.core

class ConstKey private constructor(){
    companion object{
        const val cityName = "cityName"
        fun getIconUrl(icon: String):String{
            return "https://www.weatherbit.io/static/img/icons/${icon}.png"
        }
    }
}