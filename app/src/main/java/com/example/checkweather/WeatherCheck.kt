package com.example.checkweather

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherCheck : Application (){
    override fun onCreate() {
        super.onCreate()
    }
}