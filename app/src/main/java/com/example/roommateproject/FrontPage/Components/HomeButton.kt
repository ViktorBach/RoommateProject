package com.example.roommateproject.FrontPage.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.roommateproject.ui.theme.Typography
import com.example.roommateproject.ui.theme.jaldiBoldFontFamily
import com.example.roommateproject.ui.theme.lightYellow
import com.example.roommateproject.ui.theme.white

@Composable
fun HomeButton() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxHeight(0.2f)
            .fillMaxWidth(0.33f),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {}, //insert notification that user is home
            colors = ButtonDefaults.buttonColors(
                lightYellow
            ), modifier = Modifier.padding(start = 10.dp)
        ) {
            Text(
                text = "I'm home",
                fontFamily = jaldiBoldFontFamily,
                style = Typography.labelSmall,
                color = white
            )
        }
    }
}