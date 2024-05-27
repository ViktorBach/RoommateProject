package com.example.roommateproject.RoomLogin.Components

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
import com.example.roommateproject.RoomLogin.RoomViewModel
import com.example.roommateproject.ui.theme.Typography
import com.example.roommateproject.ui.theme.darkGreen
import com.example.roommateproject.ui.theme.jaldiFontFamily
import com.example.roommateproject.ui.theme.lightYellow

@Composable
fun RoomRegisterButton(
    isLoading: Boolean,
    navigateFrontPage: () -> Unit,
    onClick: () -> Unit
) {
    val roomViewModel = viewModel<RoomViewModel>()

    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxHeight(0.75f)
            .fillMaxWidth(0.5f),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                if (roomViewModel.houseName.isNotEmpty() &&
                    roomViewModel.password.isNotEmpty() &&
                    roomViewModel.members.isNotEmpty()
                ) {
                    roomViewModel.registerNewHouse(navigateFrontPage)
                } else {
                    println("Error: All fields must be filled")
                    // Show an appropriate error message to the user (e.g., Toast)
                }
            },
            colors = ButtonDefaults.buttonColors(
                lightYellow
            )
        ) {
            Text(
                text = "Register",
                fontFamily = jaldiFontFamily,
                style = Typography.labelSmall,
                color = darkGreen // Ensure this is the correct color from your theme
            )
        }
    }
}
