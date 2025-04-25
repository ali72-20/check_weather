package com.example.checkweather
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.checkweather.ui.theme.CheckWeatherTheme
import com.example.checkweather.ui.theme.White

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CheckWeatherTheme {
                HomeScreenContent()
            }
        }
    }
}

@Composable
fun HomeScreenContent() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->
        HomeScreenBody(modifier = Modifier.padding(paddingValues))
    }
}


@Composable
fun HomeScreenBody(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.city_view), contentScale = ContentScale.Crop)
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp, start = 16.dp)

    ) {
        LocationRowView()
        CurrentWeatherView(modifier)
    }
}

@Composable
fun LocationRowView() {
    Row(modifier = Modifier.wrapContentSize()) {
        Icon(
            painterResource(R.drawable.location_icon),
            contentDescription = stringResource(R.string.location_icon),
            tint = Color.White
        )
        Text(
            text = stringResource(R.string.paris),
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = 4.dp, top = 4.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            contentDescription = stringResource(R.string.search_icon),
            tint = White,
            painter = painterResource(R.drawable.search_icon),
            modifier = Modifier.padding(end = 16.dp)
        )
    }
}
@Composable
fun CurrentWeatherView(modifier: Modifier) {
    Column(
        Modifier.fillMaxSize().padding(top = 48.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "June 07",
            fontSize = 40.sp,
            color = White,
            fontWeight = FontWeight.Bold
        )
        Box(
            Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Updated at 6/7/2025 10:00PM",
            color = Color.White,
            fontSize = 16.sp
        )

    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenContentPreview() {
    HomeScreenContent()
}