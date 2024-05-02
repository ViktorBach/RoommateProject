package com.example.roommateproject.Register.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.roommateproject.ui.theme.Typography
import com.example.roommateproject.ui.theme.lightYellow
import com.example.roommateproject.ui.theme.playFairDisplayFontFamily

@Composable
fun WelcomeHomieTab() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxHeight(0.2f)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Welcome Homie!",
            fontFamily = playFairDisplayFontFamily,
            style = Typography.titleSmall,
            color = lightYellow,
        )
    }
}