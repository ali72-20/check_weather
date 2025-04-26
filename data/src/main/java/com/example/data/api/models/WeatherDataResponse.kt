package com.example.data.api.models

import com.example.domain.entities.DataItemEntity
import com.example.domain.entities.WeatherDataEntity
import com.example.domain.entities.WeatherEntity
import com.google.gson.annotations.SerializedName

data class WeatherDataResponse(

    @field:SerializedName("country_code")
    val countryCode: String? = null,

    @field:SerializedName("city_name")
    val cityName: String? = null,

    @field:SerializedName("data")
    val data: List<DataItem?>? = null,

    @field:SerializedName("timezone")
    val timezone: String? = null,

    @field:SerializedName("lon")
    val lon: String? = null,

    @field:SerializedName("state_code")
    val stateCode: String? = null,

    @field:SerializedName("lat")
    val lat: String? = null
) {
    fun toDomain(): WeatherDataEntity {
        return WeatherDataEntity(
            cityName = cityName!!,
            timeZone = timezone!!,
            data = data!!.map { it!!.toDomain() }
        )
    }
}

data class Weather(

    @field:SerializedName("code")
    val code: Int? = null,

    @field:SerializedName("icon")
    val icon: String? = null,

    @field:SerializedName("description")
    val description: String? = null
) {
    fun toDomain(): WeatherEntity {
        return WeatherEntity(
            code = code,
            icon = icon,
            description = description
        )
    }
}

data class DataItem(

    @field:SerializedName("pres")
    val pres: Int? = null,

    @field:SerializedName("moon_phase")
    val moonPhase: Any? = null,

    @field:SerializedName("wind_cdir")
    val windCdir: String? = null,

    @field:SerializedName("moonrise_ts")
    val moonriseTs: Int? = null,

    @field:SerializedName("clouds")
    val clouds: Int? = null,

    @field:SerializedName("low_temp")
    val lowTemp: Any? = null,

    @field:SerializedName("wind_spd")
    val windSpd: Any? = null,

    @field:SerializedName("ozone")
    val ozone: Int? = null,

    @field:SerializedName("pop")
    val pop: Int? = null,

    @field:SerializedName("datetime")
    val datetime: String? = null,

    @field:SerializedName("valid_date")
    val validDate: String? = null,

    @field:SerializedName("precip")
    val precip: Int? = null,

    @field:SerializedName("min_temp")
    val minTemp: Any? = null,

    @field:SerializedName("sunrise_ts")
    val sunriseTs: Int? = null,

    @field:SerializedName("weather")
    val weather: Weather? = null,

    @field:SerializedName("app_max_temp")
    val appMaxTemp: Int? = null,

    @field:SerializedName("max_temp")
    val maxTemp: Any? = null,

    @field:SerializedName("snow_depth")
    val snowDepth: Int? = null,

    @field:SerializedName("max_dhi")
    val maxDhi: Any? = null,

    @field:SerializedName("sunset_ts")
    val sunsetTs: Int? = null,

    @field:SerializedName("clouds_mid")
    val cloudsMid: Int? = null,

    @field:SerializedName("uv")
    val uv: Int? = null,

    @field:SerializedName("vis")
    val vis: Int? = null,

    @field:SerializedName("high_temp")
    val highTemp: Any? = null,

    @field:SerializedName("temp")
    val temp: Any? = null,

    @field:SerializedName("clouds_hi")
    val cloudsHi: Int? = null,

    @field:SerializedName("app_min_temp")
    val appMinTemp: Any? = null,

    @field:SerializedName("moon_phase_lunation")
    val moonPhaseLunation: Any? = null,

    @field:SerializedName("dewpt")
    val dewpt: Any? = null,

    @field:SerializedName("wind_dir")
    val windDir: Int? = null,

    @field:SerializedName("wind_gust_spd")
    val windGustSpd: Any? = null,

    @field:SerializedName("clouds_low")
    val cloudsLow: Int? = null,

    @field:SerializedName("rh")
    val rh: Int? = null,

    @field:SerializedName("slp")
    val slp: Int? = null,

    @field:SerializedName("snow")
    val snow: Int? = null,

    @field:SerializedName("wind_cdir_full")
    val windCdirFull: String? = null,

    @field:SerializedName("moonset_ts")
    val moonsetTs: Int? = null,

    @field:SerializedName("ts")
    val ts: Int? = null
) {
    fun toDomain(): DataItemEntity {
        return DataItemEntity(
            windCdir = windCdir,
            clouds = clouds,
            lowTemp = lowTemp,
            windSpd = windSpd,
            ozone = ozone,
            validDate = validDate,
            minTemp = minTemp,
            sunriseTs = sunriseTs,
            weather = weather?.toDomain(),
            appMaxTemp = appMaxTemp,
            maxTemp = maxTemp,
            snowDepth = snowDepth,
            maxDhi = maxDhi,
            highTemp = highTemp,
            temp = temp
        )
    }
}
