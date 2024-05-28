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
import com.example.roommateproject.ui.theme.Typography
import com.example.roommateproject.ui.theme.jaldiBoldFontFamily
import com.example.roommateproject.ui.theme.lightYellow
import com.example.roommateproject.ui.theme.white
import sendNotification

/*****************************************************************************/
// Guest Visit Button //

/*****************************************************************************/
@Composable
fun GuestVisitButton() {
    val accountService: AccountService = AccountService()
    val context = LocalContext.current

    Box(
        modifier =
            Modifier
                .background(Color.White)
                .fillMaxWidth(0.483f)
                .fillMaxHeight(0.75f),
        contentAlignment = Alignment.Center,
    ) {
        Button(
            onClick = {
                sendNotification("${AccountService.currentUserName} is having guests over", context)

                accountService.addEvent(AccountService.EventType.GUEST_VISIT)
            }, // insert notification that user is having guests over
            shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
            colors =
                ButtonDefaults.buttonColors(
                    lightYellow,
                ),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(5.dp), // Set content padding to zero
            modifier = Modifier.padding(start = 60.dp),
        ) {
            Text(
                text = "  Guest Visit  ",
                fontFamily = jaldiBoldFontFamily,
                style = Typography.labelSmall,
                color = white,
            )
        }
    }
}
