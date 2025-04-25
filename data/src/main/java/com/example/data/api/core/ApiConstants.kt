package com.example.data.api.core

class ApiConstants private constructor(){
    companion object{
        const val BASE_URL = "https://api.weatherbit.io/v2.0/"
        const val API_KEY = "cd882689df9d4a4196897ad9c3e0297a"
        const val CITY_KEY = "city";
        const val COUNTRY_KEY = "country"
        const val DAYS_KEY = "days"
        const val FORECAST_DAILY_END_POINT = "forecast/daily";
    }
}