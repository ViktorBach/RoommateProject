package com.example.roommateproject.FrontPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.R
import com.example.roommateproject.ui.theme.Neutral2
import com.example.roommateproject.ui.theme.Neutral3
import com.example.roommateproject.ui.theme.Typography
import com.example.roommateproject.ui.theme.green
import com.example.roommateproject.ui.theme.jaldiBoldFontFamily
import com.example.roommateproject.ui.theme.jaldiFontFamily
import com.example.roommateproject.ui.theme.karantinaFontFamily
import com.example.roommateproject.ui.theme.katibehFontFamily
import com.example.roommateproject.ui.theme.lightBlue
import com.example.roommateproject.ui.theme.lightGrey
import com.example.roommateproject.ui.theme.lightYellow
import com.example.roommateproject.ui.theme.orange
import com.example.roommateproject.ui.theme.white

@Composable
fun FrontPage(navigateRegisterPage: () -> Unit, function: () -> Unit) {
    val frontPageViewModel = viewModel<FrontPageViewModel>()

    Column {
        Spacer(modifier = Modifier.width(16.dp))
        Row {
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
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight(0.13f),
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
        Row {
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
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight(0.2f)
                    .fillMaxWidth(0.63f),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {}, //Insert pop-up with grocery list
                    colors = ButtonDefaults.buttonColors(
                        lightYellow // Set the text color to lightYellow
                    )
                ) {
                    Text(
                        text = "Add to list",
                        fontFamily = jaldiBoldFontFamily,
                        style = Typography.labelSmall,
                        color = white

                    )
                }
            }
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight(0.2f)
                    .fillMaxWidth(0.99f),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {}, //Insert pop-up with chat
                    colors = ButtonDefaults.buttonColors(
                        lightYellow // Set the text color to lightYellow
                    ), modifier = Modifier.padding(end = 8.dp)
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
        Row {
            Box(
                modifier = Modifier
                    .fillMaxHeight(0.2f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.news),
                    contentDescription = null, // Provide proper content description
                    modifier = Modifier
                        .height(180.dp) // Adjust the height of the Image
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row {
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
        Row {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth(0.5f),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {}, //insert notification that user is going to bed
                    colors = ButtonDefaults.buttonColors(
                        lightYellow
                    ), modifier = Modifier.padding(start = 25.dp)
                ) {
                    Text(
                        text = "I'm sleeping ",
                        fontFamily = jaldiBoldFontFamily,
                        style = Typography.labelSmall,
                        color = white
                    )
                }
            }
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth(1f),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {}, //Insert notification that user is going to be home late
                    colors = ButtonDefaults.buttonColors(
                        lightYellow // Set the text color to lightYellow
                    ), modifier = Modifier.padding(end = 25.dp)
                ) {
                    Text(
                        text = " Working late ",
                        fontFamily = jaldiBoldFontFamily,
                        style = Typography.labelSmall,
                        color = white
                    )
                }
            }
        }
        Row {
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
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight(0.25f)
                    .fillMaxWidth(1f),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = {}, //Insert notification that user is getting up early in the morning
                    colors = ButtonDefaults.buttonColors(
                        lightYellow // Set the text color to lightYellow
                    ), modifier = Modifier.padding(end = 20.dp)
                ) {
                    Text(
                        text = "Early morning",
                        fontFamily = jaldiBoldFontFamily,
                        style = Typography.labelSmall,
                        color = white
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight(0.5f)
                    .fillMaxWidth(1f),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = { frontPageViewModel.logOut(navigateRegisterPage) }) {
                    Text(text = "Logout")
                }
            }
        }
    }
}
