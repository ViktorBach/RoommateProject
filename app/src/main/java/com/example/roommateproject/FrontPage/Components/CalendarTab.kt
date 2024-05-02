package com.example.roommateproject.FrontPage.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.roommateproject.R

@Composable
fun CalendarTab() {
    Box(
        modifier = Modifier
            .fillMaxHeight(0.35f)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.calender),
            contentDescription = null, // Provide proper content description
            modifier = Modifier
                .fillMaxSize()
        )
    }
}