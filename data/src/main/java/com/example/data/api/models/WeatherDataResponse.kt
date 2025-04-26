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
            cityName = cityName.orEmpty(),
            timeZone = timezone.orEmpty(),
            data = data?.mapNotNull { it?.toDomain() } ?: emptyList()
        )
    }
}

data class Weather(

    @field:SerializedName("code")
    val code: Double? = null,

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
    val pres: Double? = null,

    @field:SerializedName("moon_phase")
    val moonPhase: Double? = null,

    @field:SerializedName("wind_cdir")
    val windCdir: String? = null,

    @field:SerializedName("moonrise_ts")
    val moonriseTs: Double? = null,

    @field:SerializedName("clouds")
    val clouds: Double? = null,

    @field:SerializedName("low_temp")
    val lowTemp: Double? = null,

    @field:SerializedName("wind_spd")
    val windSpd: Double? = null,

    @field:SerializedName("ozone")
    val ozone: Double? = null,

    @field:SerializedName("pop")
    val pop: Double? = null,

    @field:SerializedName("datetime")
    val datetime: String? = null,

    @field:SerializedName("valid_date")
    val validDate: String? = null,

    @field:SerializedName("precip")
    val precip: Double? = null,

    @field:SerializedName("min_temp")
    val mDoubleemp: Double? = null,

    @field:SerializedName("sunrise_ts")
    val sunriseTs: Double? = null,

    @field:SerializedName("weather")
    val weather: Weather? = null,

    @field:SerializedName("app_max_temp")
    val appMaxTemp: Double? = null,

    @field:SerializedName("max_temp")
    val maxTemp: Double? = null,

    @field:SerializedName("snow_depth")
    val snowDepth: Double? = null,

    @field:SerializedName("max_dhi")
    val maxDhi: Any? = null,

    @field:SerializedName("sunset_ts")
    val sunsetTs: Double? = null,

    @field:SerializedName("clouds_mid")
    val cloudsMid: Double? = null,

    @field:SerializedName("uv")
    val uv: Double? = null,

    @field:SerializedName("vis")
    val vis: Double? = null,

    @field:SerializedName("high_temp")
    val highTemp: Double? = null,

    @field:SerializedName("temp")
    val temp: Double? = null,

    @field:SerializedName("clouds_hi")
    val cloudsHi: Double? = null,

    @field:SerializedName("app_min_temp")
    val appMDoubleemp: Double? = null,

    @field:SerializedName("moon_phase_lunation")
    val moonPhaseLunation: Double? = null,

    @field:SerializedName("dewpt")
    val dewpt: Double? = null,

    @field:SerializedName("wind_dir")
    val windDir: Double? = null,

    @field:SerializedName("wind_gust_spd")
    val windGustSpd: Double? = null,

    @field:SerializedName("clouds_low")
    val cloudsLow: Double? = null,

    @field:SerializedName("rh")
    val rh: Double? = null,

    @field:SerializedName("slp")
    val slp: Double? = null,

    @field:SerializedName("snow")
    val snow: Double? = null,

    @field:SerializedName("wind_cdir_full")
    val windCdirFull: String? = null,

    @field:SerializedName("moonset_ts")
    val moonsetTs: Double? = null,

    @field:SerializedName("ts")
    val ts: Double? = null
) {
    fun toDomain(): DataItemEntity {
        return DataItemEntity(
            windCdir = windCdir,
            clouds = clouds,
            lowTemp = lowTemp,
            windSpd = windSpd,
            ozone = ozone,
            validDate = validDate,
            mDoubleemp = mDoubleemp,
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
