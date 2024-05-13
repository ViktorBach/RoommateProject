package com.example.roommateproject.FrontPage.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
fun ChatButton() {
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(0.99f)
            .wrapContentHeight(),
        contentAlignment = Alignment.TopCenter
    ) {
        Button(
            onClick = {}, //Insert pop-up with chat
            colors = ButtonDefaults.buttonColors(
                lightYellow // Set the text color to lightYellow
            ), modifier = Modifier.padding(end = 10.dp)
        ) {
            Text(
                text = "Chat",
                fontFamily = jaldiBoldFontFamily,
                style = Typography.labelSmall,
                color = white
            )
        }
    }
}