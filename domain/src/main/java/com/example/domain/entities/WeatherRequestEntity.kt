package com.example.domain.entities

data class WeatherRequestEntity(
    val city:String,
    val country: String,
    val days : Int
)
