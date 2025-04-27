package com.example.checkweather.Screen.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.checkweather.R
import com.example.checkweather.core.ConstKey
import com.example.checkweather.core.Dimens
import com.example.checkweather.managers.search.SearchFragmentStates
import com.example.checkweather.managers.search.SearchViewModel
import com.example.checkweather.ui.theme.White
import com.example.domain.entities.CityHistoryEntity
import androidx.compose.material3.TextField
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.checkweather.Screen.Home.HomeLoadingView


@Composable
fun SearchFragment(
    viewModel: SearchViewModel = hiltViewModel<SearchViewModel>(),
    navController: NavController
) {
    LaunchedEffect(Unit) {
        val result = viewModel.getHistory()
        viewModel.searchFragmentStates.value = result
    }
    when (viewModel.searchFragmentStates.value) {
        is SearchFragmentStates.LoadingState -> {
            HomeLoadingView()
        }

        is SearchFragmentStates.SuccessState<*> -> {
            SearchFragmentContent(viewModel, navController)
        }

        is SearchFragmentStates.ErrorState -> {
            // Handle error state
        }
    }
}

@Composable
fun SearchFragmentContent(viewModel: SearchViewModel, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(painterResource(R.drawable.bg), contentScale = ContentScale.FillBounds)
            .padding(Dimens.PaddingMedium),

        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = Dimens.PaddingSmall),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painterResource(R.drawable.arrow_back_ios_24dp_5f6368_fill0_wght400_grad0_opsz24_1),
                contentDescription = stringResource(R.string.arrowback),
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                    }
                    .padding(Dimens.PaddingSmall)
            )
            Text(
                text = stringResource(R.string.back),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(Dimens.PaddingLarge))

        TextField(
            value = viewModel.cityName.value,
            onValueChange = { value -> viewModel.cityName.value = value },
            label = { Text(text = stringResource(R.string.enter_city)) },
            placeholder = { Text(text = stringResource(R.string.city)) },
            isError = viewModel.errorMessage.value != null,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = Dimens.PaddingSmall)
        )

        Spacer(modifier = Modifier.height(Dimens.PaddingMedium))

        Button(
            onClick = {
                if (viewModel.validateCityName()) {
                    navController.previousBackStackEntry?.savedStateHandle?.set(
                        ConstKey.cityName,
                        viewModel.cityName.value
                    )
                    navController.popBackStack()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.PaddingSmall),
            shape = MaterialTheme.shapes.medium,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
        ) {
            Text(stringResource(R.string.search), color = White)
        }

        Spacer(modifier = Modifier.height(Dimens.PaddingMedium))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimens.PaddingSmall),
            Arrangement.SpaceBetween
        ) {
            Text(
                "RecentSearch", style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize =16.sp
                )
            )

            Text(
                "Clear", style = TextStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 16.sp
                ),
                modifier = Modifier.clickable {

                })
        }
        if (viewModel.cityHistory.value.isEmpty()) {
            Text(
                text = stringResource(R.string.no_history),
                color = Color.Blue,
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = Dimens.PaddingSmall)
            ) {
                items(viewModel.cityHistory.value) { item ->
                    CityHistoryItem(
                        item = item,
                        viewModel = viewModel,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun CityHistoryItem(
    item: CityHistoryEntity,
    viewModel: SearchViewModel,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.PaddingSmall)
            .clickable {
                viewModel.cityName.value = item.cityName
                navController.previousBackStackEntry?.savedStateHandle?.set(
                    ConstKey.cityName,
                    viewModel.cityName.value
                )
                navController.popBackStack()
            },
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painterResource(R.drawable.reload),
            contentDescription = stringResource(R.string.reload),
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.padding(start = Dimens.PaddingXSmall))
        Text(
            text = item.cityName,
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
