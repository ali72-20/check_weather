package com.example.data.api.models

data class WeatherRequestModel(
    val city : String,
    val country: String,
    val days: Int
)
