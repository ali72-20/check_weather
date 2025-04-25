package com.example.data.api.models

import com.example.domain.entities.WeatherRequestEntity

data class WeatherRequestModel(
    val city: String,
    val country: String,
    val days: Int
) {
    companion object {
        fun fromDomain(weatherRequestEntity: WeatherRequestEntity): WeatherRequestModel {
            return WeatherRequestModel(
                city = weatherRequestEntity.city,
                country = weatherRequestEntity.country,
                days = weatherRequestEntity.days
            )
        }
    }
}
