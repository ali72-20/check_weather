package com.example.checkweather.Screen.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.MaterialTheme
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.checkweather.core.ConstKey
import com.example.checkweather.core.Dimens
import com.example.checkweather.managers.search.SearchViewModel
import com.example.checkweather.ui.theme.White

@Composable
fun SearchFragment(
    viewModel :SearchViewModel = hiltViewModel<SearchViewModel>(),
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            shape = MaterialTheme.shapes.medium,
            value =viewModel.cityName.value,
            onValueChange = {value->
                viewModel.cityName.value = value
            },
            label = { Text(text = "Enter City") },
            placeholder = { Text(text = "City") },
            isError = viewModel.errorMessage.value != null
        )
        Button(onClick = {
            if(viewModel.validateCityName()) {
                navController.previousBackStackEntry?.savedStateHandle?.set(
                    ConstKey.cityName,
                    viewModel.cityName.value
                )
                navController.popBackStack()
            }
        }, modifier = Modifier.fillMaxWidth().padding(Dimens.PaddingSmall), shape = MaterialTheme.shapes.medium) {
            Text("search", color = White)
        }
    }
}
