package com.example.roommateproject.FrontPage.Components.UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.roommateproject.FrontPage.Components.ListView.ListScreenStateful
import com.example.roommateproject.R
import com.example.roommateproject.ui.theme.lightBlue

// Custom drawer that opens and closes the to-do list
// Natazja
@Composable
fun CustomDrawer(
    isOpen: Boolean,
    onClose: () -> Unit,
    content: @Composable () -> Unit,
) {
    if (isOpen) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            // Full screen background that captures clicks to close the drawer
            Box(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(top = 183.dp)
                        .background(Color.Transparent) // Set the background to transparent
                        .clickable(onClick = onClose),
            ) {
                // Row that contains the drawer content and the non-clickable button
                Row(
                    modifier = Modifier.wrapContentSize(),
                ) {
                    // Blue box content
                    Box(
                        modifier =
                            Modifier
                                .fillMaxHeight(0.59f)
                                .fillMaxWidth(0.85f) // 80% of the screen width
                                .background(lightBlue.copy(alpha = 0.75f))
                                // Capture clicks inside the blue box to prevent drawer from closing
                                .clickable(onClick = { /* Do nothing */ }),
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        Column {
                            ListScreenStateful()
                        }
                    }
                    // Box representing the drawer button (non-clickable)
                    Box {
                        Box(
                            modifier =
                                Modifier
                                    .clip(
                                        shape =
                                            RoundedCornerShape(
                                                topRight = 20.dp,
                                                bottomRight = 20.dp,
                                            ),
                                    )
                                    .background(lightBlue.copy(alpha = 0.75f))
                                    .height(100.dp) // Adjust the width to make it rectangular
                                    .width(40.dp)
                                    .padding(start = 0.dp, top = 5.dp, end = 3.dp, bottom = 3.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            Image(
                                modifier =
                                    Modifier
                                        .size(60.dp),
                                // Adjust the size as needed
                                painter = painterResource(id = R.drawable.ic_grocery_logo), // Use your PNG here
                                contentDescription = "Grocery Logo",
                            )
                        }
                    }
                }
            }
        }
    }
}
