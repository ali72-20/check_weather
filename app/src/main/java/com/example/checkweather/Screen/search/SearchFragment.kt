package com.example.checkweather.Screen.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.checkweather.R
import com.example.checkweather.Screen.Home.LoadingView
import com.example.checkweather.core.ConstKey
import com.example.checkweather.core.Dimens
import com.example.checkweather.managers.search.SearchFragmentStates
import com.example.checkweather.managers.search.SearchViewModel
import com.example.checkweather.ui.theme.White

@Composable
fun SearchFragment(
    viewModel: SearchViewModel = hiltViewModel<SearchViewModel>(),
    navController: NavController
) {
    LaunchedEffect(Unit) {
       val result = viewModel.getHistory()
       viewModel.searchFragmentStates.value = result
    }
    when(viewModel.searchFragmentStates.value){
        is SearchFragmentStates.LoadingState -> {
            LoadingView()
        }
        is SearchFragmentStates.SuccessState<*> -> {
            SearchFragmentContent(viewModel,navController)
        }
        is SearchFragmentStates.ErrorState -> {
        }
    }
}


@Composable
fun SearchFragmentContent(viewModel: SearchViewModel, navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            shape = MaterialTheme.shapes.medium,
            value = viewModel.cityName.value,
            onValueChange = { value ->
                viewModel.cityName.value = value
            },
            label = { Text(text = stringResource(R.string.enter_city)) },
            placeholder = { Text(text = "City") },
            isError = viewModel.errorMessage.value != null,
        )
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
            shape = MaterialTheme.shapes.medium
        ) {
            Text(stringResource(R.string.search), color = White)
        }
        if (viewModel.cityHistory.value.isEmpty()) {
            Text("No history available", color = Color.Blue)
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(viewModel.cityHistory.value) { item ->
                    Text(
                        text = item.cityName,
                        color = Color.Blue,
                        modifier = Modifier
                            .padding(Dimens.PaddingSmall)
                            .clickable {
                                viewModel.cityName.value = item.cityName
                                navController.previousBackStackEntry?.savedStateHandle?.set(
                                    ConstKey.cityName,
                                    viewModel.cityName.value
                                )
                                navController.popBackStack()
                            }
                    )
                }
            }
        }
    }
}
