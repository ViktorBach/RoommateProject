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
fun GuestVisitButton() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxHeight(0.25f)
            .fillMaxWidth(0.5f),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {}, //insert notification that user is having guests over
            colors = ButtonDefaults.buttonColors(
                lightYellow
            ), modifier = Modifier.padding(start = 25.dp)
        ) {
            Text(
                text = "  Guest visit  ",
                fontFamily = jaldiBoldFontFamily,
                style = Typography.labelSmall,
                color = white
            )
        }
    }
}