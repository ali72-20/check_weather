package com.example.checkweather

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.checkweather.ui.theme.CheckWeatherTheme
import android.os.Handler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.draw.paint
import androidx.compose.ui.res.painterResource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CheckWeatherTheme {
                StartSplash()
                SplashActivityContent()
            }
        }
    }

    @Composable
    private fun StartSplash() {
        Handler(mainLooper).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent);
            finish()
        }, 2500)
    }
}


@Composable
fun SplashActivityContent() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(R.drawable.splash_icon),
            ).background(color = MaterialTheme.colorScheme.secondary)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSplashActivityContent() {
    SplashActivityContent()
}