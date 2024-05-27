package com.example.roommateproject.SharedComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.roommateproject.ui.theme.Typography
import com.example.roommateproject.ui.theme.karantinaFontFamily
import com.example.roommateproject.ui.theme.katibehFontFamily
import com.example.roommateproject.ui.theme.orange

@Composable
fun Header() {
    Row{
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxHeight(0.1f),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "H",
                fontFamily = karantinaFontFamily,
                style = Typography.titleLarge,
                color = orange,
                modifier = Modifier.padding(start = 2.dp)
            )
        }
        Box(
            modifier = Modifier
                .background(Color.White)
                .fillMaxHeight(0.165f),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "omie",
                fontFamily = katibehFontFamily,
                style = Typography.titleMedium,
                color = orange
            )
        }
    }
}
