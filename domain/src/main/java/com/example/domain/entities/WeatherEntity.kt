package com.example.domain.entities

data class WeatherDataEntity(
    val cityName: String,
    val timeZone : String,
    val data : List<DataItemEntity>
)


data class DataItemEntity(
    val windCdir: String? = null,
    val clouds: Int? = null,
    val lowTemp: Any? = null,

    val windSpd: Any? = null,
    val ozone: Int? = null,

    val validDate: String? = null,

    val minTemp: Any? = null,

    val sunriseTs: Int? = null,

    val weather: WeatherEntity? = null,

    val appMaxTemp: Int? = null,

    val maxTemp: Any? = null,

    val snowDepth: Int? = null,

    val maxDhi: Any? = null,

    val highTemp: Any? = null,

    val temp: Any? = null,

)

data class WeatherEntity(
    val code: Int? = null,

    val icon: String? = null,

    val description: String? = null
)
