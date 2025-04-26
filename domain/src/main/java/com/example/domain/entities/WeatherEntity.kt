package com.example.domain.entities

data class WeatherDataEntity(
    val cityName: String,
    val timeZone : String,
    val data : List<DataItemEntity>
)


data class DataItemEntity(
    val windCdir: String? = null,
    val clouds: Double? = null,
    val lowTemp: Any? = null,

    val windSpd: Any? = null,
    val ozone: Double? = null,

    val validDate: String? = null,

    val mDoubleemp: Any? = null,

    val sunriseTs: Double? = null,

    val weather: WeatherEntity? = null,

    val appMaxTemp: Double? = null,

    val maxTemp: Any? = null,

    val snowDepth: Double? = null,

    val maxDhi: Any? = null,

    val highTemp: Any? = null,

    val temp: Any? = null,

)

data class WeatherEntity(
    val code: Double? = null,

    val icon: String? = null,

    val description: String? = null
)
