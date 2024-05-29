package com.example.roommateproject.FrontPage.Components.Buttons

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.roommateproject.Services.AccountService
import com.example.roommateproject.Services.LocalDataStorage.EventType
import com.example.roommateproject.Services.LocalDataStorage.LocalDataStorage
import com.example.roommateproject.ui.theme.Typography
import com.example.roommateproject.ui.theme.jaldiBoldFontFamily
import com.example.roommateproject.ui.theme.lightYellow
import com.example.roommateproject.ui.theme.white
import sendNotification

/*****************************************************************************/
// Sleeping Button //

/*****************************************************************************/

// Natazja
@Composable
fun SleepingButton() {
    val accountService: AccountService = AccountService()

    val context = LocalContext.current
    Box(
        modifier =
            Modifier
                .background(Color.White)
                .fillMaxWidth(0.483f)
                .fillMaxHeight(0.38f),
        contentAlignment = Alignment.Center,
    ) {
        Button(
            onClick = {
                sendNotification("${LocalDataStorage.currentUserName} is going to bed", context)
                accountService.addEvent(EventType.I_AM_SLEEPING)
            }, // insert notification that user is going to bed
            shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
            colors =
                ButtonDefaults.buttonColors(
                    lightYellow,
                ),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(5.dp), // Set content padding to zero
            modifier = Modifier.padding(start = 60.dp),
        ) {
            Text(
                text = "I'm Sleeping ",
                fontFamily = jaldiBoldFontFamily,
                style = Typography.labelSmall,
                color = white,
            )
        }
    }
}
