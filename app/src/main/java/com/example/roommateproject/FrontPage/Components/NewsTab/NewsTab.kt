package com.example.roommateproject.FrontPage.Components.NewsTab

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.ui.theme.boxLayerGrey
import com.example.roommateproject.ui.theme.darkGreen
import com.example.roommateproject.ui.theme.jaldiFontFamily
import java.text.SimpleDateFormat
import java.util.Locale

/*****************************************************************************/
                    // News Tab Screen Composable Function //
/*****************************************************************************/
@Composable
fun NewsTab(newsViewModel: NewsViewModel = viewModel()) {
    val events by newsViewModel.events.collectAsState()
    val sortedEvents = events.sortedByDescending { it.timeStamp }

    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(30.dp)),
        contentAlignment = Alignment.TopCenter,
    ) {
        Box(
            modifier = Modifier
                .background(boxLayerGrey)
                .fillMaxHeight(0.25f)
                .fillMaxWidth(0.85f)
                .clip(shape = RoundedCornerShape(52.dp)),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight(0.9f)
                    .verticalScroll(ScrollState(1), true)
                    .fillMaxWidth(0.92f),
                contentAlignment = Alignment.TopCenter
            ) {
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 4.dp),
                    ) {
                        Text(
                            text = "NEWS",
                            color = darkGreen,
                            fontFamily = jaldiFontFamily,
                            fontSize = 25.sp
                        )
                    }
                    val sdf = SimpleDateFormat("EEEE HH:mm", Locale.getDefault())
                    val eventString = sortedEvents.joinToString("\n\n")
                    { "${sdf.format(it.timeStamp)} \n   ${it.eventType}" }
                    Text(
                        text = eventString,
                        fontFamily = jaldiFontFamily,
                        color = Color.DarkGray,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
