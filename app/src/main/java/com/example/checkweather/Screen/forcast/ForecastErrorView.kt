package com.example.checkweather.Screen.forcast

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.checkweather.LocalWeatherData
import com.example.checkweather.R
import com.example.checkweather.core.Dimens
import com.example.checkweather.managers.forcast.ForcastScreenActions
import com.example.checkweather.managers.forcast.FortCastViewModel

@Composable
fun ForecastErrorView(errorMessage: String, viewModel: FortCastViewModel,cityName:String) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(Dimens.PaddingMedium)
            .fillMaxWidth()
    ) {
        Image(
            painterResource(R.drawable.warning),
            contentDescription = stringResource(R.string.waring),
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.height(Dimens.PaddingXXSmall))
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.bodyMedium.copy(color = Color.White),
            modifier = Modifier.padding(Dimens.PaddingMedium)
        )
        Spacer(modifier = Modifier.height(Dimens.PaddingXXSmall))
        Image(
            painterResource(R.drawable.reload),
            contentDescription = stringResource(R.string.reload),
            modifier = Modifier
                .clickable {
                    viewModel.doAction(ForcastScreenActions.GetForcastDataAction(cityName = cityName))
                }
                .size(40.dp)
                .padding(Dimens.PaddingSmall)
                .background(MaterialTheme.colorScheme.secondary, RoundedCornerShape(50))
                .clickable {
                    viewModel.doAction(ForcastScreenActions.GetForcastDataAction(cityName = cityName))
                }
                .padding(Dimens.PaddingSmall)
        )
    }
}
