package com.example.data.data_source.local_data_source

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.entities.CityHistoryEntity

@Entity(tableName = "city_history")
data class CityHistory(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "city_name") val cityName: String
){
    fun toDomain():CityHistoryEntity{
        return CityHistoryEntity(
            id = id,
            cityName = cityName
        )
    }
}
