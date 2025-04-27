package com.example.checkweather.Screen.forcast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.checkweather.core.Dimens
import com.example.domain.entities.DataItemEntity




@Composable
fun DaysForcastListItem(dataItemEntity: DataItemEntity) {
    Box(
        modifier = Modifier
            .padding(
                vertical = Dimens.PaddingXSmall,
                horizontal = Dimens.PaddingSmall
            )
            .fillMaxWidth()
            .background(
                color = Color(0xFFE0F7FA),
                shape = RoundedCornerShape(Dimens.PaddingXSmall)
            )
            .shadow(4.dp, RoundedCornerShape(Dimens.PaddingXSmall))
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
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )
                )
                Spacer(modifier = Modifier.heightIn(Dimens.PaddingXXSmall))
                WeatherIcon(dataItemEntity.weather?.icon ?: "")
                Text(
                    text = "${dataItemEntity.temp}°C",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00796B)
                    )
                )
            }
        }
    }
}