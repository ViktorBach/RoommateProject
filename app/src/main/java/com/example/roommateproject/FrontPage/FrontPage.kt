package com.example.roommateproject.FrontPage

import HomeButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.FrontPage.Components.AddToListButton
import com.example.roommateproject.FrontPage.Components.CalendarTab
import com.example.roommateproject.FrontPage.Components.ChatButton
import com.example.roommateproject.FrontPage.Components.EarlyMorningButton
import com.example.roommateproject.FrontPage.Components.GuestVisitButton
import com.example.roommateproject.FrontPage.Components.LogoutButton.LogoutButton
import com.example.roommateproject.FrontPage.Components.NewsTab
import com.example.roommateproject.FrontPage.Components.SleepingButton
import com.example.roommateproject.FrontPage.Components.WorkingLateButton
import com.example.roommateproject.R
import com.example.roommateproject.ui.theme.Typography
import com.example.roommateproject.ui.theme.karantinaFontFamily
import com.example.roommateproject.ui.theme.katibehFontFamily
import com.example.roommateproject.ui.theme.orange
import com.example.roommateproject.ui.theme.white
import kotlinx.coroutines.launch


@Composable
fun FrontPage(
    drawerState: androidx.compose.material3.DrawerState,
    navigateRegisterPage: () -> Unit, function: () -> Unit) {
    val frontPageViewModel = viewModel<FrontPageViewModel>()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .background(white)
    ) {
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
                    modifier = Modifier.padding(start = 5.dp)
                )
            }
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxHeight(0.1f),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "omie",
                    fontFamily = katibehFontFamily,
                    style = Typography.titleMedium,
                    color = orange,
                    modifier = Modifier.padding(top = 47.dp)
                )
            }
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .padding(start = 240.dp)
                    .fillMaxHeight(0.1f),
                contentAlignment = Alignment.CenterEnd
            ) {
                LogoutButton(
                    navigateRegisterPage = navigateRegisterPage,
                    onClick = { frontPageViewModel.logOut(navigateRegisterPage) }
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Row {
            HomeButton() // Calls the HomeButton file from Components
            AddToListButton {} // Calls the AddToListButton file from Components
            ChatButton() // Calls the ChatButton file from Components
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                coroutineScope.launch {
                    drawerState.open()
                }
            }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_grocery_logo), // Use your PNG here
                    contentDescription = "Grocery Logo"
                )
            }
            NewsTab() // Calls the NewsTab file from Components
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row {
            CalendarTab() // Calls the CalendarTab file from Components
        }
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .wrapContentHeight()
        ) {
            Row {
                SleepingButton() // Calls the SleepingButton file from Components
                WorkingLateButton() // Calls the WorkingLateButton file from Components
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .wrapContentHeight()
        ) {
            Row {
                GuestVisitButton() // Calls the GuestVisitButton file from Components
                EarlyMorningButton() // Calls the EarlyMorning file from Components
            }
        }
    }
}
