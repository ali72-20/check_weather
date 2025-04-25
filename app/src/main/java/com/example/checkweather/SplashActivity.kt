package com.example.checkweather

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.checkweather.ui.theme.CheckWeatherTheme
import android.os.Handler
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CheckWeatherTheme {
           Handler(mainLooper).postDelayed({
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent);
                    finish()
                },2500)
                setContent(
                    content = {
                        SplashActivityContent()
                    }
                )
            }
        }
    }
}


@Composable
fun SplashActivityContent(){
    Scaffold (modifier =Modifier.fillMaxSize()){
        Text(text = "splash", modifier = Modifier.padding(it))
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSplashActivityContent(){
    SplashActivityContent()
}