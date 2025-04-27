package com.example.checkweather.Screen.Home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.checkweather.LocalWeatherData
import com.example.checkweather.R
import com.example.checkweather.core.AppRoutesManger
import com.example.checkweather.core.Dimens
import com.example.checkweather.ui.theme.White

@Composable
fun LocationRowView(navController: NavController) {
    Row(modifier = Modifier.wrapContentSize()) {
        Icon(
            painterResource(R.drawable.location_icon),
            contentDescription = stringResource(R.string.location_icon),
            tint = Color.White
        )
        Text(
            text = LocalWeatherData.current.cityName,
            color = Color.White,
            fontSize = 24.sp,
            modifier = Modifier.padding(start = Dimens.PaddingXXSmall, top = Dimens.PaddingXXSmall)
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            contentDescription = stringResource(R.string.search_icon),
            tint = White,
            painter = painterResource(R.drawable.search_icon),
            modifier = Modifier.padding(end = Dimens.PaddingSmall).clickable{
                navController.navigate(AppRoutesManger.SearchScreen.route)
            }
        )
    }
}


