package com.example.checkweather.Screen.forcast

import android.R.style
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.checkweather.R
import com.example.checkweather.core.ConstKey
import com.example.checkweather.core.Dimens
import com.example.checkweather.managers.forcast.ForcastScreenActions
import com.example.checkweather.managers.forcast.ForcastScreenUIState
import com.example.checkweather.managers.forcast.FortCastViewModel
import com.example.domain.entities.DataItemEntity


@Composable
fun DaysForcastFragment(
    viewModel: FortCastViewModel = hiltViewModel<FortCastViewModel>()
) {
    val uiState by viewModel.forcastUiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.doAction(ForcastScreenActions.GetForcastDataAction("Cairo"))
    }
    DaysForcastScreenBody(uiState,viewModel)
}


@Composable
fun DaysForcastScreenBody(state: ForcastScreenUIState,viewModel: FortCastViewModel) {
    when (state) {
        is ForcastScreenUIState.ForcastScreenLoadingState -> {
            ShimmerEffectLoadingView()
        }

        is ForcastScreenUIState.ForcastScreenSuccessState -> {
            DaysListView(state.weatherData.data)
        }

        is ForcastScreenUIState.ForcastScreenErrorState -> {
            ErrorView(state.errorMessage, viewModel)
        }
    }
}

@Composable
fun DaysListView(days: List<DataItemEntity>) {
    LazyColumn(modifier = Modifier.padding(Dimens.PaddingSmall)) {
        items(days) { item ->
            DaysForcastListItem(item)
        }
    }
}

@Composable
fun DaysForcastListItem(dataItemEntity: DataItemEntity) {
    Box(
        modifier = Modifier
            .padding(
                vertical = Dimens.PaddingXSmall,
                horizontal = Dimens.PaddingSmall
            )
            .fillMaxWidth()
            .background(color = Color.LightGray, shape = RoundedCornerShape(Dimens.PaddingXSmall))
            .padding(Dimens.PaddingSmall)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column() {
                Text(
                    text = dataItemEntity.dataTime ?: "Date",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.heightIn(Dimens.PaddingXXSmall))
                WeatherIcon(dataItemEntity.weather?.icon ?: "")
                Text(
                    text = "${dataItemEntity.temp}°C",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


@Composable
fun WeatherIcon(iconCode: String) {
    Image(
        painter = rememberAsyncImagePainter(ConstKey.getIconUrl(iconCode)),
        contentDescription = "Weather Icon",
        modifier = Modifier
            .padding(bottom = Dimens.PaddingMedium)
            .size(100.dp)
    )
}

@Composable
fun ShimmerEffectLoadingView() {
    CircularProgressIndicator()
}


@Composable
fun ErrorView(errorMessage: String, viewModel: FortCastViewModel) {
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.primary)) {
        Image(
            painterResource(R.drawable.warning),
            contentDescription = stringResource(R.string.waring)
        )
        Spacer(modifier = Modifier.heightIn(Dimens.PaddingXXSmall))
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(Dimens.PaddingMedium)
        )
        Spacer(modifier = Modifier.heightIn(Dimens.PaddingXXSmall))
        Image(
            painterResource(R.drawable.reload),
            contentDescription = stringResource(R.string.reload),
            modifier = Modifier.clickable {
                viewModel.doAction(ForcastScreenActions.GetForcastDataAction(cityName = "Cairo"))
            },
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DaysForcastFragmentPreview() {
    ErrorView("Error", viewModel = hiltViewModel <FortCastViewModel>())
}