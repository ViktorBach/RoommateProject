package com.example.roommateproject.Login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.roommateproject.Register.RegisterViewModel
import com.example.roommateproject.ui.theme.Typography
import com.example.roommateproject.ui.theme.green
import com.example.roommateproject.ui.theme.jaldiFontFamily
import com.example.roommateproject.ui.theme.lightYellow

/*****************************************************************************/
// Login Screen //

/*****************************************************************************/
// Login function
@Composable
fun Login(navigateFrontPage: () -> Unit) {
    val registerViewModel = viewModel<RegisterViewModel>()

    Box(
        modifier =
            Modifier
                .background(Color.White)
                .fillMaxHeight(0.75f)
                .fillMaxWidth(1f),
        contentAlignment = Alignment.Center,
    ) {
        Button(
            onClick = { registerViewModel.loginWithUser(navigateFrontPage) },
            colors =
                ButtonDefaults.buttonColors(
                    lightYellow, // Set the text color to lightYellow
                ),
        ) {
            Text(
                text = "Login",
                fontFamily = jaldiFontFamily,
                style = Typography.labelSmall,
                color = green,
            )
        }
    }
}
