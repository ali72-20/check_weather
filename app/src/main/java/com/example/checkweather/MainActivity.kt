package com.example.checkweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.checkweather.core.AppNavigation
import com.example.checkweather.ui.theme.CheckWeatherTheme
import com.example.domain.entities.WeatherDataEntity
import dagger.hilt.android.AndroidEntryPoint

val LocalWeatherData = staticCompositionLocalOf<WeatherDataEntity> {
    error("No weather data found! Did you forget to provide it?")
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CheckWeatherTheme {
                AppNavigation()
            }
        }
    }
}
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun HomeScreenContentPreview() {
//    HomeScreenContent()
//}