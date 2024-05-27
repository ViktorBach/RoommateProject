package com.example.roommateproject.FrontPage

import HomeToggleButton
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.FrontPage.Components.Buttons.EarlyMorningButton
import com.example.roommateproject.FrontPage.Components.Buttons.GuestVisitButton
import com.example.roommateproject.FrontPage.Components.Buttons.SleepingButton
import com.example.roommateproject.FrontPage.Components.Buttons.WorkingLateButton
import com.example.roommateproject.FrontPage.Components.CalendarTab
import com.example.roommateproject.FrontPage.Components.ListView.AddToListButton
import com.example.roommateproject.FrontPage.Components.LogoutButton.LogoutButton
import com.example.roommateproject.FrontPage.Components.NewsTab.NewsTab
import com.example.roommateproject.FrontPage.Components.UI.RoundedCornerShape
import com.example.roommateproject.R
import com.example.roommateproject.ui.theme.Typography
import com.example.roommateproject.ui.theme.karantinaFontFamily
import com.example.roommateproject.ui.theme.katibehFontFamily
import com.example.roommateproject.ui.theme.lightBlue
import com.example.roommateproject.ui.theme.orange
import com.example.roommateproject.ui.theme.white
import kotlinx.coroutines.launch

/*****************************************************************************/
                                // FrontPage UI //
/*****************************************************************************/
@SuppressLint("Range")
@Composable
fun FrontPage(
    drawerState: androidx.compose.material3.DrawerState,
    navigateRegisterPage: () -> Unit, function: () -> Unit) {

    var isDrawerOpen by remember { mutableStateOf(false) } // Track whether the drawer is open or closed

    val frontPageViewModel = viewModel<FrontPageViewModel>()
    val coroutineScope = rememberCoroutineScope()

    // Observe the state of the drawer and update the visibility accordingly
    LaunchedEffect(drawerState.isOpen) {
        isDrawerOpen = drawerState.isOpen
    }

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
                // Call the Logout Button file from Components
                LogoutButton(
                    navigateRegisterPage = navigateRegisterPage,
                    onClick = { frontPageViewModel.logOut(navigateRegisterPage) }
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.Center
        ) {
            HomeToggleButton() // Calls the HomeButton file from Components
            Spacer(modifier = Modifier.width(16.dp))
            // Calls the AddToListButton file from Components
            AddToListButton { item -> frontPageViewModel.createTask(item) }
        }

        Spacer(modifier = Modifier.height(22.dp))

        Row(
            verticalAlignment = Alignment.Top // Align items to the top of the row
        ) {
            Box(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(topRight = 20.dp, bottomRight = 20.dp))
                    .background(lightBlue.copy(alpha = 0.75f))
                    .height(100.dp)
                    .width(40.dp)
                    .wrapContentHeight()
                    .clickable {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }
                    .padding(start = 0.dp, top = 5.dp, end = 3.dp, bottom = 3.dp)
            ) {
                Image(
                    modifier = Modifier.size(60.dp),
                    painter = painterResource(id = R.drawable.ic_grocery_logo),
                    contentDescription = "Grocery Logo"
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            NewsTab() // Calls the NewsTab file from Components
        }
        Spacer(modifier = Modifier.height(22.dp))

        Row {
            CalendarTab() // Calls the CalendarTab file from Components
        }
        Spacer(modifier = Modifier.height(30.dp))

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
