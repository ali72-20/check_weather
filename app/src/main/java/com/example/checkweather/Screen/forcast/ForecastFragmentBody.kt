package com.example.checkweather.Screen.forcast

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.checkweather.R
import com.example.checkweather.core.Dimens
import com.example.domain.entities.WeatherDataEntity

@Composable
fun ForecastFragmentBody(
    navController: NavController,
    weatherDataEntity: WeatherDataEntity,
    cityName: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .paint(
                painterResource(R.drawable.bg),
                contentScale = ContentScale.FillBounds
            )
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimens.PaddingLarge)) {
            Icon(
                painterResource(R.drawable.arrow_back_ios_24dp_5f6368_fill0_wght400_grad0_opsz24_1),
                contentDescription = stringResource(R.string.back),
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(Dimens.PaddingMedium)
                    .size(24.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                cityName,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(Dimens.PaddingMedium)
                    .size(40.dp)
                    .clickable {
                        navController.popBackStack()
                    }
            )
        }
        Spacer(modifier = Modifier.padding(Dimens.PaddingMedium))
        DaysListView(weatherDataEntity.data)
    }
}